package com.doni.trafficAI.trafficService.service.impl;

import com.doni.trafficAI.trafficService.exception.ResourceNotFoundException;
import com.doni.trafficAI.trafficService.model.Lane;
import com.doni.trafficAI.trafficService.model.Traffic;
import com.doni.trafficAI.trafficService.repository.LaneRepository;
import com.doni.trafficAI.trafficService.repository.TrafficRepository;
import com.doni.trafficAI.trafficService.repository.spec.TrafficSpecification;
import com.doni.trafficAI.trafficService.service.TrafficService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateTrafficDataCommand;
import com.doni.trafficAI.trafficService.service.dto.command.FindTrafficsDataCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateTrafficDataCommandResponse;
import com.doni.trafficAI.trafficService.service.dto.response.FindTrafficDataCommandResponse;
import com.doni.trafficAI.trafficService.service.dto.response.FindTrafficsDataCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {
    private final TrafficRepository trafficRepository;
    private final LaneRepository laneRepository;

    @Override
    @Transactional
    public CreateTrafficDataCommandResponse createTraffic(CreateTrafficDataCommand createTrafficDataCommand) {
        Lane lane = laneRepository.findById(createTrafficDataCommand.laneId())
                        .orElseThrow(() -> ResourceNotFoundException.laneNotFoundException(createTrafficDataCommand.laneId()));

        Traffic traffic = new Traffic();
        traffic.setLane(lane);
        traffic.setDensity(createTrafficDataCommand.density());
        traffic.setTimestamp(Instant.now());
        traffic.setQueueLength(createTrafficDataCommand.queueLength());
        traffic.setAvgSpeed(createTrafficDataCommand.avgSpeed());

        Traffic savedTraffic = trafficRepository.save(traffic);

        return new CreateTrafficDataCommandResponse(
                savedTraffic.getId(),
                savedTraffic.getTimestamp(),
                lane.getId(),
                savedTraffic.getQueueLength(),
                savedTraffic.getDensity(),
                savedTraffic.getAvgSpeed()
        );
    }

    @Override
    public FindTrafficsDataCommandResponse findAll(FindTrafficsDataCommand findTrafficsDataCommand) {
        Specification<Traffic> spec = Specification
                .where(TrafficSpecification.byIntersectionId(findTrafficsDataCommand.intersectionId()))
                .and(TrafficSpecification.byLaneId(findTrafficsDataCommand.laneId()))
                .and(TrafficSpecification.timestampFrom(findTrafficsDataCommand.from()))
                .and(TrafficSpecification.timestampTo(findTrafficsDataCommand.to()));

        return new FindTrafficsDataCommandResponse(
                trafficRepository.findAll(spec).stream()
                        .map(this::entityToCommandResponse)
                        .toList()
        );
    }

    private FindTrafficDataCommandResponse entityToCommandResponse(Traffic traffic) {
        return new FindTrafficDataCommandResponse(
                traffic.getId(),
                traffic.getTimestamp(),
                traffic.getLane().getId(),
                traffic.getQueueLength(),
                traffic.getDensity(),
                traffic.getAvgSpeed()
        );
    }
}
