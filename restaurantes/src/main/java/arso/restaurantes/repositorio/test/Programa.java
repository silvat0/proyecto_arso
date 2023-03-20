package arso.restaurantes.repositorio.test;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Programa {

	public static void main(String[] args) {
		
		ConnectionString connectionString = new ConnectionString("mongodb+srv://arso:arso@cluch.2l0gzgu.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("test");
		
		System.out.println("fin. ");

	}

}
