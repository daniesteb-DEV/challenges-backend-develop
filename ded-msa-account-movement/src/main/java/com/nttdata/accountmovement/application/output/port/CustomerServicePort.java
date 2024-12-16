package com.nttdata.accountmovement.application.output.port;

import com.nttdata.accountmovement.domain.Customer;
import reactor.core.publisher.Mono;

public interface CustomerServicePort {

  Mono<Customer> findCustomerById(String customerId);

}
