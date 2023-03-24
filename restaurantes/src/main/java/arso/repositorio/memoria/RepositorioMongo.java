package arso.repositorio.memoria;

import java.util.List;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import arso.restaurantes.modelo.Restaurante;
import arso.utils.Utils;

public class RepositorioMongo implements IRepositorioMongo{
	
	
	
	ConnectionString connectionString = new ConnectionString("mongodb+srv://arso:<password>@cluch.2l0gzgu.mongodb.net/?retryWrites=true&w=majority");
	MongoClientSettings settings = MongoClientSettings.builder()
	        .applyConnectionString(connectionString)
	        .build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("test");
	MongoCollection<Restaurante> coleccion = database.getCollection("restaurantes", Restaurante.class);
	
	
	@Override
	public String add(Restaurante restaurante) throws RepositorioException {
		
		String id = Utils.createId();
		restaurante.setId(id);
		coleccion.insertOne(restaurante);
		
		
		return id;
	}

	@Override
	public void update(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getById(String id) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurante> getAll() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurante> getIds() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
