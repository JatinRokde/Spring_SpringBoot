package com.jr.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public boolean addAccount() {
        System.out.println(getClass() + " DOING MY DB WORK: ADDING AN ACCOUNT!");
        return true;
    }
}
