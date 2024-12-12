package com.nttdata.customer.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorList;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.customer.infrastructure.util.ErrorUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
public class WebExchangeBindExceptionResolver extends ErrorResolver<ErrorModel> {

  @Override
  protected int status() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @NonNull
  private List<ErrorList> getErrors(@NonNull final WebExchangeBindException exception) {
    return exception.getFieldErrors()
        .stream()
        .map(exceptionWebExchange -> new ErrorList()
            .message(String.format("Bad Request: %s", exceptionWebExchange.getField()))
            .businessMessage(
                String.format(
                    "%s: %s", exceptionWebExchange.getField(),
                    exceptionWebExchange.getDefaultMessage()
                )
            )
        )
        .toList();
  }

  @NonNull
  @Override
  protected ErrorModel buildError(@NonNull final String requestPath,
                                  @NonNull final Throwable throwable,
                                  @NonNull final String version) {
    final var exception = (WebExchangeBindException) throwable;

    return new ErrorModel()
        .title("BAD INPUT")
        .detail(exception.getReason())
        .errors(getErrors(exception))
        .instance(ErrorUtils.buildErrorCode(status()))
        .type(requestPath);
  }
}
