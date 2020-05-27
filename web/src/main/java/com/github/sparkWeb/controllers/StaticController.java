package com.github.sparkWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "about")
    public String about() {
        return "about";
    }


    @RequestMapping(value = "state-averages")
    public String stateAverages() {
        return "state-averages";
    }

}