package com.example.oauth.security;

import com.example.oauth.dao.IAccountDao;
import com.example.oauth.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IAccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.findByUsername(username);
        if (account == null) {
            return null;
        }
        UserDetailBean userDetailBean = new UserDetailBean();
        userDetailBean.setId(account.getId());
        userDetailBean.setUsername(account.getUsername());
        userDetailBean.setPassword(account.getPassword());
        userDetailBean.setType(account.getType());
        userDetailBean.setEnabled(account.isEnabled());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add((GrantedAuthority) account::getType);
        userDetailBean.setAuthors(authorities);
        return userDetailBean;
    }
}
