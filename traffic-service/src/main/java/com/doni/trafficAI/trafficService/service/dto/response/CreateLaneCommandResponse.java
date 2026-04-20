package com.doni.trafficAI.trafficService.service.dto.response;

import com.doni.trafficAI.trafficService.model.Direction;

import java.util.UUID;

public record CreateLaneCommandResponse(
        UUID id,
        UUID edgeId,
        Direction direction,
        Integer order) {
}
