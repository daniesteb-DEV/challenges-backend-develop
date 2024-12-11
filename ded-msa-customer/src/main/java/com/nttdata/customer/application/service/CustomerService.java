package com.nttdata.customer.application.service;

import com.nttdata.customer.application.input.port.CustomerServicePort;
import com.nttdata.customer.application.output.port.RepositoryServicePort;
import com.nttdata.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService implements CustomerServicePort {

  private final RepositoryServicePort repositoryServicePort;

  @Override
  public Mono<Customer> getCustomer(String customerId) {
    return repositoryServicePort.findCustomerByPersonId(customerId);
  }

  @Override
  public Mono<Customer> registerCustomer(Customer customer) {
    return repositoryServicePort.saveCustomer(customer);
  }

  @Override
  public Mono<Customer> updateCustomer(Customer customer, String personId) {
    return repositoryServicePort.updateCustomer(customer, personId);
  }

  @Override
  public Mono<Boolean> deleteCustomer(String personId) {
    return repositoryServicePort.deleteCustomer(personId);
  }
}
