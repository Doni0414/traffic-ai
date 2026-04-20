package com.doni.trafficAI.trafficService.controller;

import com.doni.trafficAI.trafficService.controller.dto.request.CreateIntersectionRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.response.FindIntersectionResponseDto;
import com.doni.trafficAI.trafficService.service.IntersectionService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateIntersectionCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateIntersectionCommandResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traffic-service/intersection")
@SecurityRequirement(name = "keycloak")
public class IntersectionRestController {
    private final IntersectionService intersectionService;

    @PostMapping
    public ResponseEntity<FindIntersectionResponseDto> createIntersection(@RequestBody @Valid CreateIntersectionRequestDto requestDto) {
        CreateIntersectionCommandResponse response = intersectionService.createIntersection(new CreateIntersectionCommand(
                requestDto.name(),
                requestDto.eastWestRoadId(),
                requestDto.southNorthRoadId(),
                requestDto.longitude(),
                requestDto.latitude()
        ));

        return ResponseEntity.ok(
                new FindIntersectionResponseDto(
                        response.intersecrtionId(),
                        response.name(),
                        response.eastWestRoadId(),
                        response.southNorthRoadId(),
                        response.longitude(),
                        response.latitude()
                )
        );
    }
}
