package com.doni.trafficAI.trafficService.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record CalculateSignalPhaseRequestDto(
        @NotNull(message = "Идентификатор перекрёстка обязателен")
        Long intersectionId) {
}
