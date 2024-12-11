package com.nttdata.accountmovement.infrastructure.output.repository;

import com.nttdata.accountmovement.infrastructure.output.repository.entity.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, String> {

  Mono<AccountEntity> findByAccountNumber(String accountNumber);

}
