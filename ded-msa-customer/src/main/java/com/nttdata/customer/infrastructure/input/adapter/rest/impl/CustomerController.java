package com.nttdata.customer.infrastructure.input.adapter.rest.impl;

import com.nttdata.customer.infrastructure.input.adapter.rest.CustomersApi;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.CustomerUpdate;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PostCustomerResponse;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PutCustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  @Override
  public Mono<ResponseEntity<Customer>> getCustomer(String id, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PostCustomerResponse>> postCustomer(Customer body,
                                                                 ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PutCustomerResponse>> putCustomer(String id,
                                                               CustomerUpdate body,
                                                               ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteCustomer(String id, ServerWebExchange exchange) {
    return null;
  }
}
