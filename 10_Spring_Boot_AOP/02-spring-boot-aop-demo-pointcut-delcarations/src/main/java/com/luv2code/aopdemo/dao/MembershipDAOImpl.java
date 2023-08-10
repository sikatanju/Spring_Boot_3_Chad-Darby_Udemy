package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository // This "@Repository" marks this class for component scanning
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public void addAccount()    {
        System.out.println(getClass() + " : adding a membership account & doing some weird stuff :)\n");
    }
}
