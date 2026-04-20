package com.doni.trafficAI.trafficService.service.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateEdgeCommandResponse(
        UUID id,
        Long intersectionId1,
        Long intersectionId2,
        BigDecimal distance) {
}
