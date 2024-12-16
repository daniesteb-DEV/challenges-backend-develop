package com.nttdata.accountmovement.infrastructure.output.repository;

import com.nttdata.accountmovement.infrastructure.output.repository.entity.AccountEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostgresAccountRepository {

  Flux<AccountEntity> findByCustomerId(String customerId);

  Mono<AccountEntity> findByAccountNumber(String accountNumber);

  Mono<AccountEntity> saveAccount(AccountEntity accountEntity);

  Mono<Void> deleteAccount(Long accountId);

}
