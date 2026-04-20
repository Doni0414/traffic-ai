package com.doni.trafficAI.trafficService.controller.dto.response;

import com.doni.trafficAI.trafficService.model.Direction;

import java.util.UUID;

public record FindLaneResponseDto(
        UUID id,
        UUID edgeId,
        Direction direction,
        Integer order) {
}
