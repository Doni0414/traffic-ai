package com.doni.trafficAI.trafficService.controller.dto.response;

import com.doni.trafficAI.trafficService.model.SignalPhase;

import java.time.Instant;

public record SignalPhaseCalculationResponseDto(
        Long intersectionId,
        SignalPhase phase,
        Integer recommendedGreenSeconds,
        String reason,
        Instant calculatedAt) {
}
