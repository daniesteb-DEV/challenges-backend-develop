package com.nttdata.customer.application.input.port;

import com.nttdata.customer.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
public interface CustomerServicePort {

  Mono<Customer> getCustomer(String customerId);

  Mono<Customer> registerCustomer(@Valid Customer customer);

  Mono<Customer> updateCustomer(@Valid Customer customer, String personId);

  Mono<Boolean> deleteCustomer(String personId);
}
