package com.doni.trafficAI.trafficService.repository;

import com.doni.trafficAI.trafficService.model.Edge;
import com.doni.trafficAI.trafficService.model.Intersection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, UUID> {
    Optional<Edge> findByIntersection1AndIntersection2(Intersection intersection1, Intersection intersection2);
    Optional<Edge> findByIntersection1IdAndIntersection2Id(Long intersectionId1, Long intersectionId2);
}
