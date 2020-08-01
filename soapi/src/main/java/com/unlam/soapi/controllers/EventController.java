package com.unlam.soapi.controllers;

import com.unlam.soapi.dtos.EventDTO;
import com.unlam.soapi.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "/string", method = RequestMethod.POST)
    public ResponseEntity createString(@RequestBody String dato, Principal principal) {
        kafkaProducer.createString("No hay usuario", dato);
        return ResponseEntity.accepted().body("OK");
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody EventDTO dto, Principal principal) {
        kafkaProducer.createEvent("No hay usuario", dto);
        return ResponseEntity.accepted().body("OK");
    }
}
