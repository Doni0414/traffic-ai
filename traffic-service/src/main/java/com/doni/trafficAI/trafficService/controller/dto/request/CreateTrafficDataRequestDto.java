package com.doni.trafficAI.trafficService.controller.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTrafficDataRequestDto(
        @NotNull(message = "ID полосы не может быть null")
        UUID laneId,

        @NotNull(message = "Длина очереди должна быть указана")
        @Min(value = 0, message = "Длина очереди не может быть меньше 0")
        Integer queueLength,

        @NotNull
        @DecimalMin(value = "0", message = "Плотность трафика не может быть меньше 0")
        BigDecimal density,

        @NotNull
        @DecimalMin(value = "0", message = "Средняя скорость потока не может быть меньше 0")
        BigDecimal avgSpeed) {
}
