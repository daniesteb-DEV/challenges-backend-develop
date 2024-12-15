package com.nttdata.accountmovement.infrastructure.exception;

public class NotFoundEntityException extends Exception {
    public NotFoundEntityException(String objectClass) {
        super("Not found " + objectClass + " entity");
    }
}
