package com.mercadolibre.mutant.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.mercadolibre.mutant.domain.StatusEnt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StatusRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusRepository.class) ;

    @Autowired
    private DynamoDBMapper mapper;

    public boolean save(StatusEnt statusEnt){
        try {
            mapper.save(statusEnt);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<StatusEnt> findByMutant(int mutant){
        List<StatusEnt> list=null;
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(""+mutant));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("mutant  = :val1").withExpressionAttributeValues(eav);


        return mapper.scan(StatusEnt.class,  scanExpression);

    }

}
