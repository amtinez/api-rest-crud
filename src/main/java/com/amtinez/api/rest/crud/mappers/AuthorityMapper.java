package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import org.mapstruct.Mapper;

import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;

/**
 * @author amartinezcerro@gmail.com
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL)
public interface AuthorityMapper {

    Authority authorityModelToAuthority(final AuthorityModel authorityModel);

    AuthorityModel authorityToAuthorityModel(final Authority authority);

}
