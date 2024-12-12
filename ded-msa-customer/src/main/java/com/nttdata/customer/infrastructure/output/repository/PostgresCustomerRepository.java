package com.nttdata.customer.infrastructure.output.repository;

import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import reactor.core.publisher.Mono;

public interface PostgresCustomerRepository {

    Mono<CustomerEntity> findByPersonId(Long personId);

    Mono<CustomerEntity> save(CustomerEntity customerEntity);

    Mono<Void> delete(Long id);
}
