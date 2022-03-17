package com.mercadolibre.mutant.repository;


import com.mercadolibre.mutant.domain.StatusEnt;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;


import java.util.List;


//@Configuration
//@EnableDynamoDBRepositories(basePackages = "com.mercadolibre.mutant.repository.*", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))

    @EnableScan
    public interface IStatusRepository extends CrudRepository<StatusEnt, String> {
        List<StatusEnt> findByMutant(int mutant);
    }


