package com.pinpoint.config;

import com.pinpoint.listeners.JobListener;
import com.pinpoint.models.Census;
import com.pinpoint.models.CensusDTO;
import com.pinpoint.processors.CensusProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<Census> reader() {
        FlatFileItemReader<Census> reader = new FlatFileItemReader<Census>();
        reader.setResource(new ClassPathResource("2010_Census_Populations_by_Zip_Code.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Census>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "zip", "totalPopulation", "medianAge", 
                                          "totalMales", "totalFemales", "totalHouseholds", 
                                          "averageHousehold" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(Census.class);
            }});
        }});
        return reader;
    }


    @Bean
    public CensusProcessor processor() {
        return new CensusProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CensusDTO> writer() {
        JdbcBatchItemWriter<CensusDTO> writer = new JdbcBatchItemWriter<CensusDTO>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO census (zip, population, medianAge, totalMales, totalFemales," +
          " totalHouseholds, averageHousehold) VALUES (:zip, :population, :medianAge, :totalMales," +
          " :totalFemales, :totalHouseholds, :averageHousehold)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Census, CensusDTO> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
