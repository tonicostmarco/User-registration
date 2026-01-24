package com.userregistration.userregistration.controllers.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> messages = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getMessages() {
        return messages;
    }

    public void addError(String fieldName, String fieldMessage) {

        messages.add(new FieldMessage(fieldName, fieldMessage));

    }
}
