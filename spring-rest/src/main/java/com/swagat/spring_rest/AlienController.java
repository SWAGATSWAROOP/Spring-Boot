package com.swagat.spring_rest;

import com.swagat.spring_rest.model.Alien;
import com.swagat.spring_rest.repo.AlienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AlienController {
    @Autowired
    AlienRepo repo;

    @GetMapping("aliens")
    @ResponseBody
    public List<Alien> getAliens(){
        List<Alien> aliens = repo.findALl();
        return aliens;
    }

    @GetMapping("alien/{aid}")
    @ResponseBody
    public Alien getAlien(@PathVariable("aid") int aid){
        return repo.findById(aid).orElse(new Alien());
    }
}
