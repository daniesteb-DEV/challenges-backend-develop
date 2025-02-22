package com.nttdata.customer.infrastructure.input.adapter.rest.impl;

import com.nttdata.customer.application.input.port.CustomerServicePort;
import com.nttdata.customer.infrastructure.input.adapter.rest.CustomersApi;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapper;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.CustomerUpdate;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PostCustomerResponse;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PutCustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
@Validated
public class CustomerController implements CustomersApi {

  private final CustomerServicePort customerServicePort;
  private final CustomerControllerMapper customerMapper;

  @Override
  public Mono<ResponseEntity<Customer>> getCustomer(String id, ServerWebExchange exchange) {
    log.info("|-> [controller] getCustomer start ");
    return customerServicePort.getCustomer(id)
        .map(customerMapper::toCustomer)
        .map(ResponseEntity::ok)
        .doOnSuccess(response -> log.info("<-| [controller] getCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "<-| [controller] getCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<PostCustomerResponse>> postCustomer(Customer body,
                                                                 ServerWebExchange exchange) {
    log.info("|-> [controller] postCustomer start ");
    return customerServicePort.registerCustomer(customerMapper.toCustomer(body))
        .map(customerMapper::toPostCustomerResponse)
        .map(postCustomerResponse -> ResponseEntity.status(HttpStatus.CREATED)
            .body(postCustomerResponse))
        .doOnSuccess(response -> log.info("<-| [controller] postCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "<-| [controller] postCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<PutCustomerResponse>> putCustomer(String id,
                                                               CustomerUpdate body,
                                                               ServerWebExchange exchange) {
    log.info("|-> [controller] putCustomer start ");
    return customerServicePort.updateCustomer(customerMapper.toCustomer(body), id)
        .map(customerMapper::toPutCustomerResponse)
        .map(ResponseEntity::ok)
        .doOnSuccess(response -> log.info("<-| [controller] putCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "<-| [controller] putCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteCustomer(String id, ServerWebExchange exchange) {
    log.info("|-> [controller] deleteCustomer start ");
    return customerServicePort.deleteCustomer(
            id
        )
        .map(aBoolean -> ResponseEntity.ok().<Void>build());
  }
}
