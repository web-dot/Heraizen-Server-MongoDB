package Heraizen.Server.MongoDB;

import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;



public class MongoServiceImpl implements MongoService {

    @Override
    public void createMongoConnection() {
       
        //READ DATA FROM MONGODB
        
        //connect to mongoDb instance
        final String uriString = "mongodb://localhost:27017/test";
        MongoClient mongoClient = MongoClients.create(uriString);
       
        //create first document -> MongoDB stores documents as BSON
        //get the inventory collection
        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("IplData");
       
        //query the document
        FindIterable<Document> findIterable = collection.find(new Document());
        
        //iterate over the results
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                
                System.out.println(document.toJson());
                
            }
            
        };
        
        findIterable.forEach(printBlock);
        //when done working with data,close the connection
        //mongoClient.close();
        
        System.out.println();
        
        //READ DATA FROM MONGODB WITH QUUIRIES
        
        //retrieve specific documents in a collection
        findIterable = collection.find(eq("city", "Mumbai, Maharashtra"));
        
        //use Java.util.function.Consumer to print results of the iteration
        Consumer<Document> printConsumer = new Consumer<Document>() {
            
            @Override
            public void accept(final Document doc) {
                
                System.out.println(doc.toJson());
                
            }
        };
        
        findIterable.forEach(printConsumer);
        
        
        
    }

}
