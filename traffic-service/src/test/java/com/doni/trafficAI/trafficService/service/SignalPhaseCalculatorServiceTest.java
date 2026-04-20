package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.model.Direction;
import com.doni.trafficAI.trafficService.model.Lane;
import com.doni.trafficAI.trafficService.model.SignalPhase;
import com.doni.trafficAI.trafficService.model.Traffic;
import com.doni.trafficAI.trafficService.repository.TrafficRepository;
import com.doni.trafficAI.trafficService.service.dto.command.CalculateSignalPhaseCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CalculateSignalPhaseCommandResponse;
import com.doni.trafficAI.trafficService.service.impl.SignalPhaseCalculatorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignalPhaseCalculatorServiceTest {

    @Mock
    private TrafficRepository trafficRepository;

    @InjectMocks
    private SignalPhaseCalculatorServiceImpl signalPhaseCalculatorService;

    @Test
    @DisplayName("Должен выбрать фазу NORTH_SOUTH, если суммарная очередь по NORTH/SOUTH больше")
    void shouldReturnNorthSouthPhaseWhenNsQueueIsGreater() {
        // given
        Long intersectionId = 5L;
        CalculateSignalPhaseCommand command = new CalculateSignalPhaseCommand(intersectionId);

        List<Traffic> traffics = List.of(
                traffic(Direction.NORTH, 12, "0.50", "18.00"),
                traffic(Direction.SOUTH, 10, "0.60", "16.00"),
                traffic(Direction.EAST, 3, "0.20", "30.00"),
                traffic(Direction.WEST, 2, "0.10", "32.00")
        );

        when(trafficRepository.findAll(any(Specification.class))).thenReturn(traffics);

        // when
        CalculateSignalPhaseCommandResponse response = signalPhaseCalculatorService.calculateGreen(command);

        // then
        assertEquals(intersectionId, response.intersectionId());
        assertEquals(SignalPhase.NORTH_SOUTH, response.phase());
        assertEquals("N/A", response.reason());
        assertNotNull(response.calculatedAt());

        // green = 15 + totalQueue*0.7 + avgDensity*10 + 5(if avgSpeed < 20)
        // totalQueue = 27
        // avgDensity = (0.5 + 0.6 + 0.2 + 0.1)/4 = 0.35
        // avgSpeed = (18 + 16 + 30 + 32)/4 = 24 => no +5
        // result = 15 + 18.9 + 3.5 = 37.4 => 37
        assertEquals(37, response.recommendedGreenSeconds());
    }

    @Test
    @DisplayName("ДЕФЕКТ: сервис использует исторические записи traffic вместо только актуальных")
    void shouldUseOnlyLatestTrafficPerLaneButCurrentlyUsesAllHistoricalRecords() {
        // given
        Long intersectionId = 5L;
        CalculateSignalPhaseCommand command = new CalculateSignalPhaseCommand(intersectionId);

        UUID northLaneId = UUID.randomUUID();
        UUID southLaneId = UUID.randomUUID();
        UUID eastLaneId = UUID.randomUUID();
        UUID westLaneId = UUID.randomUUID();

        Instant oldTime = Instant.parse("2026-04-20T10:00:00Z");
        Instant newTime = Instant.parse("2026-04-20T10:10:00Z");

        List<Traffic> traffics = List.of(
                // старые записи: вертикаль была сильно загружена
                traffic(northLaneId, Direction.NORTH, 20, "0.80", "10.00", oldTime),
                traffic(southLaneId, Direction.SOUTH, 18, "0.75", "11.00", oldTime),

                // новые записи: сейчас сильнее загружена горизонталь
                traffic(eastLaneId, Direction.EAST, 14, "0.70", "12.00", newTime),
                traffic(westLaneId, Direction.WEST, 13, "0.68", "13.00", newTime)
        );

        when(trafficRepository.findAll(any(Specification.class))).thenReturn(traffics);

        // when
        CalculateSignalPhaseCommandResponse response = signalPhaseCalculatorService.calculateGreen(command);

        // then
        // БИЗНЕС-ОЖИДАНИЕ:
        // сервис должен смотреть только на актуальные данные
        // => latest traffic показывает, что сейчас должна победить фаза EAST_WEST
        //
        // Но текущая реализация использует ВСЕ записи и суммирует:
        // NS = 20 + 18 = 38
        // EW = 14 + 13 = 27
        // => фактически выберет NORTH_SOUTH
        //
        // Поэтому этот assert сейчас должен падать и демонстрировать дефект.
        assertEquals(SignalPhase.EAST_WEST, response.phase());
    }

    private Traffic traffic(Direction direction, int queueLength, String density, String avgSpeed) {
        Lane lane = new Lane();
        lane.setId(UUID.randomUUID());
        lane.setDirection(direction);

        Traffic traffic = new Traffic();
        traffic.setId(UUID.randomUUID());
        traffic.setTimestamp(Instant.now());
        traffic.setLane(lane);
        traffic.setQueueLength(queueLength);
        traffic.setDensity(new BigDecimal(density));
        traffic.setAvgSpeed(new BigDecimal(avgSpeed));

        return traffic;
    }

    private Traffic traffic(
            UUID laneId,
            Direction direction,
            int queueLength,
            String density,
            String avgSpeed,
            Instant timestamp
    ) {
        Lane lane = new Lane();
        lane.setId(laneId);
        lane.setDirection(direction);

        Traffic traffic = new Traffic();
        traffic.setId(UUID.randomUUID());
        traffic.setLane(lane);
        traffic.setQueueLength(queueLength);
        traffic.setDensity(new BigDecimal(density));
        traffic.setAvgSpeed(new BigDecimal(avgSpeed));
        traffic.setTimestamp(timestamp);

        return traffic;
    }
}