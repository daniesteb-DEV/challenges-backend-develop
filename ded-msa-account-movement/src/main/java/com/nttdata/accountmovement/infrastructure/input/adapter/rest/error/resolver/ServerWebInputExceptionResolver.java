package com.nttdata.accountmovement.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.accountmovement.infrastructure.util.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebInputException;

@Slf4j
public class ServerWebInputExceptionResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version) {
    final var exception = (ServerWebInputException) throwable;

    return new ErrorModel()
        .title("BAD INPUT")
        .detail(exception.getMessage())
        .instance(ErrorUtils.buildErrorCode(status()))
        .type(requestPath);
  }
}
