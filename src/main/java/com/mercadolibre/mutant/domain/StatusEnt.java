package com.mercadolibre.mutant.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBDocument
@DynamoDBTable(tableName = "prueba_status" )
public class StatusEnt {

    @JsonProperty("dna")
    String dna;
    @JsonProperty("mutant")
    int mutant;


    @DynamoDBHashKey(attributeName = "dna")
    public String getDna() {
        return dna;
    }
    public void setDna(String dna) {
        this.dna = dna;
    }

    @DynamoDBAttribute
    public int getMutant() {
        return mutant;
    }

    public void setMutant(int mutant) {
        this.mutant = mutant;
    }
}
