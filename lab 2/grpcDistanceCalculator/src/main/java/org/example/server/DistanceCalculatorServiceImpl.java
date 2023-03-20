package org.example.server;

import io.grpc.stub.StreamObserver;
import org.example.CoordinatesPoint;
import org.example.grpc.DistanceCalculatorRequest;
import org.example.grpc.DistanceCalculatorResponse;
import org.example.grpc.DistanceCalculatorServiceGrpc.DistanceCalculatorServiceImplBase;

import java.util.List;

public class DistanceCalculatorServiceImpl extends DistanceCalculatorServiceImplBase {

    @Override
    public void distance(DistanceCalculatorRequest request, StreamObserver<DistanceCalculatorResponse> responseObserver) {
        List<CoordinatesPoint> points = request.getPointsList()
                .stream()
                .map(CoordinatesPoint::fromGrpcPoint)
                .toList();
        double distance = DistanceCalculator.calculateDistance(points);

        DistanceCalculatorResponse response = DistanceCalculatorResponse.newBuilder()
                .setDistance(distance)
                .build();

        System.out.println("Responding to request: " + points + ", distance: " + distance);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
