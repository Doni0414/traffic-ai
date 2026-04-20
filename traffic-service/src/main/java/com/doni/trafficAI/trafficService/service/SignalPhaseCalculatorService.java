package com.doni.trafficAI.trafficService.service;

import com.doni.trafficAI.trafficService.service.dto.command.CalculateSignalPhaseCommand;
import com.doni.trafficAI.trafficService.service.dto.response.CalculateSignalPhaseCommandResponse;

import java.util.List;

public interface SignalPhaseCalculatorService {
    CalculateSignalPhaseCommandResponse calculateGreen(CalculateSignalPhaseCommand calculateSignalPhaseCommand);
}
