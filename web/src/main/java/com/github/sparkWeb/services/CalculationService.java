package com.github.sparkWeb.services;

import com.github.sparkWeb.models.Calculation;
import com.github.sparkWeb.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;


    @PostConstruct
    public void init(){
        calculationRepository.saveAll((Stream.of(new Calculation(1,45.55),new Calculation(2,2.345)).collect(Collectors.toList())));
    }

    public List<Calculation> getCalculations(){
        return calculationRepository.findAll();
    }
}
