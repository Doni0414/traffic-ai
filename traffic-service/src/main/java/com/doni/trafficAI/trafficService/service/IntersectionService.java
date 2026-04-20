package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.service.dto.command.CreateIntersectionCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateIntersectionCommandResponse;

public interface IntersectionService {
    CreateIntersectionCommandResponse createIntersection(CreateIntersectionCommand createIntersectionCommand);
}
