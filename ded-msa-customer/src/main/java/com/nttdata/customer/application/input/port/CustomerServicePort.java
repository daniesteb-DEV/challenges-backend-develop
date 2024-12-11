package com.nttdata.customer.application.input.port;

import com.nttdata.customer.domain.Customer;
import reactor.core.publisher.Mono;

public interface CustomerServicePort {

  Mono<Customer> getCustomer(String customerId);

  Mono<Customer> registerCustomer(Customer customer);

  Mono<Customer> updateCustomer(Customer customer, String personId);

  Mono<Boolean> deleteCustomer(String personId);
}
