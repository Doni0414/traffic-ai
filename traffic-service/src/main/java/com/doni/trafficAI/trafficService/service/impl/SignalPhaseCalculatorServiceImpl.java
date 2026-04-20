package com.doni.trafficAI.trafficService.service.impl;

import com.doni.trafficAI.trafficService.model.Direction;
import com.doni.trafficAI.trafficService.model.SignalPhase;
import com.doni.trafficAI.trafficService.model.Traffic;
import com.doni.trafficAI.trafficService.repository.TrafficRepository;
import com.doni.trafficAI.trafficService.repository.spec.TrafficSpecification;
import com.doni.trafficAI.trafficService.service.SignalPhaseCalculatorService;
import com.doni.trafficAI.trafficService.service.dto.command.CalculateSignalPhaseCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CalculateSignalPhaseCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SignalPhaseCalculatorServiceImpl implements SignalPhaseCalculatorService {
    private static final int MIN_GREEN = 15;
    private static final int MAX_GREEN = 60;
    private final TrafficRepository trafficRepository;

    @Override
    public CalculateSignalPhaseCommandResponse calculateGreen(CalculateSignalPhaseCommand calculateSignalPhaseCommand) {
        Specification<Traffic> spec = Specification
                .where(TrafficSpecification.byIntersectionId(calculateSignalPhaseCommand.intersectionId()));

        List<Traffic> traffics = trafficRepository.findAll(spec);

        if (traffics.isEmpty()) {
            throw new IllegalArgumentException(
                    "Для перекрестка с id=" + calculateSignalPhaseCommand.intersectionId() + " нет данных о трафике"
            );
        }

        int totalQueue = traffics.stream()
                .mapToInt(Traffic::getQueueLength)
                .sum();

        double averageDensity = traffics.stream()
                .map(Traffic::getDensity)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);

        double averageSpeed = traffics.stream()
                .map(Traffic::getAvgSpeed)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);

        int nsQueue = traffics.stream()
                .filter(t -> isNorthSouth(t.getLane().getDirection()))
                .mapToInt(Traffic::getQueueLength)
                .sum();

        int ewQueue = traffics.stream()
                .filter(t -> isEastWest(t.getLane().getDirection()))
                .mapToInt(Traffic::getQueueLength)
                .sum();

        SignalPhase recommendedPhase = nsQueue >= ewQueue ? SignalPhase.NORTH_SOUTH : SignalPhase.EAST_WEST;

        int recommendedGreen = calculateGreen(
                totalQueue,
                averageDensity,
                averageSpeed,
                MIN_GREEN,
                MAX_GREEN
        );

        return new CalculateSignalPhaseCommandResponse(
                calculateSignalPhaseCommand.intersectionId(),
                recommendedPhase,
                recommendedGreen,
                "N/A",
                Instant.now()
        );
    }

    private int calculateGreen(
            int totalQueue,
            double averageDensity,
            double averageSpeed,
            int greenMin,
            int greenMax
    ) {
        double result = greenMin;

        result += totalQueue * 0.7;
        result += averageDensity * 10.0;

        if (averageSpeed < 20.0) {
            result += 5.0;
        }

        return (int) Math.max(greenMin, Math.min(greenMax, Math.round(result)));
    }

    private boolean isNorthSouth(Direction direction) {
        return direction == Direction.NORTH || direction == Direction.SOUTH;
    }

    private boolean isEastWest(Direction direction) {
        return direction == Direction.EAST || direction == Direction.WEST;
    }
}
