package com.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mvc.entity.Account;
import com.mvc.mapper.AccountMapper;
import com.mvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.accountMapper.selectOne(queryWrapper);
    }
}
