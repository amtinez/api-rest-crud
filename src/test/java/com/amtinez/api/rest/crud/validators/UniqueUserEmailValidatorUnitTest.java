package com.amtinez.api.rest.crud.validators;

import com.amtinez.api.rest.crud.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

/**
 * @author amartinezcerro@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class UniqueUserEmailValidatorUnitTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private UserService userService;

    @InjectMocks
    private UniqueUserEmailValidator uniqueUserEmailValidator;

    @Test
    public void testIsValid() {
        Mockito.when(userService.existsUserEmail(Mockito.anyString())).thenReturn(Boolean.FALSE);
        Assert.assertTrue(uniqueUserEmailValidator.isValid(StringUtils.EMPTY, constraintValidatorContext));
    }

    @Test
    public void testIsNotValid() {
        Mockito.when(userService.existsUserEmail(Mockito.anyString())).thenReturn(Boolean.TRUE);
        Assert.assertFalse(uniqueUserEmailValidator.isValid(StringUtils.EMPTY, constraintValidatorContext));
    }

}
