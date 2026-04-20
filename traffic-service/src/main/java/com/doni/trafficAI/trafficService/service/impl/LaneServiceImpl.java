package com.doni.trafficAI.trafficService.service.impl;

import com.doni.trafficAI.trafficService.exception.ResourceNotFoundException;
import com.doni.trafficAI.trafficService.model.Edge;
import com.doni.trafficAI.trafficService.model.Lane;
import com.doni.trafficAI.trafficService.repository.EdgeRepository;
import com.doni.trafficAI.trafficService.repository.LaneRepository;
import com.doni.trafficAI.trafficService.service.LaneService;
import com.doni.trafficAI.trafficService.service.dto.command.CreateLaneCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateLaneCommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LaneServiceImpl implements LaneService {
    private final EdgeRepository edgeRepository;
    private final LaneRepository laneRepository;

    @Override
    @Transactional
    public CreateLaneCommandResponse createLane(CreateLaneCommand createLaneCommand) {
        Edge edge = findEdgeById(createLaneCommand.edgeId());

        Lane lane = new Lane();
        lane.setEdge(edge);
        lane.setDirection(createLaneCommand.direction());
        lane.setLaneOrder(createLaneCommand.order());

        Lane savedLane = laneRepository.save(lane);
        return new CreateLaneCommandResponse(
                savedLane.getId(),
                edge.getId(),
                savedLane.getDirection(),
                savedLane.getLaneOrder()
        );
    }

    private Edge findEdgeById(UUID edgeId) {
        return edgeRepository.findById(edgeId)
                .orElseThrow(() -> ResourceNotFoundException.edgeNotFound(edgeId));
    }
}
