package com.github.sparkWeb.controllers;


import com.github.sparkWeb.models.Averages;
import com.github.sparkWeb.services.AverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private AverageService averageService;

    @GetMapping("/averages")
    public List<Averages> getAverages() {
        return averageService.getAverages();
    }

}
