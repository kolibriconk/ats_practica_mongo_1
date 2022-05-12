package org.example;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class Client {

    static MongoClient mongoClient;

    public static void main(String[] args) {
        // Create a new client
        connect();
    }

    private static void connect() {
        try {
            mongoClient = new MongoClient();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
