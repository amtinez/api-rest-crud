package com.amtinez.api.rest.crud.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponseEntityUtilsUnitTest {

    @Test
    public void testGetResponseEntityByAffectedEntitiesOneEntities() {
        assertEquals(HttpStatus.OK, ResponseEntityUtils.getResponseEntityByAffectedEntities(1).getStatusCode());
    }

    @Test
    public void testGetResponseEntityByAffectedEntitiesNoEntities() {
        assertEquals(HttpStatus.NOT_FOUND, ResponseEntityUtils.getResponseEntityByAffectedEntities(0).getStatusCode());
    }

}
