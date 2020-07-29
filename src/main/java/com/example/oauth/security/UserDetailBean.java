package com.example.oauth.security;

import com.example.oauth.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserDetailBean extends Account implements UserDetails {
    private Set<GrantedAuthority> authors;

    public void setAuthors(Set<GrantedAuthority> authors) {
        this.authors = authors;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authors;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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

    public UserDetailBean() {
    }
}
