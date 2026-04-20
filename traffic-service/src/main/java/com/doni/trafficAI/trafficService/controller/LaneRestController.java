package com.doni.trafficAI.trafficService.controller;

import com.doni.trafficAI.trafficService.controller.dto.request.CreateLaneRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.response.FindLaneResponseDto;
import com.doni.trafficAI.trafficService.service.LaneService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateLaneCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateLaneCommandResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic-service/lane")
@RequiredArgsConstructor
@SecurityRequirement(name = "keycloak")
public class LaneRestController {
    private final LaneService laneService;

    @PostMapping
    public ResponseEntity<FindLaneResponseDto> createLane(@RequestBody @Valid CreateLaneRequestDto requestDto) {
        CreateLaneCommandResponse response = laneService.createLane(new CreateLaneCommand(
                requestDto.edgeId(),
                requestDto.direction(),
                requestDto.order()
        ));

        return ResponseEntity.ok(
                new FindLaneResponseDto(
                        response.id(),
                        response.edgeId(),
                        response.direction(),
                        response.order()
                )
        );
    }
}
