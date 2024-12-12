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
    public Mono<PersonEntity> findByIdentification(String personId) {
        log.info("|-> [output-adapter] findByIdentification start ");
        return personRepository.findByIdentification(personId)
                               .doOnSuccess(response -> log.info(
                                       "|-> [output-adapter] findByIdentification finished successfully"))
                               .doOnError(error -> log.error(
                                                  "|-> [output-adapter] findByIdentification finished with error. ErrorDetail: {} ",
                                                  error.getMessage()
                                          )
                               );
    }

    @Transactional
    @Override
    public Mono<PersonEntity> save(PersonEntity personEntity) {
        log.info("|-> [repository] savePerson start");
        return personRepository.save(personEntity)
                               .doOnSuccess(response -> log.info("|-> [repository] savePerson finished successfully."))
                               .doOnError(error -> log.error(
                                       "|-> [repository] savePerson finished with error. ErrorDetail: {}",
                                       error.getMessage())
                               );
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return personRepository.deleteById(id);
    }
}
