package com.example.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public class AuthenticationProviderImpl implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UserDetails account = userDetailsService.loadUserByUsername(username);
        if (account == null || !password.equals(account.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误!");
        }
        if (!account.isEnabled()) {
            throw new DisabledException("用户已停用!");
        }
        Collection<? extends GrantedAuthority> authorities = account.getAuthorities();
        return new UsernamePasswordAuthenticationToken(account, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
