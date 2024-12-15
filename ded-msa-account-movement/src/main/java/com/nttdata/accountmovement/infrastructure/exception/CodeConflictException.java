package com.nttdata.accountmovement.infrastructure.exception;

import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.ErrorModel;

public class CodeConflictException extends FailureException {

  public CodeConflictException(ErrorModel failure,
                               int errorCode) {
    super(failure, errorCode);
  }
}
