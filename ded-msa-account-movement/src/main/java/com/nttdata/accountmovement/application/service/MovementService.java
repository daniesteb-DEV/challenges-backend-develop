package com.nttdata.accountmovement.application.service;

import com.nttdata.accountmovement.application.input.port.MovementServicePort;
import com.nttdata.accountmovement.application.output.port.RepositoryServicePort;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.domain.MovementReport;
import com.nttdata.accountmovement.infrastructure.exception.BussinessValidException;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapper;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovementService implements MovementServicePort {

  private final RepositoryServicePort repositoryServicePort;
  private final MovementMapper movementMapper;

  @Override
  public Flux<MovementReport> retrieveMovementsByFilter(String customerId,
                                                        OffsetDateTime startDate,
                                                        OffsetDateTime endDate) {
    return repositoryServicePort.findAccountByCustomer(
            customerId
        )
        .flatMap(account -> Mono.zip(
                         Mono.just(account),
                         repositoryServicePort.findMovementsByFilter(
                                 customerId,
                                 startDate,
                                 endDate
                             )
                             .filter(movement -> movement.getAccountNumber()
                                 .equals(account.getAccountNumber()))
                             .collectList()
                     )
                     .map(tupleObjects -> movementMapper.toMovementReport(
                              tupleObjects.getT1(),
                              tupleObjects.getT2()
                          )
                     )
        );
  }

  @Override
  public Mono<Movement> retrieveMovement(String movementUuid) {
    log.info("|-> [service] retrieveMovement start ");
    return repositoryServicePort.findMovementByUuid(movementUuid)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] retrieveMovement finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] retrieveMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Movement> registerMovement(Movement movement) {
    log.info("|-> [service] registerMovement start ");
    return repositoryServicePort.findAccountByNumber(movement.getAccountNumber())
        .flatMap(accountFound -> {
                   switch (movement.getMovementType()) {
                     case "Retiro":

                       if (accountFound.getOpeningBalance().compareTo(BigDecimal.ZERO) == 0) {
                         return Mono.error(new BussinessValidException("Saldo no disponible"));
                       }
                       accountFound.setOpeningBalance(accountFound.getOpeningBalance()
                                                          .subtract(movement.getAmount()));
                       break;
                     case "Deposito":
                       accountFound.setOpeningBalance(accountFound.getOpeningBalance()
                                                          .add(movement.getAmount()));
                       break;
                     default:
                       break;
                   }
                   return repositoryServicePort.updateAccount(accountFound);
                 }
        )
        .flatMap(account -> {
                   movement.setBalance(account.getOpeningBalance());
                   return repositoryServicePort.createMovement(movement);
                 }
        )
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] registerMovement finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] registerMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Movement> updateMovement(Movement movement, String movementUuid) {
    log.info("|-> [service] updateMovement start ");
    return repositoryServicePort.findMovementByUuid(movementUuid)
        .map(movementFound -> {
               movementMapper.updateMovement(movementFound, movement);
               return movementFound;
             }
        )
        .flatMap(repositoryServicePort::updateMovement)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] updateMovement finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] updateMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Boolean> removeMovement(String movementUuid) {
    log.info("|-> [service] removeMovement start ");
    return repositoryServicePort.findMovementByUuid(movementUuid)
        .flatMap(movementFound -> repositoryServicePort.deleteMovement(movementFound.getMovementId()))
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] removeMovement finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] removeMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
