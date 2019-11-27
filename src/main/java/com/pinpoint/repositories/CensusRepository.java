package com.pinpoint.repositories;

import java.util.List;

import com.pinpoint.models.Census;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CensusRepository extends JpaRepository<Census, Integer> {
    @Query("SELECT zip FROM Census c WHERE c.population >= (:min) AND c.population <= (:max)")
    public List<Census> findByPopulation(@Param("min") int min, @Param("max") int max);

    @Query("SELECT zip FROM Census c WHERE c.medianAge >= (:min) AND c.medianAge <= (:max)")
    public List<Census> findByMedianAge(@Param("min") float min, @Param("max") float max);

    @Query(value = "SELECT zip FROM census c ORDER BY c.population DESC LIMIT :amount", 
           nativeQuery=true)
    public List<String> findMostPopulous(@Param("amount") int amount);

    @Query("SELECT zip FROM Census c WHERE c.totalFemales > c.totalMales " + 
           "ORDER BY c.totalFemales - c.totalMales")
    public List<Census> findMostlyFemale();
}