package com.doni.trafficAI.trafficService.controller.dto.request;

import com.doni.trafficAI.trafficService.model.Direction;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateLaneRequestDto(
        @NotNull(message = "ID дороги должен быть указан")
        UUID edgeId,

        @NotNull(message = "Направление полосы должен быть указан")
        Direction direction,

        @NotNull(message = "Номер полосы должен быть указан")
        Integer order) {
}
