package com.doni.trafficAI.trafficService.exception;

public class EdgeException extends ApplicationException {
    public EdgeException() {
    }

    public EdgeException(String message) {
        super(message);
    }

    public EdgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EdgeException(Throwable cause) {
        super(cause);
    }

    public EdgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static EdgeException edgeExists(Long intersectionId1, Long intersectionId2) {
        return new EdgeException("Edge already exists between %d and %d intersections".formatted(intersectionId1, intersectionId2));
    }
}
