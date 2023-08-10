package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficServiceImpl implements TrafficService{
    @Override
    public String getUpdate() {
        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // return the update
        return "It will be Heavy Traffic this morning :)";
    }

    @Override
    public String getUpdate(boolean tripWire) {
        if (tripWire)   {
            throw new RuntimeException("Major Accident! Highway is closed");
        }
        return getUpdate();
    }
}
