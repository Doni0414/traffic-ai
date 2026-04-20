package com.doni.trafficAI.trafficService.service.dto.command;

import java.math.BigDecimal;

public record CreateEdgeCommand(
        Long intersectionId1,
        Long intersectionId2,
        BigDecimal distance) {
}
