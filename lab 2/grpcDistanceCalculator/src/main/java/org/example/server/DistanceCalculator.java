package org.example.server;

import org.example.CoordinatesPoint;

import java.util.List;

public class DistanceCalculator {
    private static final int EARTH_RADIUS_IN_KM = 6371;

    private DistanceCalculator() {
    }

    public static double calculateDistance(List<CoordinatesPoint> points) {
        double distance = 0f;
        for (int i = 0; i < points.size() - 1; i++) {
            CoordinatesPoint point1 = points.get(i);
            CoordinatesPoint point2 = points.get(i + 1);
            double longitudeDistance = Math.toRadians(point2.longitude() - point1.longitude());
            double latitudeDistance = Math.toRadians(point2.latitude() - point1.latitude());
            double a = Math.pow(Math.sin(latitudeDistance / 2), 2) + Math.cos(Math.toRadians(point1.latitude())) *
                            Math.cos(Math.toRadians(point2.latitude())) * Math.pow(Math.sin(longitudeDistance / 2), 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            distance += EARTH_RADIUS_IN_KM * c;
        }
        return distance;
    }
}
