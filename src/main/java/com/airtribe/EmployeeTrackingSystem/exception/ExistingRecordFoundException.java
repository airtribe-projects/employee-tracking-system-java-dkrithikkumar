package com.airtribe.EmployeeTrackingSystem.exception;

public class ExistingRecordFoundException extends RuntimeException{
    public ExistingRecordFoundException(String message) {
        super(message);
    }
}
