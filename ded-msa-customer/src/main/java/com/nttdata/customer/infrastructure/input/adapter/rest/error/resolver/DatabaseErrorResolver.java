package com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver;

import static com.nttdata.customer.infrastructure.util.Constants.DATABASE_EXCEPTION_TITLE;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.customer.infrastructure.util.ErrorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Slf4j
public class DatabaseErrorResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.CONFLICT.value();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version) {
    return new ErrorModel()
        .title(DATABASE_EXCEPTION_TITLE)
        .detail(throwable.getMessage())
        .instance(ErrorUtils.buildErrorCode(status()))
        .type(requestPath);
  }
}
