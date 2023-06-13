package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach    {
    @Override
    public String getDailyWorkout() {
        return "I'm the Track Coach";
    }
}
