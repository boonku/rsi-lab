package org.example;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        MyData.info();
        Client client = new Client();
        client.start();
    }

}
