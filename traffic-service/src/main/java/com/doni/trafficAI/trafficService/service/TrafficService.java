package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.service.dto.command.CreateTrafficDataCommand;
import com.doni.trafficAI.trafficService.service.dto.command.FindTrafficsDataCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CreateTrafficDataCommandResponse;
import com.doni.trafficAI.trafficService.service.dto.response.FindTrafficsDataCommandResponse;

public interface TrafficService {
    CreateTrafficDataCommandResponse createTraffic(CreateTrafficDataCommand createTrafficDataCommand);
    FindTrafficsDataCommandResponse findAll(FindTrafficsDataCommand findTrafficsDataCommand);
}
