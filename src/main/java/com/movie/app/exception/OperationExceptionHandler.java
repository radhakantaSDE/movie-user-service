package com.movie.app.exception;

import com.movie.app.exception.custom.UserServiceOperationException;
import com.movie.app.exception.custom.UserValidationException;
import com.movie.app.model.response.BaseResponse;
import com.movie.app.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.movie.app.util.Constant.*;

import java.util.Optional;

@ControllerAdvice
public class OperationExceptionHandler {

    @ExceptionHandler(exception = {UserServiceOperationException.class})
    public ResponseEntity<BaseResponse<String>> handleUserServiceOperationException(UserServiceOperationException ex) {

        BaseResponse<String> response = BaseResponse.<String>builder().status(FAILED).message(ex.getErrorMessage()).build();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getHttpStatus() != null) {
            status = ex.getHttpStatus();
        }
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(exception = {UserValidationException.class})
    public ResponseEntity<ErrorResponse> handleUserValidationException(UserValidationException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .status(FAILED)
                .message(ex.getErrorMessage())
                .errorCode(ex.getErrorCode())
                .params(ex.getParams())
                .build();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex.getHttpStatus() != null) {
            status = ex.getHttpStatus();
        }
        return new ResponseEntity<>(response, status);
    }
}
