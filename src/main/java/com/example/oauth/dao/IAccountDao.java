package com.example.oauth.dao;

import com.example.oauth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDao extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
