package com.doni.trafficAI.trafficService.controller;

import com.doni.trafficAI.trafficService.controller.dto.request.CreateRoadRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.response.FindRoadResponseDto;
import com.doni.trafficAI.trafficService.service.RoadService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateRoadCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateRoadCommandResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic-service/road")
@RequiredArgsConstructor
@SecurityRequirement(name = "keycloak")
public class RoadRestController {
    private final RoadService roadService;

    @PostMapping
    public ResponseEntity<FindRoadResponseDto> createRoad(@RequestBody @Valid CreateRoadRequestDto requestDto) {
        CreateRoadCommandResponse response = roadService.createRoad(
                new CreateRoadCommand(requestDto.name())
        );
        return ResponseEntity.ok(new FindRoadResponseDto(
                response.id(),
                response.name()
        ));
    }
}
