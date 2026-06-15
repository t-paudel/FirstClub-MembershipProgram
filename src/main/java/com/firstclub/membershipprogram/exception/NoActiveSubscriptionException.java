package com.firstclub.membershipprogram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoActiveSubscriptionException extends RuntimeException {
    public NoActiveSubscriptionException(String message) {
        super(message);
    }
}
