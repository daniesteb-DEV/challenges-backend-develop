package com.nttdata.accountmovement.infrastructure.input.adapter.rest.error.resolver;

import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.ErrorList;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.ErrorModel;
import com.nttdata.accountmovement.infrastructure.util.ErrorUtils;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Slf4j
public class ConstraintViolationExceptionResolver extends ErrorResolver<ErrorModel> {

  public String getField(Path fieldPath) {
    PathImpl path = (PathImpl) fieldPath;
    return path.getLeafNode().toString();
  }

  @NonNull
  private List<ErrorList> getErrors(@NonNull final ConstraintViolationException exception) {
    return exception.getConstraintViolations()
        .stream()
        .map(constraintViolation -> new ErrorList()
            .message("Bad request")
            .businessMessage(
                String.format(
                    "%s %s", getField(constraintViolation.getPropertyPath()),
                    constraintViolation.getMessage()
                )
            )
        )
        .toList();
  }

  @Override
  protected int status() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  protected ErrorModel buildError(@NonNull String requestPath,
                                  @NonNull Throwable throwable,
                                  @NonNull String version) {
    final var exception = (ConstraintViolationException) throwable;
    final var status = HttpStatus.BAD_REQUEST.value();
    return new ErrorModel()
        .title("BAD INPUT")
        .detail("Input fields are not correct")
        .errors(getErrors(exception))
        .instance(ErrorUtils.buildErrorCode(status))
        .type(requestPath);
  }

}
