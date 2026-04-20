package com.doni.trafficAI.trafficService.service.dto.response;

import com.doni.trafficAI.trafficService.model.SignalPhase;

import java.time.Instant;

public record CalculateSignalPhaseCommandResponse(
        Long intersectionId,
        SignalPhase phase,
        Integer recommendedGreenSeconds,
        String reason,
        Instant calculatedAt) {
}
