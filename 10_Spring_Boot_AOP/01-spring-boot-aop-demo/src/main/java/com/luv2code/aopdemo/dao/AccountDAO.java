package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

public interface AccountDAO {
    void addAccount();

    // Adding another method to test out the return type
    // boolean addAnotherAccount(int id);

    // void addActualAccount(Account account);
    void addActualAccount(Account account, boolean vipFlag);

    void addCheck(int id, String name, boolean vipFlag);
}
