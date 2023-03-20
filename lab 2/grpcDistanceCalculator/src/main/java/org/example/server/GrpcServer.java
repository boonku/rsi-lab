package org.example.server;

import io.grpc.ServerBuilder;
import org.example.MyData;

import java.io.IOException;
import java.net.UnknownHostException;

public class GrpcServer {
    private final static int PORT=9000;

    public static void main(String[] args) throws UnknownHostException {
        MyData.info();
        try {
            var server = ServerBuilder
                    .forPort(PORT)
                    .addService(new DistanceCalculatorServiceImpl())
                    .build();

            server.start();
            while (true) Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            System.out.println("Server terminated");
        }
    }
}
