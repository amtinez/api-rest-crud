package com.amtinez.api.rest.crud.dtos;

import com.amtinez.api.rest.crud.audits.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Auditable<String> {

    private Long id;
    private String name;
    private Set<User> users;

}
