package com.swagat.spring_rest;

import com.swagat.spring_rest.model.Alien;
import com.swagat.spring_rest.repo.AlienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlienController {
    @Autowired
    AlienRepo repo;

    @GetMapping("aliens")
    public List<Alien> getAliens(){
        List<Alien> aliens = repo.findALl();
        return aliens;
    }

    @GetMapping(path = "alien/{aid}",produces = "application/xml") //Returns what type of response
    public Alien getAlien(@PathVariable("aid") int aid){
        return repo.findById(aid).orElse(new Alien());
    }

//    Accept form data
    @PostMapping(path = "alien",consumes = {"application/json"}) //This makes only accept json
//    @RequestBody used for converting json body to Java object format
    public Alien saveAlien(@RequestBody Alien a){
        repo.save(a);
        return a;
    }
}
