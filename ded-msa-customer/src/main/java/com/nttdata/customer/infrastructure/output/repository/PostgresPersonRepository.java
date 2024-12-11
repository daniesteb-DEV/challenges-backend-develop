package com.nttdata.customer.infrastructure.output.repository;

import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import reactor.core.publisher.Mono;

public interface PostgresPersonRepository {

  Mono<PersonEntity> findById(String id);

  Mono<PersonEntity> save(PersonEntity personEntity);

  Mono<Void> delete(String id);

}
