package com.doni.trafficAI.trafficService.controller;

import com.doni.trafficAI.trafficService.controller.dto.request.CalculateSignalPhaseRequestDto;
import com.doni.trafficAI.trafficService.controller.dto.response.SignalPhaseCalculationResponseDto;
import com.doni.trafficAI.trafficService.service.SignalPhaseCalculatorService;
import com.doni.trafficAI.trafficService.service.dto.command.CalculateSignalPhaseCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CalculateSignalPhaseCommandResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traffic-service/calculate")
@SecurityRequirement(name = "keycloak")
public class SignalPhaseCalculatorRestController {
    private final SignalPhaseCalculatorService signalPhaseCalculatorService;

    @PostMapping("/ai")
    public ResponseEntity<SignalPhaseCalculationResponseDto> calculateSignalPhase(@RequestBody @Valid CalculateSignalPhaseRequestDto requestDto) {
        CalculateSignalPhaseCommandResponse response = signalPhaseCalculatorService.calculateGreen(new CalculateSignalPhaseCommand(
                requestDto.intersectionId()
        ));
        return ResponseEntity.ok(
                new SignalPhaseCalculationResponseDto(
                        response.intersectionId(),
                        response.phase(),
                        response.recommendedGreenSeconds(),
                        response.reason(),
                        response.calculatedAt()
                )
        );
    }
}
