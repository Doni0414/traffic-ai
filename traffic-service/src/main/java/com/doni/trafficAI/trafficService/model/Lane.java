package com.doni.trafficAI.trafficService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lane")
public class Lane {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "edge_id", nullable = false)
    private Edge edge;

    @Column(name = "direction", nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(name = "lane_order", nullable = false)
    private int laneOrder;
}