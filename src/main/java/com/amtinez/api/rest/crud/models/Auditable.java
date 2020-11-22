package com.amtinez.api.rest.crud.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import static com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Auditable.CREATED_AT_FIELD;
import static com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Auditable.CREATED_BY_FIELD;
import static com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Auditable.DELETED_AT_FIELD;
import static com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Auditable.DELETED_BY_FIELD;
import static com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Auditable.LAST_UPDATED_AT_FIELD;
import static com.amtinez.api.rest.crud.constants.DatabaseConstants.Table.Auditable.LAST_UPDATED_BY_FIELD;

/**
 * @author amartinezcerro@gmail.com
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @CreatedBy
    @Column(name = CREATED_BY_FIELD, updatable = false)
    protected U createdBy;

    @CreatedDate
    @Column(name = CREATED_AT_FIELD, updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = LAST_UPDATED_BY_FIELD)
    protected U lastUpdatedBy;

    @LastModifiedDate
    @Column(name = LAST_UPDATED_AT_FIELD)
    protected LocalDateTime lastUpdatedDate;

    @Column(name = DELETED_BY_FIELD)
    protected U deletedBy;

    @Column(name = DELETED_AT_FIELD)
    protected LocalDateTime deletedDate;

}
