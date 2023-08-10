package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount();

    void addActualAccount(Account account, boolean vipFlag);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);

    void addCheck(int id, String name, boolean vipFlag);

    public String getName();

    public void setName(String name);

    public String getAddress();

    public void setAddress(String address);
}
