package com.doni.trafficAI.trafficService.service.impl;

import com.doni.trafficAI.trafficService.exception.ResourceNotFoundException;
import com.doni.trafficAI.trafficService.model.Intersection;
import com.doni.trafficAI.trafficService.model.Road;
import com.doni.trafficAI.trafficService.repository.IntersectionRepository;
import com.doni.trafficAI.trafficService.repository.RoadRepository;
import com.doni.trafficAI.trafficService.service.IntersectionService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateIntersectionCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateIntersectionCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IntersectionServiceImpl implements IntersectionService {
    private final RoadRepository roadRepository;
    private final IntersectionRepository intersectionRepository;

    @Override
    @Transactional
    public CreateIntersectionCommandResponse createIntersection(CreateIntersectionCommand createIntersectionCommand) {
        Road eastWestRoad = findRoadById(createIntersectionCommand.eastWestRoadId());
        Road southNorthRoad = findRoadById(createIntersectionCommand.southNorthRoadId());

        Intersection intersection = new Intersection();
        intersection.setName(createIntersectionCommand.name());
        intersection.setEastWestRoad(eastWestRoad);
        intersection.setSouthNorthRoad(southNorthRoad);
        intersection.setLongitude(createIntersectionCommand.longitude());
        intersection.setLatitude(createIntersectionCommand.latitude());

        Intersection savedIntersection = intersectionRepository.save(intersection);
        return new CreateIntersectionCommandResponse(
                savedIntersection.getId(),
                savedIntersection.getName(),
                eastWestRoad.getId(),
                southNorthRoad.getId(),
                intersection.getLongitude(),
                intersection.getLatitude()
        );
    }

    private Road findRoadById(Long roadId) {
        return roadRepository.findById(roadId)
                .orElseThrow(() -> ResourceNotFoundException.roadNotFound(roadId));
    }
}
