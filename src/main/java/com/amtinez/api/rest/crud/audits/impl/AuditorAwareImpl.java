package com.amtinez.api.rest.crud.audits.impl;

import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;
import com.amtinez.api.rest.crud.utils.UserUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * AuditorAwareImpl is the class that will be in charge of returning the full name of the user that is connected in order to keep track of
 * the different modifications that are made to the database.
 *
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        final UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Objects.nonNull(userDetails) ? Optional.of(UserUtils.getFullName(userDetails)) : Optional.empty();
    }

}
