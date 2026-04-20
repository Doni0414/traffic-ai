package com.doni.trafficAI.trafficService.service.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CreateTrafficDataCommandResponse(
        UUID id,
        Instant timestamp,
        UUID laneId,
        Integer queueLength,
        BigDecimal density,
        BigDecimal avgSpeed) {
}
