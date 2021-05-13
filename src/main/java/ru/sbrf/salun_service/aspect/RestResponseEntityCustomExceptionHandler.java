package ru.sbrf.salun_service.aspect;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sbrf.salun_service.exceptions.CustomException;
import ru.sbrf.salun_service.web.reqponse.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Object> handleConflict(CustomException e, WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
