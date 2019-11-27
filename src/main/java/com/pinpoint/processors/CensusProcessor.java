package com.pinpoint.processors;

import com.pinpoint.models.Census;
import com.pinpoint.models.CensusDTO;

import org.springframework.batch.item.ItemProcessor;

public class CensusProcessor implements ItemProcessor<Census, CensusDTO> {

    @Override
    public CensusDTO process(final Census census) throws Exception {
        System.out.println("Transforming Census(s) to CensusDTO(s)..");
        final CensusDTO censusDto = new CensusDTO(census.getZip(), census.getPopulation(),
                census.getMedianAge(), census.getTotalMales(), census.getTotalFemales(), 
                census.getTotalHouseholds(), census.getAverageHousehold());

        return censusDto;
    }

}
