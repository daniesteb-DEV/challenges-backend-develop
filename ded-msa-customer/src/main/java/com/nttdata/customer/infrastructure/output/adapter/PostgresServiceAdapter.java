package com.nttdata.customer.infrastructure.output.adapter;

import com.nttdata.customer.application.output.port.RepositoryServicePort;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infrastructure.output.repository.PostgresCustomerRepository;
import com.nttdata.customer.infrastructure.output.repository.PostgresPersonRepository;
import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import com.nttdata.customer.infrastructure.output.repository.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostgresServiceAdapter implements RepositoryServicePort {

  private final PostgresCustomerRepository customerRepository;
  private final PostgresPersonRepository personRepository;
  private final CustomerMapper customerMapper;

  @Override
  public Mono<Customer> findCustomerByPersonId(String personId) {
    log.info("|-> [output-adapter] findCustomerByPersonId start ");
    return personRepository.findByIdentification(personId)
        .flatMap(personEntity -> Mono.zip(
                     customerRepository.findByPersonId(personEntity.getId()),
                     Mono.just(personEntity)
                 )
        )
        .map(tupleObjects -> customerMapper.toCustomer(
                 tupleObjects.getT1(),
                 tupleObjects.getT2()
             )
        )
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] findCustomerByPersonId finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] findCustomerByPersonId finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Customer> saveCustomer(Customer customer) {
    log.info("|-> [output-adapter] saveCustomer start ");
    return personRepository.save(customerMapper.toPersonEntity(customer))
        .flatMap(personEntity -> {
                   CustomerEntity customerEntity = customerMapper.toCustomerEntity(
                       customer
                   );
                   customerEntity.setPersonId(personEntity.getId());
                   return Mono.zip(customerRepository.save(customerEntity), Mono.just(personEntity));
                 }
        )
        .map(tupleObjects -> customerMapper.toCustomer(
                 tupleObjects.getT1(),
                 tupleObjects.getT2()
             )
        )
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] saveCustomer finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] saveCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Customer> updateCustomer(Customer customer, String personId) {
    log.info("|-> [output-adapter] updateCustomer start ");
    return findCustomerByPersonId(personId).map(customerResponse -> {
                                                  customerMapper.updateCustomer(
                                                      customer,
                                                      customerResponse
                                                  );
                                                  return customer;
                                                }
        )
        .flatMap(customerUpdated -> personRepository.save(
                         customerMapper.toPersonEntity(customerUpdated)
                     )
                     .flatMap(
                         personEntity -> customerRepository.save(
                                 customerMapper.toCustomerEntity(
                                     customerUpdated)
                             )
                             .map(customerEntity -> customerMapper.toCustomer(
                                      customerEntity,
                                      personEntity
                                  )
                             )
                     )
        )
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] updateCustomer finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] updateCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Boolean> deleteCustomer(String personId) {
    log.info("|-> [output-adapter] deleteCustomer start ");
    return personRepository.findByIdentification(personId)
        .flatMap(personEntity -> customerRepository.findByPersonId(personEntity.getId()))
        .publishOn(Schedulers.boundedElastic())
        .map(customerEntity -> {
               customerRepository.delete(
                       customerEntity.getId()
                   )
                   .subscribe();
               return customerEntity;
             }
        )
        .flatMap(customerEntity -> personRepository.delete(
                     customerEntity.getId()
                 )
        )
        .then(Mono.fromCallable(() -> true))
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] deleteCustomer finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] deleteCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
