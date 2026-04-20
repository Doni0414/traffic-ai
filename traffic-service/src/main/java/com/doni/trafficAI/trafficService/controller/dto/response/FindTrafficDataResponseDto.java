package com.doni.trafficAI.trafficService.controller.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record FindTrafficDataResponseDto(
        UUID id,
        Instant timestamp,
        UUID laneId,
        Integer queueLength,
        BigDecimal density,
        BigDecimal avgSpeed) {
}
