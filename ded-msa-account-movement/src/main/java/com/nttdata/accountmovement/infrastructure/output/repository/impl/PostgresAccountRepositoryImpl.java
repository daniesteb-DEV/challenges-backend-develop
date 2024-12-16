package com.nttdata.accountmovement.infrastructure.output.repository.impl;

import com.nttdata.accountmovement.infrastructure.exception.NotFoundEntityException;
import com.nttdata.accountmovement.infrastructure.output.repository.AccountRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.PostgresAccountRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostgresAccountRepositoryImpl implements PostgresAccountRepository {

  private final AccountRepository accountRepository;

  @Override
  public Flux<AccountEntity> findByCustomerId(String customerId) {
    log.info("|-> [repository] findByCustomerId start");
    return accountRepository.findByCustomerId(customerId)
        .switchIfEmpty(Mono.error(new NotFoundEntityException("Account")))
        .doOnError(error -> log.error(
                       "|-> [repository] findByCustomerId finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<AccountEntity> findByAccountNumber(String accountNumber) {
    log.info("|-> [repository] findByAccountNumber start");
    return accountRepository.findByAccountNumber(accountNumber)
        .switchIfEmpty(Mono.error(new NotFoundEntityException("Account")))
        .doOnSuccess(response -> log.info(
            "|-> [repository] findByAccountNumber finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] findByAccountNumber finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<AccountEntity> saveAccount(AccountEntity accountEntity) {
    log.info("|-> [repository] saveAccount start");
    return accountRepository.save(accountEntity)
        .doOnSuccess(response -> log.info(
            "|-> [repository] saveAccount finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] saveAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Void> deleteAccount(Long accountId) {
    log.info("|-> [repository] deleteAccount start");
    return accountRepository.deleteById(accountId)
        .doOnSuccess(response -> log.info(
            "|-> [repository] deleteAccount finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] deleteAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
