package com.jr.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addMemberAccount() {
        System.out.println(getClass() + " DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT!");
    }
}