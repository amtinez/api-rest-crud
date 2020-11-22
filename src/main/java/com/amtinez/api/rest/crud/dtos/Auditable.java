package com.amtinez.api.rest.crud.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author amartinezcerro@gmail.com
 */
@Getter
@Setter
public abstract class Auditable<U> {

    protected U createdBy;
    protected LocalDateTime createdDate;
    protected U lastUpdatedBy;
    protected LocalDateTime lastUpdatedDate;
    protected U deletedBy;
    protected LocalDateTime deletedDate;

}
