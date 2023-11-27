package com.simplebank.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {
/*
returning null means that the current user is unknown. This is the default implementation of the AuditorAware interface.
If you want to return the current user, you can use the Spring Security to get the currently logged in user.
reurn current user
* */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS-MS");
    }
}
