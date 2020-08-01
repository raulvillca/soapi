package com.unlam.soapi.consumers;

import com.unlam.soapi.dtos.EventDTO;
import com.unlam.soapi.models.Event;
import com.unlam.soapi.models.EventType;
import com.unlam.soapi.models.User;
import com.unlam.soapi.repositories.EventRepository;
import com.unlam.soapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class KafkaConsumer {

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    @KafkaListener(topics = "${kafka.string_topic}", groupId = "${kafka.group_id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String dto) {
        LOGGER.info("INICIO - String Topic - Particion : {}");



        LOGGER.info("FIN - String Topic - Particion : {}");
    }

    @KafkaListener(topics = "${kafka.event_topic}", groupId = "${kafka.group_event}", containerFactory = "eventDTOKafkaListenerFactory")
    public void consume(@Nullable EventDTO eventDTO, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOGGER.info("INICIO - Event Topic - Particion: {} - dato: {}", partition, eventDTO.getEventType());

        Event event = new Event();
        if (eventDTO.getUsername() == null) {
            LOGGER.info("No existe usuario");
            return;
        }

        Optional<User> user = this.userRepository.findByUsername(eventDTO.getUsername());
        try {
            event.setEventType(EventType.valueOf(eventDTO.getEventType()));
        } catch (Exception e) {
            event.setEventType(EventType.OTHER);
        }
        event.setDate(new Date());
        event = this.eventRepository.save(event);
        Event finalEvent = event;
        user.ifPresent(u -> {
            u.getEvents().add(finalEvent);
            this.userRepository.save(u);
        });
        LOGGER.info("FIN - Event Topic - Particion : {}");
    }
}
