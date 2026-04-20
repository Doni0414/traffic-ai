package com.doni.trafficAI.trafficService.controller.dto.request;

import java.time.Instant;
import java.util.UUID;

public record FindTrafficsDataRequestDto(
        Long intersectionId,
        UUID laneId,
        Instant from,
        Instant to
) {
}
