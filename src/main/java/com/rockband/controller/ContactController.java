package com.rockband.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockband.dto.ContactMessage;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {
@PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody ContactMessage message) {
        System.out.println("📩 Contact message received:");
        System.out.println("From: " + message.getName() + " <" + message.getEmail() + ">");
        System.out.println("Message: " + message.getMessage());

        return ResponseEntity.ok("Message received");
    }
}
