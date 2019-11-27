package com.pinpoint.listeners;

import com.pinpoint.models.CensusDTO;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
           // System.out.println("In Completion Listener ..");
            List<CensusDTO> results = jdbcTemplate.query("SELECT zip, population, medianAge, " +
                                                "totalMales, totalFemales, totalHouseholds, " + 
                                                "averageHousehold FROM census",
                    (rs,rowNum)->{
                        return new CensusDTO(rs.getString(1), rs.getInt(2),rs.getFloat(3),rs.getInt(4),
                                rs.getInt(5),rs.getInt(6),rs.getFloat(7));
                    }
            );
            results.forEach(System.out::println);
        }
    }
}
