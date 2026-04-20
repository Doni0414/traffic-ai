package com.doni.trafficAI.trafficService.service.dto.command;

import com.doni.trafficAI.trafficService.model.Direction;

import java.util.UUID;

public record CreateLaneCommand(
        UUID edgeId,
        Direction direction,
        Integer order) {
}
