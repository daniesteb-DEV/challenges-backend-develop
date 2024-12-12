package com.nttdata.customer.infrastructure.exception;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Generated
public class FailureException extends RuntimeException {

  final ErrorModel failure;
  final int errorCode;

  public FailureException(@NonNull final ErrorModel failure, final int errorCode) {
    super(failure.getDetail());
    this.failure = failure;
    this.errorCode = errorCode;
  }
}
