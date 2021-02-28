package com.amtinez.api.rest.crud.handlers;

import com.amtinez.api.rest.crud.errors.FieldError;
import com.amtinez.api.rest.crud.errors.ValidationError;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

/**
 * CustomResponseEntityExceptionHandler is the class that will handle the different exceptions that may occur in our controllers.
 *
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                               final HttpHeaders headers,
                                                               final HttpStatus status,
                                                               final WebRequest request) {
        final List<FieldError> errors = ex.getBindingResult()
                                          .getFieldErrors()
                                          .stream()
                                          .map(fieldError -> FieldError.builder()
                                                                       .field(fieldError.getField())
                                                                       .message(fieldError.getDefaultMessage())
                                                                       .build())
                                          .collect(Collectors.toList());
        final ValidationError validationError = ValidationError.builder()
                                                               .errors(errors)
                                                               .build();
        return new ResponseEntity<>(validationError, headers, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        final FieldError fieldError = FieldError.builder()
                                                .field(ex.getName())
                                                .message("does not have the correct type")
                                                .build();
        final ValidationError validationError = ValidationError.builder()
                                                               .errors(Collections.singletonList(fieldError))
                                                               .build();
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
        final List<FieldError> errors = ex.getConstraintViolations().stream()
                                          .map(constraintViolation -> {
                                              final PathImpl path = (PathImpl) constraintViolation.getPropertyPath();
                                              return FieldError.builder()
                                                               .field(path.getLeafNode().getName())
                                                               .message(constraintViolation.getMessage())
                                                               .build();
                                          })
                                          .collect(Collectors.toList());
        final ValidationError validationError = ValidationError.builder()
                                                               .errors(errors)
                                                               .build();
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

}
