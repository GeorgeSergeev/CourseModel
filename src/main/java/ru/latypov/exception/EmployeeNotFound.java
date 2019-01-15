package ru.latypov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
@ResponseBody
public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound(Integer id) {

    }
}
