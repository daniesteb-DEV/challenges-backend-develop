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

@Service
@RequiredArgsConstructor
@Slf4j
public class PostgresServiceAdapter implements RepositoryServicePort {

  private final PostgresCustomerRepository customerRepository;
  private final PostgresPersonRepository personRepository;
  private final CustomerMapper customerMapper;

  @Override
  public Mono<Customer> findCustomerByPersonId(String id) {
    return customerRepository.findByPersonId(id)
        .map(customerMapper::toCustomer);
  }

  @Override
  public Mono<Customer> saveCustomer(Customer customer) {
    return personRepository.save(customerMapper.toPersonEntity(customer))
        .flatMap(personEntity -> {
                   CustomerEntity customerEntity = customerMapper.toCustomerEntity(
                       customer);
                   customerEntity.setPersonId(personEntity);
                   return customerRepository.save(customerEntity);
                 }
        )
        .map(customerMapper::toCustomer);
  }

  @Override
  public Mono<Customer> updateCustomer(Customer customer, String personId) {
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
                                 customerMapper.toCustomerEntity(customerUpdated)
                             )
                             .map(customerMapper::toCustomer)
                     )
        );
  }

  @Override
  public Mono<Boolean> deleteCustomer(String id) {
    return customerRepository.findByPersonId(id)
        .flatMap(customerEntity -> customerRepository.delete(
                     customerEntity.getId()
                 )
        )
        .then(Mono.fromCallable(() -> personRepository.delete(id)))
        .then(Mono.fromCallable(() -> true));
  }
}
