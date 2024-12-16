package com.nttdata.accountmovement.infrastructure.output.adapter;

import com.nttdata.accountmovement.application.output.port.RepositoryServicePort;
import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.infrastructure.output.repository.PostgresAccountRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.PostgresMovementRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.mapper.PostgresAccountMapper;
import com.nttdata.accountmovement.infrastructure.output.repository.mapper.PostgresMovementMapper;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostgresServiceAdapter implements RepositoryServicePort {

  private final PostgresAccountRepository postgresAccountRepository;
  private final PostgresMovementRepository postgresMovementRepository;
  private final PostgresAccountMapper postgresAccountMapper;
  private final PostgresMovementMapper postgresMovementMapper;

  @Override
  public Flux<Account> findAccountByCustomer(String customerId) {
    log.info("|-> [output-adapter] findAccountByCustomer start ");
    return postgresAccountRepository.findByCustomerId(customerId)
        .map(postgresAccountMapper::toAccount)
        .doOnError(error -> log.error(
                       "|-> [output-adapter] findAccountByCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Flux<Movement> findMovementsByFilter(String customerId,
                                              OffsetDateTime startDate,
                                              OffsetDateTime endDate) {
    log.info("|-> [output-adapter] findMovementsByFilter start ");
    return postgresMovementRepository.findByFilter(customerId, startDate, endDate)
        .map(postgresMovementMapper::toMovement)
        .doOnError(error -> log.error(
                       "|-> [output-adapter] findMovementsByFilter finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Account> findAccountByNumber(String accountNumber) {
    log.info("|-> [output-adapter] findAccountByNumber start ");
    return postgresAccountRepository.findByAccountNumber(accountNumber)
        .map(postgresAccountMapper::toAccount)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] findAccountByNumber finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] findAccountByNumber finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Account> createAccount(Account account) {
    log.info("|-> [output-adapter] createAccount start ");
    return postgresAccountRepository.saveAccount(postgresAccountMapper.toAccountEntity(account))
        .map(postgresAccountMapper::toAccount)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] createAccount finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] createAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Account> updateAccount(Account accountUpdate) {
    log.info("|-> [output-adapter] updateAccount start ");
    return postgresAccountRepository.saveAccount(postgresAccountMapper.toAccountEntity(accountUpdate))
        .map(postgresAccountMapper::toAccount)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] updateAccount finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] updateAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Boolean> deleteAccount(Long accountId) {
    log.info("|-> [output-adapter] deleteAccount start ");
    return postgresAccountRepository.deleteAccount(accountId).then(Mono.fromCallable(() -> true))
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] deleteAccount finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] deleteAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Movement> findMovementByUuid(String movementUuid) {
    log.info("|-> [output-adapter] findMovementByUuid start ");
    return postgresMovementRepository.findByMovementUuid(movementUuid)
        .map(postgresMovementMapper::toMovement)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] findMovementByUuid finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] findMovementByUuid finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Movement> createMovement(Movement movement) {
    log.info("|-> [output-adapter] createMovement start ");
    return postgresMovementRepository.saveMovement(postgresMovementMapper.toMovementEntity(movement))
        .map(postgresMovementMapper::toMovement)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] createMovement finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] createMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Movement> updateMovement(Movement movementUpdate) {
    log.info("|-> [output-adapter] updateMovement start ");
    return postgresMovementRepository.saveMovement(postgresMovementMapper.toMovementEntity(
            movementUpdate)).map(postgresMovementMapper::toMovement)
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
  public Mono<Boolean> deleteMovement(Long movementId) {
    log.info("|-> [output-adapter] deleteMovement start ");
    return postgresMovementRepository.deleteMovement(movementId)
        .then(Mono.fromCallable(() -> true))
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] deleteMovement finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] deleteMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
