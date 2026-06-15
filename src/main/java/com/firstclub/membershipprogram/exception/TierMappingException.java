package com.firstclub.membershipprogram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TierMappingException extends RuntimeException {
    public TierMappingException(String message) {
        super(message);
    }
}
