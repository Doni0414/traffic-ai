package com.doni.trafficAI.trafficService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "edge")
public class Edge {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "intersection_id_1", nullable = false)
    private Intersection intersection1;

    @ManyToOne
    @JoinColumn(name = "intersection_id_2", nullable = false)
    private Intersection intersection2;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal distance;
}