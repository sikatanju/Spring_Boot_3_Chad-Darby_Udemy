package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;
    private Coach myCoach2;
    // Cricket Coach is marked primary.
    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theCoach2)   {

        System.out.println("In constructor : " + getClass().getSimpleName());
        myCoach = theCoach;
        myCoach2 = theCoach2;
    }

    @GetMapping("/check")
    public String check()   {
        return "Comparing Beans : myCoach == anotherCoach : " + (myCoach2 == myCoach);
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/")
    public String any()    {
        return "Hello, My name is Sika";
    }
}
