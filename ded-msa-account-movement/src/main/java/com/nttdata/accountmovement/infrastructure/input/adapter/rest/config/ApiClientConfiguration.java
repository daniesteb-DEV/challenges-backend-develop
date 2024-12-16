package com.nttdata.accountmovement.infrastructure.input.adapter.rest.config;

import com.nttdata.accountmovement.infrastructure.output.customer.client.CustomerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@RequiredArgsConstructor
public class ApiClientConfiguration {

  private final ApiClientProperties apiClientProperties;

  @Bean
  @NonNull
  public CustomerApi customerApi() {
    final var customerApi = new CustomerApi();
    customerApi.getApiClient()
        .setBasePath(apiClientProperties.getClientCustomer().getBaseUrl());
    return customerApi;
  }
}
