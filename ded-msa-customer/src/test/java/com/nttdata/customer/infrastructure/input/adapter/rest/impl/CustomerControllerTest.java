package com.nttdata.customer.infrastructure.input.adapter.rest.impl;

import com.nttdata.customer.application.input.port.CustomerServicePort;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.ErrorResolverHandler;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapper;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapperImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(value = CustomerController.class)
@ExtendWith(MockitoExtension.class)
@ImportAutoConfiguration({
    ErrorResolverHandler.class,
    CustomerControllerMapperImpl.class
})
class CustomerControllerTest {

  @MockBean
  private CustomerServicePort customerServicePort;
  @SpyBean
  private CustomerControllerMapper customerControllerMapper;
  @Autowired
  private WebTestClient webTestClient;

  private void getCustomerThenExpectCustomer(
      @NonNull final HttpStatus expectedHttpStatus,
      @NonNull final Class<?> expectedBodyClass
  ) {
    webTestClient.get()
        .uri("/customers")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(expectedHttpStatus)
        .expectBody(expectedBodyClass);
  }

}