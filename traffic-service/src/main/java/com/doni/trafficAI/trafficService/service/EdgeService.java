package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.service.dto.command.CreateEdgeCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateEdgeCommandResponse;

public interface EdgeService {
    CreateEdgeCommandResponse createEdge(CreateEdgeCommand createEdgeCommand);
}
