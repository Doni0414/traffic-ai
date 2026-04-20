package com.doni.trafficAI.trafficService.service.impl;

import com.doni.trafficAI.trafficService.exception.EdgeException;
import com.doni.trafficAI.trafficService.exception.ResourceNotFoundException;
import com.doni.trafficAI.trafficService.model.Edge;
import com.doni.trafficAI.trafficService.model.Intersection;
import com.doni.trafficAI.trafficService.repository.EdgeRepository;
import com.doni.trafficAI.trafficService.repository.IntersectionRepository;
import com.doni.trafficAI.trafficService.service.EdgeService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateEdgeCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateEdgeCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EdgeServiceImpl implements EdgeService {
    private final EdgeRepository edgeRepository;
    private final IntersectionRepository intersectionRepository;

    @Override
    @Transactional
    public CreateEdgeCommandResponse createEdge(CreateEdgeCommand createEdgeCommand) {
        if (edgeExists(createEdgeCommand.intersectionId1(), createEdgeCommand.intersectionId2())) {
            throw EdgeException.edgeExists(createEdgeCommand.intersectionId1(), createEdgeCommand.intersectionId2());
        }

        Intersection intersection1 = findIntersectionById(createEdgeCommand.intersectionId1());
        Intersection intersection2 = findIntersectionById(createEdgeCommand.intersectionId2());

        Edge edge = new Edge();
        edge.setIntersection1(intersection1);
        edge.setIntersection2(intersection2);
        edge.setDistance(createEdgeCommand.distance());

        Edge savedEdge = edgeRepository.save(edge);
        return new CreateEdgeCommandResponse(
                edge.getId(),
                intersection1.getId(),
                intersection2.getId(),
                savedEdge.getDistance()
        );
    }

    private Intersection findIntersectionById(Long intersectionId) {
        return intersectionRepository.findById(intersectionId)
                .orElseThrow(() -> ResourceNotFoundException.intersectionNotFound(intersectionId));
    }

    private boolean edgeExists(Long intersectionId1, Long intersectionId2) {
        return edgeRepository.findByIntersection1IdAndIntersection2Id(intersectionId1, intersectionId2).isPresent();
    }
}
