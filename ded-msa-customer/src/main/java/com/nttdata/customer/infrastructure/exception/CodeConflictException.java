package com.nttdata.customer.infrastructure.exception;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.ErrorModel;

public class CodeConflictException extends FailureException {

  public CodeConflictException(ErrorModel failure,
                               int errorCode) {
    super(failure, errorCode);
  }
}
