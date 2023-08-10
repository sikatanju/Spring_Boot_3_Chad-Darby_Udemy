package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository // This "@Repository" marks this class for component scanning
public class AccountDAOImpl implements AccountDAO{

    @Override
    public void addAccount()    {
        System.out.println(getClass() + " : adding account :)\n");
    }

    @Override
    public void addActualAccount(Account account, boolean vipFlag) {
        System.out.println("From Actual Account class with one more parameters (boolean) named vipFlag : " + vipFlag);
    }

    @Override
    public void addCheck(int id, String name, boolean vipFlag) {
        System.out.println("For Any number of parameters of any type :)");
    }
}
