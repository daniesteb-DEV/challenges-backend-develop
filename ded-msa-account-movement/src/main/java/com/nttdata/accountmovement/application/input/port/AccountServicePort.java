package com.nttdata.accountmovement.application.input.port;

import com.nttdata.accountmovement.domain.Account;
import reactor.core.publisher.Mono;

public interface AccountServicePort {

  Mono<Account> retrieveAccount(String accountNumber);

  Mono<Account> registerAccount(Account account);

  Mono<Account> updateAccount(Account account, String accountNumber);

  Mono<Boolean> removeAccount(String accountNumber);

}
