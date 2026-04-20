package com.doni.trafficAI.trafficService.controller.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateIntersectionRequestDto(
        @NotNull(message = "Имя перекрестка не может быть пустым")
        @NotBlank(message = "Имя перекрестка не может быть пустым")
        String name,

        @NotNull(message = "ID востоко-западной дороги должен быть указан")
        Long eastWestRoadId,

        @NotNull(message = "ID юго-северной дороги должен быть указан")
        Long southNorthRoadId,

        @NotNull(message = "Долгота должна быть указана")
        @DecimalMin(value = "-180.0", message = "Долгота должна быть не меньше -180")
        @DecimalMax(value = "180.0", message = "Долгота должна быть не больше 180")
        BigDecimal longitude,

        @NotNull(message = "Широта должна быть указана")
        @DecimalMin(value = "-90.0", message = "Широта должна быть не меньше -90")
        @DecimalMax(value = "90.0", message = "Широта должна быть не больше 90")
        BigDecimal latitude
) {
}
