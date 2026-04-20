package com.doni.trafficAI.trafficService.repository;

import com.doni.trafficAI.trafficService.model.Intersection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntersectionRepository extends JpaRepository<Intersection, Long> {
}
