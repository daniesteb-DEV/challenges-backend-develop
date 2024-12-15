package com.nttdata.accountmovement.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.accountmovement.infrastructure.util.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Slf4j
public class NotFoundErrorResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.NOT_FOUND.value();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version) {
    return new ErrorModel()
        .title("NOT FOUND")
        .detail(throwable.getMessage())
        .instance(ErrorUtils.buildErrorCode(status()))
        .type(requestPath);
  }
}
