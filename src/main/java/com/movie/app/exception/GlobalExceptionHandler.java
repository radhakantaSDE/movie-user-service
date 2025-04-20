package com.movie.app.exception;

import com.movie.app.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import static com.movie.app.util.Constant.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( exception = {NoResourceFoundException.class, NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<String> handleNoHandlerException(Exception ex) {

        BaseResponse<String> response = BaseResponse.<String>builder().message(ex.getMessage()).build();
        response.setStatus(FAILED);
        response.setMessage("Invalid url");
        return response;
    }
}
