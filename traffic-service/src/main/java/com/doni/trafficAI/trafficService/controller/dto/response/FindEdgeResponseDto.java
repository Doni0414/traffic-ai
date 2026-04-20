package com.doni.trafficAI.trafficService.controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record FindEdgeResponseDto(
        UUID id,
        Long intersectionId1,
        Long intersectionId2,
        BigDecimal distance) {
}
