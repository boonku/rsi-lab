package org.example;

import org.example.grpc.Point;

public record CoordinatesPoint(String cityName, double latitude, double longitude) {
    public Point toGrpcPoint() {
        return Point.newBuilder()
                .setCityName(cityName)
                .setLongitude(longitude)
                .setLatitude(latitude)
                .build();
    }

    public static CoordinatesPoint fromGrpcPoint(Point point) {
        return new CoordinatesPoint(
                point.getCityName(),
                point.getLatitude(),
                point.getLongitude()
        );
    }
}
