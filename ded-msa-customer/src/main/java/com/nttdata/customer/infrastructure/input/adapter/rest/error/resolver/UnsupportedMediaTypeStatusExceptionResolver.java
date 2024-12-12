package com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.customer.infrastructure.util.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

@Slf4j
public class UnsupportedMediaTypeStatusExceptionResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version) {
    final var exception = (UnsupportedMediaTypeStatusException) throwable;

    return new ErrorModel()
        .title("UNSUPPORTED MEDIA TYPE")
        .detail(exception.getReason())
        .instance(ErrorUtils.buildErrorCode(status()))
        .type(requestPath);
  }
}
