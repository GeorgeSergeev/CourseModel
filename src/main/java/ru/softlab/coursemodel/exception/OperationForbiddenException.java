package ru.softlab.coursemodel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY)
public class OperationForbiddenException extends RuntimeException {

    public OperationForbiddenException(String message) {
        super(message);
    }

    public OperationForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationForbiddenException(Throwable cause) {
        super(cause);
    }
}
