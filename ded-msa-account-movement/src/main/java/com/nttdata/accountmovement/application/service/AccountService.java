package com.nttdata.accountmovement.application.service;

import com.nttdata.accountmovement.application.input.port.AccountServicePort;
import com.nttdata.accountmovement.application.output.port.RepositoryServicePort;
import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService implements AccountServicePort {

  private final RepositoryServicePort repositoryServicePort;
  private final AccountMapper accountMapper;

  @Override
  public Mono<Account> retrieveAccount(String accountNumber) {
    log.info("|-> [service] retrieveAccount start ");
    return repositoryServicePort.findAccountByNumber(accountNumber)
        .doOnSuccess(response -> log.info("|-> [service] retrieveAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [service] retrieveAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Account> registerAccount(Account account) {
    log.info("|-> [service] registerAccount start ");
    return repositoryServicePort.createAccount(account)
        .doOnSuccess(response -> log.info("|-> [service] registerAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [service] registerAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Account> updateAccount(Account account, String accountNumber) {
    log.info("|-> [service] updateAccount start ");
    return repositoryServicePort.findAccountByNumber(accountNumber)
        .map(accountFound -> {
               accountMapper.updateAccount(accountFound, account);
               return accountFound;
             }
        )
        .flatMap(repositoryServicePort::updateAccount)
        .doOnSuccess(response -> log.info("|-> [service] updateAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [service] updateAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Boolean> removeAccount(String accountNumber) {
    log.info("|-> [service] removeAccount start ");
    return repositoryServicePort.findAccountByNumber(accountNumber)
        .flatMap(accountFound -> repositoryServicePort.deleteAccount(accountFound.getAccountId()))
        .doOnSuccess(response -> log.info("|-> [service] removeAccount finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [service] removeAccount finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
