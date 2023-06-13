package com.luv2code.springboot.demo.mycoolapp.rest;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // Inject properties for : coach.name and my.name and my.age
    @Value("${coach.name}")
    private String coachName;

    @Value("${my.name}")
    private String myName;

    // Expose new endpoint for myteaminfo
    @GetMapping("/myteaminfo")
    public String getTeamInfor()    {
        return "Coach : " + coachName + " & My Name : " + myName;
    }

    // Expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello()    {
        return "Hello World !";
    }
    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout()  {
        return "Run a hard 5k";
    }
}
