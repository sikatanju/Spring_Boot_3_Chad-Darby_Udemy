package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;
    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach theCoach)   {
        myCoach = theCoach;
    }

//    If we were to do this for any other Coach, like : FootballCoach, BaseballCoach, etc.
//    @Autowired
//    public DemoController(@Qualifier("baseballCoach") Coach theCoach)   {
//        or public DemoController(@Qualifier("footballCoach") Coach theCoach)   {
//        myCoach = theCoach;
//    }

//    ********************

//    We could use any name for the setter method
//    public void anyName(Coach theCoach)    {
//        myCoach = theCoach;
//    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/")
    public String any()    {
        return "Hello, My name is Sika";
    }
}
