package com.nttdata.customer.infrastructure.output.repository.impl;

import com.nttdata.customer.infrastructure.output.repository.CustomerRepository;
import com.nttdata.customer.infrastructure.output.repository.PostgresCustomerRepository;
import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
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
        return customerRepository.findByPersonId(personId);
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
                                         error.getMessage())
                                 );
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return customerRepository.deleteById(id);
    }
}
