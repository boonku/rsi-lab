package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

public class Receiver {
    private static final LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String QUEUE_NAME = "hello";
    private static final String MARKER = "end";
    private static final int MAX_COUNTER = 2;
    private static int counter = 0;

    public static void main(String[] args) throws IOException, TimeoutException {
        MyData.info();

        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        objectMapper.registerModule(module);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("client");
//        factory.setPassword("client");
//        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("[*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            if (message.equals(MARKER)) {
                counter++;
                if (counter >= MAX_COUNTER) {
                    System.out.println("Exiting...");
                    channel.basicCancel(consumerTag);
                    try {
                        channel.close();
                        connection.close();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Message decodedMessage = objectMapper.readValue(message, Message.class);
                System.out.println("[x] Received '" + decodedMessage);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
