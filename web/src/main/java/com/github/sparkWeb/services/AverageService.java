package com.github.sparkWeb.services;

import com.github.sparkWeb.models.Averages;
import com.github.sparkWeb.repositories.AveragesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageService {

    @Autowired
    private AveragesRepository averagesRepository;


//    @PostConstruct
//    public void init(){
//        calculationRepository.saveAll((Stream.of(new Calculation(1,45.55),new Calculation(2,2.345)).collect(Collectors.toList())));
//    }

    public List<Averages> getAverages() {
        return averagesRepository.findAll();
    }
}
