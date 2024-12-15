package com.nttdata.accountmovement.infrastructure.input.adapter.rest.impl;

import com.nttdata.accountmovement.application.input.port.AccountServicePort;
import com.nttdata.accountmovement.application.input.port.MovementServicePort;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.AccountsApi;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.MovementsApi;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Account;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.AccountUpdate;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Movement;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PostAccountResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PostMovementResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PutAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
@Validated
public class AccountMovementController implements AccountsApi, MovementsApi {

  private final AccountServicePort accountServicePort;
  private final MovementServicePort movementServicePort;

  @Override
  public Mono<ResponseEntity<Void>> deleteAccount(String accountNumber,
                                                  ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Account>> getAccount(String accountNumber,
                                                  ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PostAccountResponse>> postAccount(Account body,
                                                               ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PutAccountResponse>> putAccount(String accountNumber,
                                                             AccountUpdate body,
                                                             ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteMovement(String movementId,
                                                   ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Movement>> getMovement(String movementId,
                                                    ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PostMovementResponse>> postMovement(Movement body,
                                                                 ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PutAccountResponse>> putMovement(String movementId,
                                                              AccountUpdate body,
                                                              ServerWebExchange exchange) {
    return null;
  }
}
