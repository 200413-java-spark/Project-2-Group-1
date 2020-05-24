package com.github.sparkWeb.controllers;


import com.github.sparkWeb.models.Calculation;
import com.github.sparkWeb.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @GetMapping("/calculations")
    public List<Calculation> getCalculations() {
        return calculationService.getCalculations();
    }

}
