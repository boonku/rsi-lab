package com.example.restservice.exceptions.handler;

import com.example.restservice.exceptions.PersonExistsException;
import com.example.restservice.exceptions.PersonNotFoundException;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class FaultController {

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handlePersonNotFoundException(PersonNotFoundException exception) {
        return createHateosExceptionResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(PersonExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handlePersonExistsException(PersonExistsException exception) {
        return createHateosExceptionResponse(HttpStatus.BAD_REQUEST, exception);
    }

    private ResponseEntity<Problem> createHateosExceptionResponse(HttpStatus status, RuntimeException exception) {
        return ResponseEntity.status(status)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withStatus(status)
                        .withTitle(status.name())
                        .withDetail(exception.getMessage()));
    }

}
