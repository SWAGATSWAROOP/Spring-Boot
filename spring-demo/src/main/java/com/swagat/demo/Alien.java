package com.swagat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //Using this I am telling spring to initialize it
public class Alien {

    @Autowired
    Laptop Lap; //For initializing object without creating spring manages this.
    public void code(){
        System.out.println("I am coding");
        Lap.compile();
    }
}