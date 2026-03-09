package com.doni.trafficAI.trafficService.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic-service/traffic")
public class TrafficRestController {

    @GetMapping
    public String traffic() {
        return "traffic";
    }
}
