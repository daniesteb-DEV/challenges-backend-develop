package com.nttdata.accountmovement.infrastructure.input.adapter.rest.impl;

import com.nttdata.accountmovement.application.input.port.AccountServicePort;
import com.nttdata.accountmovement.application.input.port.MovementServicePort;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.error.ErrorResolverHandler;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.AccountMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.AccountMapperImpl;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapper;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper.MovementMapperImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

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
}