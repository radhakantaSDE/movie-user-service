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
public class UserValidationException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
    private Map<String, String> params;

    public UserValidationException(String errorCode,
                                   String errorMessage,
                                   HttpStatus httpStatus,
                                   Map<String, String> params) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.params = params;
    }

    public UserValidationException(String errorCode,
                                   String errorMessage,
                                   Map<String, String> params) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.params = params;
    }

    public UserValidationException(String errorMessage,
                                   Map<String, String> params) {
        super(errorMessage);
        this.errorCode = null;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.params = params;
    }

    public UserValidationException(String errorMessage) {
        super(errorMessage);
        this.errorCode = null;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.params = null;
    }


}
