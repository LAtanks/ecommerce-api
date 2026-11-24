package me.latanks.ecommerce_api.utils;

import me.latanks.ecommerce_api.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {

    private static final AuthenticationUtil INSTANCE = new AuthenticationUtil();
    public static AuthenticationUtil getInstance() {
        return INSTANCE;
    }

    public User getUser() {
        return getAuthentication().getPrincipal() instanceof User ? (User)getAuthentication().getPrincipal() : null;
    }

    public String getUsername() {
        return getAuthentication().getName();
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
