package com.nttdata.customer.infrastructure.output.repository;

import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import reactor.core.publisher.Mono;

public interface PostgresPersonRepository {

  Mono<PersonEntity> findByIdentification(String personId);

  Mono<PersonEntity> save(PersonEntity personEntity);

  Mono<Void> delete(Long id);

}
