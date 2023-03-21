package org.example.client;

import io.grpc.ManagedChannelBuilder;
import org.example.CoordinatesPoint;
import org.example.MyData;
import org.example.grpc.DistanceCalculatorRequest;
import org.example.grpc.DistanceCalculatorResponse;
import org.example.grpc.DistanceCalculatorServiceGrpc;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrpcClient {
    private final static String HOST_ADDRESS = "10.182.253.154";
    private final static int PORT = 9000;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException {
        MyData.info();

        var channel = ManagedChannelBuilder.forAddress(HOST_ADDRESS, PORT)
                .usePlaintext()
                .build();
        printMenu();
        String option = scanner.nextLine();
        while (!option.equals("c")) {
            int numberOfPoints = getNumberOfPoints(option);
            List<CoordinatesPoint> points = readPoints(numberOfPoints);

            var stub = DistanceCalculatorServiceGrpc.newBlockingStub(channel);
            DistanceCalculatorRequest request = buildDistanceCalculatorRequest(points);
            DistanceCalculatorResponse distanceCalculatorResponse = stub.distance(request);

            printResponse(points, distanceCalculatorResponse);

            printMenu();
            option = scanner.nextLine();
        }
        channel.shutdownNow();
    }

    private static void printMenu() {
        System.out.println("Choose option:");
        System.out.println("a) Calculate distance between two cities");
        System.out.println("b) Calculate distance between two cities through another city");
        System.out.println("c) Exit");
        System.out.print("> ");
    }

    private static int getNumberOfPoints(String option) {
        int numberOfPoints;
        switch (option) {
            case "a" -> numberOfPoints = 2;
            case "b" -> numberOfPoints = 3;
            default -> numberOfPoints = 0;
        }
        return numberOfPoints;
    }

    private static List<CoordinatesPoint> readPoints(int numberOfPoints) {
        List<CoordinatesPoint> points = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            points.add(readPoint());
        }
        return points;
    }

    private static CoordinatesPoint readPoint() {
        System.out.print("City name: ");
        String cityName = scanner.nextLine();
        System.out.print("City latitude: ");
        double latitude = Double.parseDouble(scanner.nextLine());
        System.out.print("City longitude: ");
        double longitude = Double.parseDouble(scanner.nextLine());
        System.out.println();
        return new CoordinatesPoint(cityName, latitude, longitude);
    }

    private static DistanceCalculatorRequest buildDistanceCalculatorRequest(List<CoordinatesPoint> points) {
        var request = DistanceCalculatorRequest.newBuilder();
        for (CoordinatesPoint point : points) {
            request.addPoints(point.toGrpcPoint());
        }
        return request.build();
    }

    private static void printResponse(List<CoordinatesPoint> points, DistanceCalculatorResponse response) {
        if (points.size() == 2) {
            System.out.printf("Distance between %s and %s is: %.4fkm\n",
                    points.get(0).cityName(), points.get(1).cityName(), response.getDistance());
        } else if (points.size() == 3) {
            System.out.printf("Distance between %s and %s through %s is: %.4fkm\n",
                    points.get(0).cityName(), points.get(2).cityName(),
                    points.get(1).cityName(), response.getDistance());
        }
    }
}
