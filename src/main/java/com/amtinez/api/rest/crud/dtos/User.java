package com.amtinez.api.rest.crud.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author amartinezcerro@gmail.com
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends Auditable<String> {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime birthdayDate;
    private Set<Authority> authorities;

}
