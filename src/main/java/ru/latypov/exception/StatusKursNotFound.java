package ru.latypov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
@ResponseBody
public class StatusKursNotFound extends RuntimeException {
    public StatusKursNotFound(Integer id) {

    }
}
