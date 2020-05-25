package com.github.sparkWeb.controllers;

import com.github.sparkWeb.models.Calculation;
import com.github.sparkWeb.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class StaticController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }


}