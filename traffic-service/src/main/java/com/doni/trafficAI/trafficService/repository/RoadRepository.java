package com.doni.trafficAI.trafficService.repository;

import com.doni.trafficAI.trafficService.model.Road;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends JpaRepository<Road, Long> {
}
