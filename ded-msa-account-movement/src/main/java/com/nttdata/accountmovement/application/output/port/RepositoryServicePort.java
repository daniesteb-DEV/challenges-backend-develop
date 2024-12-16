package com.nttdata.accountmovement.application.output.port;

import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Movement;
import java.time.OffsetDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryServicePort {

  Flux<Account> findAccountByCustomer(String customerId);

  Flux<Movement> findMovementsByFilter(String customerId,
                                       OffsetDateTime startDate,
                                       OffsetDateTime endDate);

  Mono<Account> findAccountByNumber(String accountNumber);

  Mono<Account> createAccount(Account account);

  Mono<Account> updateAccount(Account accountUpdate);

  Mono<Boolean> deleteAccount(Long accountId);

  Mono<Movement> findMovementByUuid(String movementUuid);

  Mono<Movement> createMovement(Movement movement);

  Mono<Movement> updateMovement(Movement movementUpdate);

  Mono<Boolean> deleteMovement(Long movementId);

}
