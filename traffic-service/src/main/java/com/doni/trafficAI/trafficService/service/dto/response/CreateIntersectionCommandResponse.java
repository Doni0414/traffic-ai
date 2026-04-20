package com.doni.trafficAI.trafficService.service.dto.response;

import java.math.BigDecimal;

public record CreateIntersectionCommandResponse(
        Long intersecrtionId,
        String name,
        Long eastWestRoadId,
        Long southNorthRoadId,
        BigDecimal longitude,
        BigDecimal latitude) {
}
