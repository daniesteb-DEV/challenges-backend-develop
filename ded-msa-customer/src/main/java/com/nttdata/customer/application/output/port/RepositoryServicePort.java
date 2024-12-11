package com.nttdata.customer.application.output.port;

import com.nttdata.customer.domain.Customer;
import reactor.core.publisher.Mono;

public interface RepositoryServicePort {

  Mono<Customer> findCustomerByPersonId(String id);

  Mono<Customer> saveCustomer(Customer customer);

  Mono<Customer> updateCustomer(Customer customer, String personId);

  Mono<Boolean> deleteCustomer(String personId);
}
