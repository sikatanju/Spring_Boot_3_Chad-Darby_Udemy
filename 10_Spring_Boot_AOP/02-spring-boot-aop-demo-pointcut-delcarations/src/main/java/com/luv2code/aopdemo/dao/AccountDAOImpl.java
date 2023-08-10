package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // This "@Repository" marks this class for component scanning
public class AccountDAOImpl implements AccountDAO {

    // Declaring couple of variable for getter/setter
    private String name;
    private String address;

    @Override
    public void addAccount()    {
        System.out.println(getClass() + " : adding account :)\n");
    }

    @Override
    public void addActualAccount(Account account, boolean vipFlag) {
        System.out.println("From Actual Account class with one more parameters (boolean) named vipFlag : " + vipFlag + "\n");
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // for academic purposes... simulate an exception

        if (tripWire)   {
            throw new RuntimeException("No Soup for you !!!");
        }

        List<Account> accounts = new ArrayList<>();

        // Create sample accounts
        Account account1 = new Account("John Wick", "Legendary");
        Account account2 = new Account("Mike Hussy", "World-class");
        Account account3 = new Account("Luka", "Gold");

        // Add them to our accounts list.
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        return accounts;
    }

    @Override
    public void addCheck(int id, String name, boolean vipFlag) {
        System.out.println("For Any number of parameters of any type :)");
    }

    public String getName() {
        System.out.println(getClass() + " : in getter/setter() :)\n");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " : in getter/setter() :)\n");
        this.name = name;
    }

    public String getAddress() {
        System.out.println(getClass() + " : in getter/setter() :)\n");
        return address;
    }

    public void setAddress(String address) {
        System.out.println(getClass() + " : in getter/setter() :)\n");
        this.address = address;
    }
}
