package com.doni.trafficAI.trafficService.exception;

import java.util.UUID;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static ResourceNotFoundException laneNotFoundException(UUID laneId) {
        return new ResourceNotFoundException(String.format("Lane with id %s not found", laneId));
    }

    public static ResourceNotFoundException roadNotFound(Long roadId) {
        return new ResourceNotFoundException(String.format("Road with id %s not found", roadId));
    }

    public static ResourceNotFoundException intersectionNotFound(Long intersectionId) {
        return new ResourceNotFoundException(String.format("Intersection with id %s not found", intersectionId));
    }

    public static ResourceNotFoundException edgeNotFound(UUID edgeId) {
        return new ResourceNotFoundException("Edge with id %s not found".formatted(edgeId));
    }
}
