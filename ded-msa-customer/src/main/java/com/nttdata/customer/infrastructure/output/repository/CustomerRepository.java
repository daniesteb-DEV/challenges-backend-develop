package com.nttdata.customer.infrastructure.output.repository;

import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity, Long> {

  Mono<CustomerEntity> findByPersonId(Long personId);
}
