package com.amtinez.api.rest.crud.configurations;

import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.utils.UserUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author amartinezcerro@gmail.com
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        final UserModel currentUser = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Objects.nonNull(currentUser) ? Optional.of(UserUtils.getFullName(currentUser)) : Optional.empty();
    }

}
