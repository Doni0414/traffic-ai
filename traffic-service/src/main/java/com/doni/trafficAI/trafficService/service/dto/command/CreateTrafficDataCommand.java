package com.doni.trafficAI.trafficService.service.dto.command;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTrafficDataCommand(
        UUID laneId,
        Integer queueLength,
        BigDecimal density,
        BigDecimal avgSpeed) {
}
