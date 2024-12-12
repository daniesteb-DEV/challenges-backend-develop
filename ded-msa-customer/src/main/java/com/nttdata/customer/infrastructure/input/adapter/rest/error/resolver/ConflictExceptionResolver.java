package com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.customer.infrastructure.util.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Slf4j
public class ConflictExceptionResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.CONFLICT.value();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull String requestPath,
                                  @NonNull Throwable throwable,
                                  @NonNull String version) {
    return new ErrorModel()
        .title("CONFLICT")
        .detail(throwable.getMessage())
        .type(requestPath)
        .instance(ErrorUtils.buildErrorCode(status()));
  }
}
