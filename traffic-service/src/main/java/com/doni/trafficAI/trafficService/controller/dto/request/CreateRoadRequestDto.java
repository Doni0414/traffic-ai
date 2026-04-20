package com.doni.trafficAI.trafficService.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRoadRequestDto(
        @NotNull(message = "Имя дороги не может быть пустым")
        @NotBlank(message = "Имя дороги не может быть пустым")
        String name) {
}
