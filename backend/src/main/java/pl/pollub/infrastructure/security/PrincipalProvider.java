package pl.pollub.infrastructure.security;

import pl.pollub.user.User;

public interface PrincipalProvider {
    AppPrincipal getPrincipal();

    User getLoggedUser();

    boolean isUserLogged();

    String getLoggedUsername();
}
