package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import org.mapstruct.Mapper;

/**
 * @author amartinezcerro@gmail.com
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    Authority authorityModelToAuthority(final AuthorityModel authorityModel);

    AuthorityModel authorityToAuthorityModel(final Authority authority);

}
