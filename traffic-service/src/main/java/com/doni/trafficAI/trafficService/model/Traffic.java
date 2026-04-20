package com.doni.trafficAI.trafficService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "traffic")
public class Traffic {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Instant timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lane_id", nullable = false)
    private Lane lane;

    @Column(name = "queue_length", nullable = false)
    private Integer queueLength;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal density;

    @Column(name = "avg_speed", nullable = false, precision = 10, scale = 2)
    private BigDecimal avgSpeed;
}