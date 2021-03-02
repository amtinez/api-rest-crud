package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;
import com.amtinez.api.rest.crud.utils.RoleUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.ACCOUNT_NOT_EXPIRED_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.ACCOUNT_NOT_LOCKED_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.AUTHORITIES_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.CREDENTIALS_NOT_EXPIRED_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.IS_ACCOUNT_NOT_EXPIRED;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.IS_ACCOUNT_NOT_LOCKED;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.IS_CREDENTIALS_NOT_EXPIRED;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.LAST_ACCESS_DATE_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.LAST_PASSWORD_UPDATE_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.LOCKED_DATE_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.ROLES_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.UserDetails.ROLES_TO_AUTHORITIES;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.INACTIVE_LIFETIME_MONTHS;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.PASSWORD_LIFETIME_MONTHS;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDetailsMapper {

    @Mapping(target = AUTHORITIES_PROPERTY, source = ROLES_PROPERTY, qualifiedByName = ROLES_TO_AUTHORITIES)
    @Mapping(target = ACCOUNT_NOT_LOCKED_PROPERTY, source = LOCKED_DATE_PROPERTY, qualifiedByName = IS_ACCOUNT_NOT_LOCKED)
    @Mapping(target = ACCOUNT_NOT_EXPIRED_PROPERTY, source = LAST_ACCESS_DATE_PROPERTY, qualifiedByName = IS_ACCOUNT_NOT_EXPIRED)
    @Mapping(target = CREDENTIALS_NOT_EXPIRED_PROPERTY,
             source = LAST_PASSWORD_UPDATE_PROPERTY,
             qualifiedByName = IS_CREDENTIALS_NOT_EXPIRED)
    UserDetailsImpl userModelToUserDetails(final UserModel userModel);

    @Named(ROLES_TO_AUTHORITIES)
    static Collection<SimpleGrantedAuthority> rolesToAuthorities(final Set<RoleModel> roles) {
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(RoleUtils.getPrefixedName(role)))
                    .collect(Collectors.toList());
    }

    @Named(IS_ACCOUNT_NOT_LOCKED)
    static boolean isAccountNonLocked(final LocalDateTime lockedDate) {
        return lockedDate == null;
    }

    @Named(IS_ACCOUNT_NOT_EXPIRED)
    static boolean isAccountNonExpired(final LocalDateTime lastAccessDate) {
        return lastAccessDate != null && ChronoUnit.MONTHS.between(lastAccessDate, LocalDateTime.now()) < INACTIVE_LIFETIME_MONTHS;
    }

    @Named(IS_CREDENTIALS_NOT_EXPIRED)
    static boolean isCredentialsNonExpired(final LocalDateTime lastPasswordUpdateDate) {
        return lastPasswordUpdateDate != null
            && ChronoUnit.MONTHS.between(lastPasswordUpdateDate, LocalDateTime.now()) < PASSWORD_LIFETIME_MONTHS;
    }

}
