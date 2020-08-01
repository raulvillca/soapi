package com.unlam.soapi.controllers;

import com.unlam.soapi.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/oauth")
public class AuthController {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
        LOGGER.info("INICIO - Registrar usuario");

        LOGGER.info("FIN ´Registrar usuario");
        return ResponseEntity.accepted().body("OK");
    }
}
