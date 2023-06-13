package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
// Here we're implementing Bean Scopes, so we're specifying what the scope of the bean would be.
@Component
public class CricketCoach implements Coach{
    public CricketCoach()   {
        System.out.println("In constructor : " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice breathing exercise for at least 15 minutes !!! (Cricket Coach)";
    }
}
