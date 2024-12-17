package com.nttdata.customer.infrastructure.output.repository.impl;

import com.nttdata.customer.infrastructure.exception.DatabaseException;
import com.nttdata.customer.infrastructure.exception.NotFoundEntityException;
import com.nttdata.customer.infrastructure.output.repository.CustomerRepository;
import com.nttdata.customer.infrastructure.output.repository.PostgresCustomerRepository;
import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PostgresCustomerRepositoryImpl implements PostgresCustomerRepository {

  private final CustomerRepository customerRepository;

  @Override
  public Mono<CustomerEntity> findByPersonId(Long personId) {
    log.info("|-> [repository] findByPersonId start");
    return customerRepository.findByPersonId(personId)
        .switchIfEmpty(Mono.error(new NotFoundEntityException("Customer")));
  }

  @Transactional
  @Override
  public Mono<CustomerEntity> save(CustomerEntity customerEntity) {
    log.info("|-> [repository] saveCustomer start");
    return customerRepository.save(customerEntity)
        .doOnSuccess(response -> log.info(
            "|-> [repository] saveCustomer finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] saveCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        )
        .onErrorMap(DatabaseException::new);
  }

  @Transactional
  @Override
  public Mono<Void> delete(Long id) {
    log.info("|-> [repository] delete start");
    return customerRepository.deleteById(id);
  }
}
