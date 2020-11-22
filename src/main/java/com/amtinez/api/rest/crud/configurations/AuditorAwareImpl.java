package com.amtinez.api.rest.crud.configurations;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author amartinezcerro@gmail.com
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /* TODO: FUTURE IMPLEMENTATION WITH THE USER LOGGED
        final UserModel currentUser = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Objects.nonNull(currentUser) ? Optional.of(UserUtils.getFullName(currentUser)) : Optional.empty();
        */
        return Optional.of("Auditor Test User");
    }

}
