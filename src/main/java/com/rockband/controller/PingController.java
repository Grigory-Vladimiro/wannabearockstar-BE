package com.rockband.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ping")
public class PingController {
    @GetMapping
    public String ping() {
        return "pong";
    }
}
