package com.nttdata.accountmovement.application.service;

import static com.nttdata.accountmovement.util.Constants.END_DATE;
import static com.nttdata.accountmovement.util.Constants.START_DATE;
import static com.nttdata.accountmovement.util.DomainMockDataUtil.getAccount;
import static com.nttdata.accountmovement.util.DomainMockDataUtil.getCustomer;
import static com.nttdata.accountmovement.util.DomainMockDataUtil.getMovement;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.nttdata.accountmovement.application.output.port.CustomerServicePort;
import com.nttdata.accountmovement.application.output.port.RepositoryServicePort;
import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Customer;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapperImpl;
import com.nttdata.accountmovement.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {
    MovementService.class,
    MovementMapperImpl.class,
})
class MovementServiceTest {

  @MockBean
  private RepositoryServicePort repositoryServicePort;
  @MockBean
  private CustomerServicePort customerServicePort;
  @SpyBean
  private MovementMapper movementMapper;

  @InjectMocks
  @Autowired
  private MovementService movementService;

  @Test
  void givenMovementUuidWhenretrieveMovementsByFilterThenReturnMovementReportResponse() {
    Account account = getAccount();
    Movement movement = getMovement();
    Customer customer = getCustomer();
    when(repositoryServicePort.findAccountByCustomer(anyString())).thenReturn(Flux.just(account));
    when(repositoryServicePort.findMovementsByFilter(
        anyString(),
        any(),
        any()
    )).thenReturn(Flux.just(
        movement));
    when(customerServicePort.findCustomerById(anyString())).thenReturn(Mono.just(customer));
    StepVerifier.create(movementService.retrieveMovementsByFilter(
                            Constants.CUSTOMER_IDENTIFICATION,
                            START_DATE,
                            END_DATE
                        )
        )
        .expectNextCount(1)
        .verifyComplete();
  }
}