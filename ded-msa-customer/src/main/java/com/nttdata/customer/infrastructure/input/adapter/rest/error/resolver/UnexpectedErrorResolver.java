package com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.customer.infrastructure.util.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Slf4j
public class UnexpectedErrorResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.INTERNAL_SERVER_ERROR.value();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version) {

    return new ErrorModel()
        .title("UNEXPECTED ERROR")
        .detail("An unexpected error has occurred")
        .instance(ErrorUtils.buildErrorCode(status()))
        .type(requestPath);
  }
}
