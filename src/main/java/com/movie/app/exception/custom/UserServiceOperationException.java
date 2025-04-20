package com.movie.app.exception.custom;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
public class UserServiceOperationException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public UserServiceOperationException(String errorMessage, String errorCode, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public UserServiceOperationException(String errorMessage) {
        super(errorMessage);
        this.errorCode = null;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public UserServiceOperationException(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = null;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

}
