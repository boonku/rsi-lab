package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Sender {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws UnknownHostException {
        MyData.info();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello world!";
            publishMessage(channel, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void publishMessage(Channel channel, String message) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("[x] Sent '" + message + "'");
    }
}
