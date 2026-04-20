package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.service.dto.command.CreateLaneCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateLaneCommandResponse;

public interface LaneService {
    CreateLaneCommandResponse createLane(CreateLaneCommand createLaneCommand);
}
