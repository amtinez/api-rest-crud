package com.amtinez.api.rest.crud.dtos;

import com.amtinez.api.rest.crud.annotations.UniqueUserEmail;
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
import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.LOCKED_BY_MAX_FIELD_LENGTH;
import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.LOCKED_REASON_MAX_FIELD_LENGTH;
import static com.amtinez.api.rest.crud.constants.ValidationConstants.User.PASSWORD_MAX_FIELD_LENGTH;

/**
 * @author amartinezcerro@gmail.com
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
    private LocalDateTime birthdayDate;

    @Size(max = LOCKED_BY_MAX_FIELD_LENGTH)
    private String lockedBy;

    private LocalDateTime lockedDate;

    @Size(max = LOCKED_REASON_MAX_FIELD_LENGTH)
    private String lockedReason;

    private Boolean enabled;

    private Set<Authority> authorities;

}
