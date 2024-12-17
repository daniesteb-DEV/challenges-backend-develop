package com.nttdata.customer.infrastructure.input.adapter.rest.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.customer.infrastructure.exception.CodeConflictException;
import com.nttdata.customer.infrastructure.exception.DatabaseException;
import com.nttdata.customer.infrastructure.exception.FailureException;
import com.nttdata.customer.infrastructure.exception.NotFoundEntityException;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.ConflictExceptionResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.ConstraintViolationExceptionResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.DatabaseErrorResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.ErrorResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.NotFoundErrorResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.ServerWebInputExceptionResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.UnexpectedErrorResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.UnsupportedMediaTypeStatusExceptionResolver;
import com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver.WebExchangeBindExceptionResolver;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ErrorResolverHandler implements ErrorWebExceptionHandler {

  @Value("${info.project.version}")
  private String version;
  private final ObjectMapper mapper;
  private final UnexpectedErrorResolver unexpectedErrorResolver = new UnexpectedErrorResolver();
  private final Map<Class<? extends Throwable>, ErrorResolver> resolvers = new HashMap<>();

  @PostConstruct
  private void initializeResolvers() {
    resolvers.put(ConstraintViolationException.class, new ConstraintViolationExceptionResolver());
    resolvers.put(
        UnsupportedMediaTypeStatusException.class,
        new UnsupportedMediaTypeStatusExceptionResolver()
    );
    resolvers.put(WebExchangeBindException.class, new WebExchangeBindExceptionResolver());
    resolvers.put(ServerWebInputException.class, new ServerWebInputExceptionResolver());
    resolvers.put(NotFoundEntityException.class, new NotFoundErrorResolver());
    resolvers.put(CodeConflictException.class, new ConflictExceptionResolver());
    resolvers.put(DatabaseException.class, new DatabaseErrorResolver());
  }

  @NonNull
  private static Class<?> getThrowableClass(@NonNull final Throwable throwable,
                                            @NonNull final Class<?>... classes) {
    return Arrays.stream(classes)
        .filter(theClass -> theClass.isInstance(throwable))
        .findFirst()
        .orElse(throwable.getClass());
  }

  @NonNull
  @Override
  public Mono<Void> handle(@NonNull final ServerWebExchange serverWebExchange,
                           @NonNull final Throwable throwable) {
    final var response = serverWebExchange.getResponse();
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    return Mono.just(
            resolvers.getOrDefault(
                getThrowableClass(throwable, FailureException.class),
                unexpectedErrorResolver
            )
        )
        .flatMap(resolver ->
                     response.writeWith(
                         Mono.fromCallable(() -> mapper.writeValueAsBytes(resolver.apply(
                                 serverWebExchange,
                                 throwable,
                                 version
                             )))
                             .map(response.bufferFactory()::wrap)
                     )
        );
  }
}
