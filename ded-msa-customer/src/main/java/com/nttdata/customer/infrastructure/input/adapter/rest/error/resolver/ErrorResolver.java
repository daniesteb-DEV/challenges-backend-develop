package com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver;


import org.apache.commons.lang3.function.TriFunction;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;

public abstract class ErrorResolver<E> implements
    TriFunction<ServerWebExchange, Throwable, String, E> {

  protected abstract int status();

  protected abstract E buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version);

  @Override
  public E apply(
      @NonNull final ServerWebExchange serverWebExchange,
      @NonNull final Throwable throwable,
      @NonNull final String version
  ) {
    final var error = buildError(
        serverWebExchange.getRequest().getPath().toString(),
        throwable,
        version
    );
    serverWebExchange.getResponse().setRawStatusCode(status());
    return error;
  }
}
