package com.doni.trafficAI.trafficService.service.dto.command;

import java.math.BigDecimal;

public record CreateIntersectionCommand(
        String name,
        Long eastWestRoadId,
        Long southNorthRoadId,
        BigDecimal longitude,
        BigDecimal latitude) {
}
