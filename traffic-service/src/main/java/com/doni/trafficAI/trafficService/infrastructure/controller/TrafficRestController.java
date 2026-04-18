package com.doni.trafficAI.trafficService.infrastructure.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic-service/traffic")
@SecurityRequirement(name = "keycloak")
public class TrafficRestController {

    @GetMapping
    public String traffic() {
        return "traffic";
    }

    @GetMapping("/public")
    public String publicTraffic() {
        return "public";
    }

    @GetMapping("/authenticated")
    public String authenticatedTraffic() {
        return "authenticated";
    }
}
