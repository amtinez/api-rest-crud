package com.amtinez.api.rest.crud.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class ResponseEntityUtils {

    /**
     * Returns a ResponseEntity based on the number of modified entities
     *
     * @param affectedEntities number of the affected entities
     * @return HttpStatus 200 if any entity has been modified or HttpStatus 404 otherwise
     */
    public static ResponseEntity<Void> getResponseEntityByAffectedEntities(final int affectedEntities) {
        return NumberUtils.INTEGER_ONE.equals(affectedEntities) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private ResponseEntityUtils() {
    }

}
