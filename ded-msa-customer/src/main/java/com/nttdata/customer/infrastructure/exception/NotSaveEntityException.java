package com.nttdata.customer.infrastructure.exception;

public class NotSaveEntityException extends Exception {

  public NotSaveEntityException(String objectClass) {
    super("Not save " + objectClass + " entity");
  }
}
