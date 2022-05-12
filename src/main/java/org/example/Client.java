package org.example;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.net.UnknownHostException;
import java.util.Collection;

public class Client {

    static final String URI = "mongodb://localhost:27017/";

    public static void main(String[] args) {
        // Create a new client
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("companies");
            exercise1(collection);
        } catch (MongoException ignored) {
            System.out.println("Cannot connect to mongo host");
        }
    }

    private static void exercise1(MongoCollection<Document> collection) {

    }
}
