package Heraizen.Server.MongoDB;

public class MongoWithJavaMain {

    public static void main(String[] args) {
        
        
        MongoServiceImpl mongoServiceImpl = new MongoServiceImpl();
        
        mongoServiceImpl.createMongoConnection();

    }

}
