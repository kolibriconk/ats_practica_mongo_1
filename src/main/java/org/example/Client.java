package org.example;

import com.mongodb.MongoException;
import com.mongodb.client.*;
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
            exercise4(collection);
        } catch (MongoException ignored) {
            System.out.println("Cannot connect to mongo host");
        }
    }

    private static void exercise1(MongoCollection<Document> collection) {

    }

    private static void exercise2(MongoCollection<Document> collection){
        System.out.println("Exercise 2");
        System.out.println(collection.count(Document.parse("{founded_year: 2000 }")));
    }

    private static void exercise4(MongoCollection<Document> collection){
        System.out.println("Exercise 4");
        String phone="phone";
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        while (cursor.hasNext()){
            String find = cursor.next().get();
        }

        //collection.updateMany(new Document(), new Document("$set", new Document("tag_list", "phone")));
    }
}
