package com.mercadolibre.mutant.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;


/**

 * Clase modelo BD , almacenamiento dna y es mutante

 * @author: Edison A. Alvear Pabon

 * @version: 18/03/2022/

 */

@DynamoDBDocument
@DynamoDBTable(tableName = "prueba_status" )
public class StatusEnt {

    @JsonProperty("dna")
    String dna;
    @JsonProperty("mutant")
    String mutant;


    @DynamoDBHashKey(attributeName = "dna")
    public String getDna() {
        return dna;
    }
    public void setDna(String dna) {
        this.dna = dna;
    }

    @DynamoDBAttribute
    public String getMutant() {
        return mutant;
    }

    public void setMutant(String mutant) {
        this.mutant = mutant;
    }
}
