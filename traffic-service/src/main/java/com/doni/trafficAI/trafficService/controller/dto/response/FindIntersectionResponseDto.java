package com.doni.trafficAI.trafficService.controller.dto.response;

import java.math.BigDecimal;

public record FindIntersectionResponseDto(
        Long intersectionId,
        String name,
        Long eastWestRoadId,
        Long southNorthRoadId,
        BigDecimal longitude,
        BigDecimal latitude) {
}
