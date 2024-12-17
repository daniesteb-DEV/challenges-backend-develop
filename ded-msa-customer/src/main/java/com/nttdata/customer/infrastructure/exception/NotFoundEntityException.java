package com.nttdata.customer.infrastructure.exception;

public class NotFoundEntityException extends Exception {

  public NotFoundEntityException(String objectClass) {
    super("Not found " + objectClass + " entity");
  }
}
