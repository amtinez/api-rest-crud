package com.amtinez.api.rest.crud.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author amartinezcerro@gmail.com
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authority extends Auditable<String> {

    private Long id;
    private String name;

}
