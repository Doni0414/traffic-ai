package com.doni.trafficAI.trafficService.repository.spec;

import com.doni.trafficAI.trafficService.model.Traffic;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.UUID;

public final class TrafficSpecification {
    private TrafficSpecification() {

    }

    public static Specification<Traffic> byIntersectionId(Long intersectionId) {
        return ((root, query, criteriaBuilder) -> {
            if (intersectionId == null) {
                return null;
            }

            var edgeRoot = root.get("lane").get("edge");
            return criteriaBuilder.or(
                    criteriaBuilder.equal(edgeRoot.get("intersection1").get("id"), intersectionId),
                    criteriaBuilder.equal(edgeRoot.get("intersection2").get("id"), intersectionId)
            );
        });
    }

    public static Specification<Traffic> byLaneId(UUID laneId) {
        return (root, criteriaQuery, cb) -> laneId == null ? null : cb.equal(root.get("lane").get("id"), laneId);
    }

    public static Specification<Traffic> timestampFrom(Instant from) {
        return (root, criteriaQuery, cb) -> from == null ? null : cb.greaterThanOrEqualTo(root.get("timestamp"), from);
    }

    public static Specification<Traffic> timestampTo(Instant to) {
        return (root, criteriaQuery, cb) -> to == null ? null : cb.lessThanOrEqualTo(root.get("timestamp"), to);
    }
}
