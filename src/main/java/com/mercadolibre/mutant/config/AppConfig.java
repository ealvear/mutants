package com.mercadolibre.mutant.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
        (basePackages = "io.pratik.dynamodbspring.repositories")
public class AppConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AWSCredentialsProvider credentials =
                new ProfileCredentialsProvider("pratikpoc");
        AmazonDynamoDB amazonDynamoDB
                = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(credentials)
                .build();

        return amazonDynamoDB;
    }

}