package com.doni.trafficAI.trafficService.controller;

import com.doni.trafficAI.trafficService.controller.dto.request.CreateTrafficDataRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.request.FindTrafficsDataRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.response.FindTrafficDataResponseDto;
import com.doni.trafficAI.trafficService.controller.dto.response.FindTrafficsDataResponseDto;
import com.doni.trafficAI.trafficService.service.TrafficService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateTrafficDataCommand;
import com.doni.trafficAI.trafficService.service.dto.command.FindTrafficsDataCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateTrafficDataCommandResponse;
import com.doni.trafficAI.trafficService.service.dto.response.FindTrafficsDataCommandResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic-service/traffic")
@RequiredArgsConstructor
@SecurityRequirement(name = "keycloak")
public class TrafficRestController {
    private final TrafficService trafficService;

    @PostMapping
    public ResponseEntity<FindTrafficDataResponseDto> createTraffic(@RequestBody @Valid CreateTrafficDataRequestDto requestDto) {
        CreateTrafficDataCommandResponse response = trafficService.createTraffic(new CreateTrafficDataCommand(
                requestDto.laneId(),
                requestDto.queueLength(),
                requestDto.density(),
                requestDto.avgSpeed()
        ));

        return ResponseEntity.ok((new FindTrafficDataResponseDto(
                response.id(),
                response.timestamp(),
                response.laneId(),
                response.queueLength(),
                response.density(),
                response.avgSpeed()
        )));
    }

    @GetMapping
    public ResponseEntity<FindTrafficsDataResponseDto> findAll(@ModelAttribute FindTrafficsDataRequestDto filter) {
        FindTrafficsDataCommandResponse response = trafficService.findAll(new FindTrafficsDataCommand(
                filter.intersectionId(),
                filter.laneId(),
                filter.from(),
                filter.to()
        ));

        return ResponseEntity.ok(
                new FindTrafficsDataResponseDto(
                        response.trafficsData().stream()
                                .map(trafficData -> new FindTrafficDataResponseDto(
                                        trafficData.id(),
                                        trafficData.timestamp(),
                                        trafficData.laneId(),
                                        trafficData.queueLength(),
                                        trafficData.density(),
                                        trafficData.avgSpeed()
                                ))
                                .toList()
                )
        );
    }
}
