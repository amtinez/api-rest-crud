package com.amtinez.api.rest.crud.dtos;

import com.amtinez.api.rest.crud.annotations.UniqueUserEmail;
import com.amtinez.api.rest.crud.audits.Auditable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.EMAIL_MAX_FIELD_LENGTH;
import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.FIRST_NAME_MAX_FIELD_LENGTH;
import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.LAST_NAME_MAX_FIELD_LENGTH;
import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.PASSWORD_MAX_FIELD_LENGTH;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Auditable<String> {

    private Long id;

    @NotBlank
    @Size(max = FIRST_NAME_MAX_FIELD_LENGTH)
    private String firstName;

    @NotBlank
    @Size(max = LAST_NAME_MAX_FIELD_LENGTH)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = EMAIL_MAX_FIELD_LENGTH)
    @UniqueUserEmail
    private String email;

    @NotBlank
    @Size(max = PASSWORD_MAX_FIELD_LENGTH)
    private String password;

    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime birthdayDate;

    private String lockedBy;

    private LocalDateTime lockedDate;

    private String lockedReason;

    private Boolean enabled;

    private Set<Role> roles;

}
