package com.doni.trafficAI.trafficService.controller;

import com.doni.trafficAI.trafficService.controller.dto.request.CreateEdgeRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.response.FindEdgeResponseDto;
import com.doni.trafficAI.trafficService.service.EdgeService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateEdgeCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateEdgeCommandResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic-service/edge")
@RequiredArgsConstructor
@SecurityRequirement(name = "keycloak")
public class EdgeRestController {
    private final EdgeService edgeService;

    @PostMapping
    public ResponseEntity<FindEdgeResponseDto> createEdge(@RequestBody @Valid CreateEdgeRequestDto requestDto) {
        CreateEdgeCommandResponse response = edgeService.createEdge(new CreateEdgeCommand(
                requestDto.intersectionId1(),
                requestDto.intersectionId2(),
                requestDto.distance()
        ));

        return ResponseEntity.ok(
                new FindEdgeResponseDto(
                        response.id(),
                        response.intersectionId1(),
                        response.intersectionId2(),
                        response.distance()
                )
        );
    }
}
