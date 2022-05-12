package org.example;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Client {

    static final String URI = "mongodb://localhost:27017/";

    public static void main(String[] args) {
        // Create a new client
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("companies");
            exercise1(collection);
            exercise2(collection);
            exercise3(collection);
            exercise4(collection);
            exercise5(collection);
        } catch (MongoException ignored) {
            System.out.println("Cannot connect to mongo host");
        }
    }

    /**
     * Augmentar el número en 1 number_of_employees pels documents que
     * tenen a l’atribut name el valor MeetMoi.
     */
    private static void exercise1(MongoCollection<Document> collection) {
        System.out.println("Exercise 1:");
        Document filter = new Document("name", "MeetMoi");
        Document update = new Document("$inc", new Document("number_of_employees", 1));
        collection.updateMany(filter, update);
    }

    private static void exercise2(MongoCollection<Document> collection) {
        System.out.println("Exercise 2");
        System.out.println(collection.count(Document.parse("{founded_year: 2000 }")));
    }

    /**
     * Quins valors diferents té l’atribut category_code?
     */
    private static void exercise3(MongoCollection<Document> collection) {
        System.out.println("Exercise 3:");
        Collection<String> distinct = collection.distinct("category_code", String.class)
                .filter(new Document("category_code", new Document("$ne", null)))
                .into(new ArrayList<>());
        System.out.println(distinct);
        System.out.println("Total: " + distinct.size());
    }

    private static void exercise4(MongoCollection<Document> collection) {
        System.out.println("Exercise 4");
        String phone = "phone";
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        while (cursor.hasNext()) {
            String find = cursor.next().get();
        }

        //collection.updateMany(new Document(), new Document("$set", new Document("tag_list", "phone")));
    }

    /**
     * Quina és la mitjana del camp number_of_employees per les companyies
     * amb founded_year posterior al 2005?
     */
    private static void exercise5(MongoCollection<Document> collection) {
        System.out.println("Exercise 5:");
        Document filter = new Document("founded_year", new Document("$gt", 2005));
        AggregateIterable<Document> avg = collection
                .aggregate(
                        Arrays.asList(
                                Aggregates.match(filter),
                                Aggregates.group("_id", new BsonField("average", new BsonDocument("$avg", new BsonString("$number_of_employees"))))
                        )
                );

        System.out.println(avg.first().getDouble("average"));
    }
}
