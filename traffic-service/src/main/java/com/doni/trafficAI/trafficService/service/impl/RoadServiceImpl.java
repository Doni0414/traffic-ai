package com.doni.trafficAI.trafficService.service.impl;

import com.doni.trafficAI.trafficService.model.Road;
import com.doni.trafficAI.trafficService.repository.RoadRepository;
import com.doni.trafficAI.trafficService.service.RoadService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateRoadCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateRoadCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoadServiceImpl implements RoadService {
    private final RoadRepository roadRepository;

    @Override
    @Transactional
    public CreateRoadCommandResponse createRoad(CreateRoadCommand createRoadCommand) {
        Road road = new Road();
        road.setName(createRoadCommand.name());

        Road savedRoad = roadRepository.save(road);
        return new CreateRoadCommandResponse(savedRoad.getId(), savedRoad.getName());
    }
}
