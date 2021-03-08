package com.amtinez.api.rest.crud.handlers;

import com.amtinez.api.rest.crud.errors.ValidationError;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomResponseEntityExceptionHandlerUnitTest {

    private static final String FIELD_NAME = "testFieldName";
    private static final String CONSTRAINT_MESSAGE = "testConstraintMessage";
    private static final String METHOD_ARGUMENT_MESSAGE = "testMethodArgumentMessage";
    private static final String METHOD_ARGUMENT_TYPE_MESSAGE = "does not have the correct type";

    @Mock
    private HttpHeaders httpHeaders;

    @Mock
    private WebRequest webRequest;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BeanPropertyBindingResult beanPropertyBindingResult;

    @Mock
    private FieldError fieldError;

    @Mock
    private MethodArgumentTypeMismatchException methodArgumentTypeMismatchException;

    @Mock
    private ConstraintViolationException constraintViolationException;

    @Mock
    private ConstraintViolationImpl<?> constraintViolation;

    @Mock
    private PathImpl path;

    @Mock
    private NodeImpl node;

    @InjectMocks
    private CustomResponseEntityExceptionHandler customResponseEntityExceptionHandler;

    @Test
    public void testHandleMethodArgumentNotValid() {
        when(fieldError.getField()).thenReturn(FIELD_NAME);
        when(fieldError.getDefaultMessage()).thenReturn(METHOD_ARGUMENT_MESSAGE);
        when(beanPropertyBindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(beanPropertyBindingResult);
        final ResponseEntity<Object> responseEntity =
            customResponseEntityExceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException,
                                                                              httpHeaders,
                                                                              HttpStatus.BAD_REQUEST,
                                                                              webRequest);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(ValidationError.class, responseEntity.getBody().getClass());
        final ValidationError validationError = (ValidationError) responseEntity.getBody();
        assertFalse(validationError.getErrors().isEmpty());
        assertEquals(1, validationError.getErrors().size());
        assertEquals(METHOD_ARGUMENT_MESSAGE, validationError.getErrors().get(0).getMessage());
        assertEquals(FIELD_NAME, validationError.getErrors().get(0).getField());
    }

    @Test
    public void testHandleMethodArgumentTypeMismatch() {
        when(methodArgumentTypeMismatchException.getName()).thenReturn(FIELD_NAME);
        final ResponseEntity<Object> responseEntity =
            customResponseEntityExceptionHandler.handleMethodArgumentTypeMismatch(methodArgumentTypeMismatchException);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(ValidationError.class, responseEntity.getBody().getClass());
        final ValidationError validationError = (ValidationError) responseEntity.getBody();
        assertFalse(validationError.getErrors().isEmpty());
        assertEquals(1, validationError.getErrors().size());
        assertEquals(METHOD_ARGUMENT_TYPE_MESSAGE, validationError.getErrors().get(0).getMessage());
        assertEquals(FIELD_NAME, validationError.getErrors().get(0).getField());
    }

    @Test
    public void testHandleConstraintViolation() {
        when(node.getName()).thenReturn(FIELD_NAME);
        when(path.getLeafNode()).thenReturn(node);
        when(constraintViolation.getPropertyPath()).thenReturn(path);
        when(constraintViolation.getMessage()).thenReturn(CONSTRAINT_MESSAGE);
        when(constraintViolationException.getConstraintViolations()).thenReturn(Collections.singleton(constraintViolation));
        final ResponseEntity<Object> responseEntity =
            customResponseEntityExceptionHandler.handleConstraintViolation(constraintViolationException);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(ValidationError.class, responseEntity.getBody().getClass());
        final ValidationError validationError = (ValidationError) responseEntity.getBody();
        assertFalse(validationError.getErrors().isEmpty());
        assertEquals(1, validationError.getErrors().size());
        assertEquals(CONSTRAINT_MESSAGE, validationError.getErrors().get(0).getMessage());
        assertEquals(FIELD_NAME, validationError.getErrors().get(0).getField());
    }

}
