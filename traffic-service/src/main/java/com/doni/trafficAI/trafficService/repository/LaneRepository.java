package com.doni.trafficAI.trafficService.repository;

import com.doni.trafficAI.trafficService.model.Lane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LaneRepository extends JpaRepository<Lane, UUID> {
}
