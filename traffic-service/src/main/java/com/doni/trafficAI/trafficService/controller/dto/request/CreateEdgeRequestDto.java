package com.doni.trafficAI.trafficService.controller.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateEdgeRequestDto(
        @NotNull(message = "ID первого перекрестка должен быть указан")
        Long intersectionId1,

        @NotNull(message = "ID второго перекрестка должен быть указан")
        Long intersectionId2,

        @NotNull(message = "Длина дороги должна быть указана")
        @DecimalMin(value = "0", message = "Неправильное знаачение длины")
        BigDecimal distance) {
}
