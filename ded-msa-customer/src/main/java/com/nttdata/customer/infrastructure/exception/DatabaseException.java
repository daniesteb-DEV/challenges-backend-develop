package com.nttdata.customer.infrastructure.exception;

import static com.nttdata.customer.infrastructure.util.Constants.DATABASE_EXCEPTION_DETAIL;

public class DatabaseException extends Exception {

  public DatabaseException(Throwable throwable) {
    super(DATABASE_EXCEPTION_DETAIL, throwable);
  }
}
