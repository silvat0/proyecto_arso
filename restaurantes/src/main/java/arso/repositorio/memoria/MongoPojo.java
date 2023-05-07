package arso.repositorio.memoria;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import arso.restaurantes.modelo.Restaurante;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoPojo {

	MongoCollection<Restaurante> coleccion;
	
	public MongoPojo() {
		ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb+srv://arso:arso@cluch.2l0gzgu.mongodb.net/?retryWrites=true&w=majority"));
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		MongoClientSettings clientSettings = MongoClientSettings.builder()
	            .applyConnectionString(connectionString)
	            .codecRegistry(codecRegistry)
	            .build();
		
		MongoClient mongoClient = MongoClients.create(clientSettings);
		MongoDatabase database = mongoClient.getDatabase("test");
		coleccion = database.getCollection("restaurantes", Restaurante.class);

	}

	public MongoCollection<Restaurante> getColeccion() {
		return coleccion;
	}
	
	
	
	
	
}
