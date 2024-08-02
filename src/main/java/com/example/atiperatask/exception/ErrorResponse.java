package com.example.atiperatask.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private Object message;

    public ErrorResponse(int httpStatus, Object message) {
        this.status = httpStatus;
        this.message = message;
    }
}
