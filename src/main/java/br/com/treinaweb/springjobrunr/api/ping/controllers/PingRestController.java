package br.com.treinaweb.springjobrunr.api.ping.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
public class PingRestController {

    @GetMapping
    public Map<String, String> ping() {
        return Map.of("message", "pong");
    }
    
}
