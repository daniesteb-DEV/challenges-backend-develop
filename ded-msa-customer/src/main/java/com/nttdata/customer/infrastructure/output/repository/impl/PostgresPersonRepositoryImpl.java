package com.nttdata.customer.infrastructure.output.repository.impl;

import com.nttdata.customer.infrastructure.output.repository.PersonRepository;
import com.nttdata.customer.infrastructure.output.repository.PostgresPersonRepository;
import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PostgresPersonRepositoryImpl implements PostgresPersonRepository {

  private final PersonRepository personRepository;

  @Override
  public Mono<PersonEntity> findById(String id) {
    return personRepository.findById(id);
  }

  @Transactional
  @Override
  public Mono<PersonEntity> save(PersonEntity personEntity) {
    return personRepository.save(personEntity);
  }

  @Transactional
  @Override
  public Mono<Void> delete(String id) {
    return personRepository.deleteById(id);
  }
}
