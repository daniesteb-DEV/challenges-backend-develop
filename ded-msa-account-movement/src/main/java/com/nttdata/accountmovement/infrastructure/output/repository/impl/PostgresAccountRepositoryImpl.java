package com.nttdata.accountmovement.infrastructure.output.repository.impl;

import com.nttdata.accountmovement.infrastructure.output.repository.AccountRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.PostgresAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgresAccountRepositoryImpl implements PostgresAccountRepository {

  private final AccountRepository accountRepository;

}
