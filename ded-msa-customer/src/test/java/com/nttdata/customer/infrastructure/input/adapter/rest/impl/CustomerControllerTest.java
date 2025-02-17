package com.nttdata.customer.infrastructure.input.adapter.rest.impl;

import static com.nttdata.customer.util.DomainMockDataUtil.getCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nttdata.customer.application.input.port.CustomerServicePort;
import com.nttdata.customer.infrastructure.exception.NotFoundEntityException;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.ErrorResolverHandler;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapper;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapperImpl;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.util.InfrastructureMockDataUtil;
import java.rmi.UnexpectedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

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

  @Test
  void getCustomerThenExpectCustomer() {
    com.nttdata.customer.domain.Customer customer = getCustomer();
    when(customerServicePort.getCustomer(anyString())).thenReturn(Mono.just(customer));
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/customers")
            .queryParam("id", "1725374134")
            .build()
        )
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Customer.class);
    verify(customerServicePort, times(1)).getCustomer(anyString());
  }

  @Test
  void getCustomerThenExpectException() {
    when(customerServicePort.getCustomer(anyString())).thenReturn(Mono.error(new NotFoundEntityException(
        "Customer")));
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/customers")
            .queryParam("id", "")
            .build()
        )
        .exchange()
        .expectStatus()
        .is4xxClientError()
        .expectBody(Customer.class);
  }

  @Test
  void postCustomerTest() {
    Customer infrastructureCustomer = InfrastructureMockDataUtil.getCustomer();
    com.nttdata.customer.domain.Customer domainCustomer = getCustomer();
    when(customerServicePort.registerCustomer(any())).thenReturn(Mono.just(domainCustomer));
    webTestClient
        .post()
        .uri(uriBuilder -> uriBuilder.path("/customers")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(infrastructureCustomer), Customer.class)
        .exchange()
        .expectStatus()
        .isCreated();
    verify(customerServicePort, times(1)).registerCustomer(any());
  }

  @Test
  void postCustomerThenExpectException() {
    Customer infrastructureCustomer = InfrastructureMockDataUtil.getCustomer();
    when(customerServicePort.registerCustomer(any())).thenReturn(Mono.error(new UnexpectedException("")));
    webTestClient
        .post()
        .uri(uriBuilder -> uriBuilder.path("/customers")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(infrastructureCustomer), Customer.class)
        .exchange()
        .expectStatus()
        .is5xxServerError();
  }

  @Test
  void putCustomerTest() {
    Customer infrastructureCustomer = InfrastructureMockDataUtil.getCustomer();
    com.nttdata.customer.domain.Customer domainCustomer = getCustomer();
    when(customerServicePort.updateCustomer(
        any(),
        anyString()
    )).thenReturn(Mono.just(domainCustomer));
    webTestClient
        .put()
        .uri("/customers/{id}", infrastructureCustomer.getId())
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(infrastructureCustomer), Customer.class)
        .exchange()
        .expectStatus()
        .isOk();
    verify(customerServicePort, times(1)).updateCustomer(any(), anyString());
  }

  @Test
  void putCustomerThenExpectException() {
    Customer infrastructureCustomer = InfrastructureMockDataUtil.getCustomer();
    when(customerServicePort.updateCustomer(
        any(),
        anyString()
    )).thenReturn(Mono.error(new NotFoundEntityException("Customer")));
    webTestClient
        .put()
        .uri("/customers/{id}", infrastructureCustomer.getId())
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(infrastructureCustomer), Customer.class)
        .exchange()
        .expectStatus()
        .is4xxClientError();
    verify(customerServicePort, times(1)).updateCustomer(any(), anyString());
  }

  @Test
  void deleteCustomerTest() {
    Customer infrastructureCustomer = InfrastructureMockDataUtil.getCustomer();
    when(customerServicePort.deleteCustomer(anyString())).thenReturn(Mono.just(Boolean.TRUE));
    webTestClient
        .delete()
        .uri("/customers/{id}", infrastructureCustomer.getId())
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();
    verify(customerServicePort, times(1)).deleteCustomer(anyString());
  }

}