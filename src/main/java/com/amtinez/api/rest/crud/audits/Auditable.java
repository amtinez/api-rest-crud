package com.amtinez.api.rest.crud.audits;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Getter
@Setter
public abstract class Auditable<U> {

    protected U createdBy;
    protected LocalDateTime createdDate;
    protected U lastUpdatedBy;
    protected LocalDateTime lastUpdatedDate;

}
