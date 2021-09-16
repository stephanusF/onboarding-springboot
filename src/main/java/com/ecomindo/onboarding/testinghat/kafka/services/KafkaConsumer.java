package com.ecomindo.onboarding.testinghat.kafka.services;

import com.ecomindo.onboarding.testinghat.config.Config;
import com.ecomindo.onboarding.testinghat.dto.HatDTO;
import com.ecomindo.onboarding.testinghat.services.HatsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {

    @Autowired
    Config config;

    @Autowired
    HatsService hatsService;

	@KafkaListener(topics = "#{config.getKafkaTopic()}", groupId = "#{config.getKafkaGroupId()}")
	public void listToTopicHats(String message) {
		System.out.println("Received Message in group hats-listener: " + message);

        try{
            ObjectMapper mapper = new ObjectMapper();
            HatDTO dto = mapper.readValue(message, HatDTO.class);

            hatsService.addHat(dto);
        }catch(Exception e){
            System.out.println("Error processing data from kafka message : " + e.getMessage());
        }
        
	}

}
