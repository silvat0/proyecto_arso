package arso.repositorio;

import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class ExtensionMongoDAO <T>{

	 protected static MongoClient mongoClient;
	    protected static MongoDatabase db;
	    protected MongoCollection<T> collection;

	    protected CodecRegistry defaultCodecRegistry;
	    
	    public ExtensionMongoDAO() {
	        
	    	ConnectionString connectionString = new ConnectionString("mongodb+srv://arso:<password>@cluch.2l0gzgu.mongodb.net/?retryWrites=true&w=majority");
	    	//ConnectionString connectionString = new ConnectionString("mongodb+srv://raul:<password>@cluster0.n9oo3nv.mongodb.net/?retryWrites=true&w=majority");
	    	MongoClientSettings settings = MongoClientSettings.builder()
	    	        .applyConnectionString(connectionString)
	    	        .build();
	    	MongoClient mongoClient = MongoClients.create(settings);
	    	db = mongoClient.getDatabase("test");
	        
	       
	        
	        createCollection();
	       
	    }

	    public abstract void createCollection();
	    
	
}
