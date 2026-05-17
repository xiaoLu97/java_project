package com.mvc.service;

import com.mvc.entity.Account;

public interface AccountService {
    public Account findByUsername(String username);
}