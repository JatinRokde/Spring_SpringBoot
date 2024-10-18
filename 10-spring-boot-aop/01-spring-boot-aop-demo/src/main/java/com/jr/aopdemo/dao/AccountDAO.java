package com.jr.aopdemo.dao;

import com.jr.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);
}
