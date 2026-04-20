--liquibase formatted sql

--changeset doni:001-init-traffic-schema

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE road (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL
);

CREATE TABLE intersection (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              east_west_road_id BIGINT NULL,
                              south_north_road_id BIGINT NULL,
                              longitude NUMERIC(10,7) NOT NULL,
                              latitude NUMERIC(10,7) NOT NULL,
                              CONSTRAINT fk_intersection_east_west_road
                                  FOREIGN KEY (east_west_road_id) REFERENCES road(id),
                              CONSTRAINT fk_intersection_south_north_road
                                  FOREIGN KEY (south_north_road_id) REFERENCES road(id)
);

CREATE TABLE edge (
                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      intersection_id_1 BIGINT NOT NULL,
                      intersection_id_2 BIGINT NOT NULL,
                      distance NUMERIC(10,2) NOT NULL,
                      CONSTRAINT fk_edge_intersection_1
                          FOREIGN KEY (intersection_id_1) REFERENCES intersection(id),
                      CONSTRAINT fk_edge_intersection_2
                          FOREIGN KEY (intersection_id_2) REFERENCES intersection(id)
);

CREATE TABLE lane (
                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      edge_id UUID NOT NULL,
                      direction VARCHAR(50) NOT NULL,
                      lane_order INTEGER NOT NULL,
                      CONSTRAINT fk_lane_edge
                          FOREIGN KEY (edge_id) REFERENCES edge(id)
);

CREATE TABLE traffic (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         "timestamp" TIMESTAMP NOT NULL,
                         lane_id UUID NOT NULL,
                         queue_length INTEGER NOT NULL,
                         density NUMERIC(10,4) NOT NULL,
                         avg_speed NUMERIC(10,2) NOT NULL,
                         CONSTRAINT fk_traffic_lane
                             FOREIGN KEY (lane_id) REFERENCES lane(id)
);

CREATE INDEX idx_intersection_east_west_road_id
    ON intersection(east_west_road_id);

CREATE INDEX idx_intersection_south_north_road_id
    ON intersection(south_north_road_id);

CREATE INDEX idx_edge_intersection_id_1
    ON edge(intersection_id_1);

CREATE INDEX idx_edge_intersection_id_2
    ON edge(intersection_id_2);

CREATE INDEX idx_lane_edge_id
    ON lane(edge_id);

CREATE INDEX idx_traffic_lane_id
    ON traffic(lane_id);

CREATE INDEX idx_traffic_timestamp
    ON traffic("timestamp");

CREATE INDEX idx_traffic_lane_id_timestamp
    ON traffic(lane_id, "timestamp");