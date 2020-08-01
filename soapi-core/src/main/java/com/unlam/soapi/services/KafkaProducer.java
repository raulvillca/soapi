package com.unlam.soapi.services;

import com.unlam.soapi.dtos.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, EventDTO> eventDTOKafkaListenerContainerFactory;
    @Value(value = "${kafka.event_topic}")
    private String eventTopic;
    @Value(value = "${kafka.string_topic}")
    private String stringTopic;

    public void createString(String username, String eventType) {

        this.kafkaTemplate.send(stringTopic, eventType);
    }

    public void createEvent(String username, EventDTO eventType) {

        this.eventDTOKafkaListenerContainerFactory.send(eventTopic, eventType);
    }
}
