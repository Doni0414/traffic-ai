package com.doni.trafficAI.trafficService.service.dto.command;

import java.time.Instant;
import java.util.UUID;

public record FindTrafficsDataCommand(
        Long intersectionId,
        UUID laneId,
        Instant from,
        Instant to) {

    public FindTrafficsDataCommand(Long intersectionId) {
        this(intersectionId, null, null, null);
    }
}
