package com.amtinez.api.rest.crud.datas;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author amartinezcerro@gmail.com
 */
@Data
public class UserData {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime birthdayDate;
    private Set<AuthorityData> authorities;

}
