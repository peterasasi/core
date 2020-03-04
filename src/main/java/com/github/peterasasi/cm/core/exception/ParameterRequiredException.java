package com.github.peterasasi.cm.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParameterRequiredException extends ResponseStatusException {
    public ParameterRequiredException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public ParameterRequiredException(String reason, Throwable cause) {
        super(HttpStatus.OK, reason, cause);
    }

    public ParameterRequiredException(String reason) {
        super(HttpStatus.OK, reason);
    }

    public ParameterRequiredException(HttpStatus status) {
        super(status);
    }

    public ParameterRequiredException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
