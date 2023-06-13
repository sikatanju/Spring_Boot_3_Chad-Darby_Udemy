package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    public FootballCoach()  {
        System.out.println("In constructor : " + getClass().getSimpleName());
    }

    @PostConstruct
    public void doMyStartupStuff()  {
        System.out.println("In PostConstruct Method :)  -- " + getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanUp()   {
        System.out.println("In PreDestroy Method :(  -- " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Run like Messi or Ronaldo :)";
    }
}
