package com.nttdata.accountmovement.infrastructure.output.adapter;

import com.nttdata.accountmovement.application.output.port.CustomerServicePort;
import com.nttdata.accountmovement.domain.Customer;
import com.nttdata.accountmovement.infrastructure.output.adapter.mapper.CustomerServiceMapper;
import com.nttdata.accountmovement.infrastructure.output.customer.client.CustomerApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceAdapter implements CustomerServicePort {

  private final CustomerApi customerApi;
  private final CustomerServiceMapper customerServiceMapper;

  @Override
  public Mono<Customer> findCustomerById(String customerId) {
    log.info("|-> [output-adapter] findCustomerById start ");
    return customerApi.getCustomer(customerId)
        .map(customerServiceMapper::toCustomer)
        .doOnSuccess(response -> log.info(
                         "|-> [output-adapter] findCustomerById finished successfully"
                     )
        )
        .doOnError(error -> log.error(
                       "|-> [output-adapter] findCustomerById finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
