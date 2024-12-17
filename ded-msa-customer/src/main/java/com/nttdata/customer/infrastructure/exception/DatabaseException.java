package com.nttdata.customer.infrastructure.exception;

public class DatabaseException extends Exception {

  public DatabaseException(Throwable throwable) {
    super(throwable.getMessage(), throwable);
  }
}
