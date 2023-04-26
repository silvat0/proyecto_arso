package arso.repositorio.mongo;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;


public abstract class ExtensionMongoDAO <T>{

	 protected static MongoClient mongoClient;
	    protected static MongoDatabase db;
	    protected MongoCollection<T> collection;

	    protected CodecRegistry defaultCodecRegistry;
	    
	    public ExtensionMongoDAO() {
	        
	        mongoClient = MongoClients.create("mongodb://localhost:27017");     
	        db = mongoClient.getDatabase("ZeppelinUMPerezCortes");
	        
	       
	        
	        createCollection();
	       
	    }

	    public abstract void createCollection();
	    
	
}
