package com.nttdata.accountmovement.infrastructure.input.adapter.rest.impl;

import static com.nttdata.accountmovement.util.DomainMockDataUtil.getMovementReportResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.nttdata.accountmovement.application.input.port.AccountServicePort;
import com.nttdata.accountmovement.application.input.port.MovementServicePort;
import com.nttdata.accountmovement.domain.MovementReportResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.error.ErrorResolverHandler;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.AccountMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.AccountMapperImpl;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapperImpl;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.GetMovementByFilterResponse;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(value = AccountMovementController.class)
@ExtendWith(MockitoExtension.class)
@ImportAutoConfiguration({
    ErrorResolverHandler.class,
    AccountMapperImpl.class,
    MovementMapperImpl.class
})
class AccountMovementControllerTest {

  @MockBean
  private AccountServicePort accountServicePort;
  @MockBean
  private MovementServicePort movementServicePort;
  @SpyBean
  private AccountMapper accountMapper;
  @SpyBean
  private MovementMapper movementMapper;
  @Autowired
  private WebTestClient webTestClient;

  @Test
  void givenCustomerIdStartDateAndEndDateWhenRetrieveMovementsByFilterThenReturnMovementReportResponse() {
    MovementReportResponse movementReportResponse = getMovementReportResponse();
    when(movementServicePort.retrieveMovementsByFilter(
        anyString(),
        any(),
        any()
    )).thenReturn(Mono.just(movementReportResponse));
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/movements/reports")
            .queryParam("customerId", "1725374134")
            .queryParam("startDate", OffsetDateTime.now().minusDays(4))
            .queryParam("endDate", OffsetDateTime.now())
            .build()
        )
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(GetMovementByFilterResponse.class);
  }
}