package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.service.dto.command.CreateRoadCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateRoadCommandResponse;

public interface RoadService {
    CreateRoadCommandResponse createRoad(CreateRoadCommand createRoadCommand);
}
