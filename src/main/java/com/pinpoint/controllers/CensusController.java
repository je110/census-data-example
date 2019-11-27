package com.pinpoint.controllers;

import java.util.List;

import com.pinpoint.repositories.CensusRepository;
import com.pinpoint.models.Census;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/census")
public class CensusController {

	@Autowired
	CensusRepository censusRepository;

	  @GetMapping(value = "/population")
    public List<Census> getByPopulation(@RequestParam int min, @RequestParam int max) {
		  return censusRepository.findByPopulation(min, max);
    }
    
    @GetMapping(value = "/medianAge")
    public List<Census> getByMedianAge(@RequestParam float min, @RequestParam float max) {
		  return censusRepository.findByMedianAge(min, max);
    }
    
    @GetMapping(value = "/mostPopulous")
    public List<String> getByMostPopulous(@RequestParam int amount) {
		  return censusRepository.findMostPopulous(amount);
    }
    
    @GetMapping(value = "/mostlyFemale")
    public List<Census> getByMostlyFemale() {
      return censusRepository.findMostlyFemale();
    }

}