package me.latanks.ecommerce_api.security;

import me.latanks.ecommerce_api.models.User;
import me.latanks.ecommerce_api.models.enums.Roles;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpringSecurity implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(User user, Set<Roles> roles) {
        this.user = user;
        this.authorities = roles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public boolean hasRole(Roles role) {
        return getAuthorities().contains(new SimpleGrantedAuthority(role.getDescription()));
    }

}
