package com.nttdata.customer.application.service;

import static com.nttdata.customer.util.DomainMockDataUtil.getCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nttdata.customer.application.output.port.RepositoryServicePort;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapper;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    CustomerService.class,
    CustomerControllerMapperImpl.class
})
class CustomerServiceTest {

  @MockBean
  private RepositoryServicePort repositoryServicePort;
  @SpyBean
  private CustomerControllerMapper customerControllerMapper;
  @InjectMocks
  @Autowired
  private CustomerService customerService;

  @Test
  void givenCustomerIdWhenGetCustomerThenReturnCustomer() {
    Customer customer = getCustomer();
    when(repositoryServicePort.findCustomerByPersonId(anyString())).thenReturn(Mono.just(customer));
    StepVerifier.create(customerService.getCustomer(customer.getIdentification()))
        .expectNext(customer)
        .verifyComplete();
    verify(repositoryServicePort, times(1)).findCustomerByPersonId(anyString());
  }

  @Test
  void givenCustomerWhenRegisterCustomerThenReturnCustomer() {
    Customer customer = getCustomer();
    when(repositoryServicePort.saveCustomer(any())).thenReturn(Mono.just(customer));
    StepVerifier.create(customerService.registerCustomer(customer))
        .expectNext(customer)
        .verifyComplete();
    verify(repositoryServicePort, times(1)).saveCustomer(any());
  }

  @Test
  void givenCustomerAndPersonIdWhenUpdateCustomerThenReturnCustomer() {
    Customer customer = getCustomer();
    when(repositoryServicePort.findCustomerByPersonId(anyString())).thenReturn(Mono.just(customer));
    when(repositoryServicePort.updateCustomer(any(), anyString())).thenReturn(Mono.just(customer));
    StepVerifier.create(customerService.updateCustomer(customer, customer.getIdentification()))
        .expectNext(customer)
        .verifyComplete();
    verify(repositoryServicePort, times(1)).findCustomerByPersonId(anyString());
    verify(repositoryServicePort, times(1)).updateCustomer(any(), anyString());
  }

  @Test
  void givenPersonIdWhenDeleteCustomerThenReturnBoolean() {
    Customer customer = getCustomer();
    when(repositoryServicePort.deleteCustomer(anyString())).thenReturn(Mono.just(Boolean.TRUE));
    StepVerifier.create(customerService.deleteCustomer(customer.getIdentification()))
        .expectNext(Boolean.TRUE)
        .verifyComplete();
    verify(repositoryServicePort, times(1)).deleteCustomer(anyString());
  }

}