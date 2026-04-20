package com.doni.trafficAI.trafficService.repository;

import com.doni.trafficAI.trafficService.model.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, UUID>, JpaSpecificationExecutor<Traffic> {
}
