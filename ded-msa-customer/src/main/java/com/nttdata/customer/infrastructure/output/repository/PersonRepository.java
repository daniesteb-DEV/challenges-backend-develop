package com.nttdata.customer.infrastructure.output.repository;

import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, String> {

  Mono<PersonEntity> findById(String id);
}
