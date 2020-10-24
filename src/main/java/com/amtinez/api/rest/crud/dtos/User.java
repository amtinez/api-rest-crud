package com.amtinez.api.rest.crud.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author amartinezcerro@gmail.com
 */
@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime birthdayDate;
    private Set<Authority> authorities;

}
