package com.nttdata.accountmovement.infrastructure.input.adapter.rest.impl;

import com.nttdata.accountmovement.application.input.port.AccountServicePort;
import com.nttdata.accountmovement.application.input.port.MovementServicePort;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.AccountsApi;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.MovementsApi;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.AccountMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Account;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.AccountUpdate;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Movement;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.MovementReport;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.MovementResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PostAccountResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PostMovementResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PutAccountResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PutMovementResponse;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
@Validated
public class AccountMovementController implements AccountsApi, MovementsApi {

  private final AccountServicePort accountServicePort;
  private final MovementServicePort movementServicePort;
  private final AccountMapper accountMapper;
  private final MovementMapper movementMapper;

  @Override
  public Mono<ResponseEntity<Void>> deleteAccount(String accountNumber,
                                                  ServerWebExchange exchange) {
    log.info("|-> [controller] deleteAccount start ");
    return accountServicePort.removeAccount(accountNumber)
        .map(aBoolean -> ResponseEntity.ok().build());
  }

  @Override
  public Mono<ResponseEntity<Account>> getAccount(String accountNumber,
                                                  ServerWebExchange exchange) {
    log.info("|-> [controller] getAccount start ");
    return accountServicePort.retrieveAccount(accountNumber)
        .map(accountMapper::toAccount)
        .map(ResponseEntity::ok)
        .doOnSuccess(response -> log.info("|-> [controller] getAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [controller] getAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<PostAccountResponse>> postAccount(Account body,
                                                               ServerWebExchange exchange) {
    log.info("|-> [controller] postAccount start ");
    return accountServicePort.registerAccount(accountMapper.toAccount(body))
        .map(accountMapper::toPostAccountResponse)
        .map(postAccountResponse -> ResponseEntity.status(HttpStatus.CREATED)
            .body(postAccountResponse))
        .doOnSuccess(response -> log.info("|-> [controller] postAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [controller] postAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<PutAccountResponse>> putAccount(String accountNumber,
                                                             AccountUpdate body,
                                                             ServerWebExchange exchange) {
    log.info("|-> [controller] putAccount start ");
    return accountServicePort.updateAccount(accountMapper.toAccount(body), accountNumber)
        .map(accountMapper::toPutAccountResponse)
        .map(ResponseEntity::ok)
        .doOnSuccess(response -> log.info("|-> [controller] putAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [controller] putAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteMovement(String movementId,
                                                   ServerWebExchange exchange) {
    log.info("|-> [controller] deleteMovement start ");
    return movementServicePort.removeMovement(movementId)
        .map(aBoolean -> ResponseEntity.ok().build());
  }

  @Override
  public Mono<ResponseEntity<MovementResponse>> getMovement(String movementId,
                                                            ServerWebExchange exchange) {
    log.info("|-> [controller] getMovement start ");
    return movementServicePort.retrieveMovement(movementId)
        .map(movementMapper::toMovementResponse)
        .map(ResponseEntity::ok)
        .doOnSuccess(response -> log.info("|-> [controller] getMovement finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [controller] getMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<Flux<MovementReport>>> getMovementByFilter(String customerId,
                                                                        OffsetDateTime startDate,
                                                                        OffsetDateTime endDate,
                                                                        ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(movementServicePort.retrieveMovementsByFilter(
                                               customerId,
                                               startDate,
                                               endDate
                                           )
                                           .map(movementMapper::toMovementReport)
                     )
    );
  }

  @Override
  public Mono<ResponseEntity<PostMovementResponse>> postMovement(Movement body,
                                                                 ServerWebExchange exchange) {
    log.info("|-> [controller] postMovement start ");
    return movementServicePort.registerMovement(movementMapper.toMovement(body))
        .map(movementMapper::toPostMovementResponse)
        .map(postMovementResponse -> ResponseEntity.status(HttpStatus.CREATED)
            .body(postMovementResponse))
        .doOnSuccess(response -> log.info("|-> [controller] postMovement finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [controller] postMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<PutMovementResponse>> putMovement(String movementId,
                                                               Movement body,
                                                               ServerWebExchange exchange) {
    log.info("|-> [controller] putMovement start ");
    return movementServicePort.updateMovement(movementMapper.toMovement(body), movementId)
        .map(movementMapper::toPutMovementResponse)
        .map(ResponseEntity::ok)
        .doOnSuccess(response -> log.info("|-> [controller] putMovement finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [controller] putMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
