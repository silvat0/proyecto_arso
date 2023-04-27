package arso.repositorio;

import java.util.LinkedList;
import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import arso.restaurantes.modelo.Restaurante;
import arso.utils.Utils;

public class RepositorioMongo<T extends Identificable> implements RepositorioString<T> {
	
	
	
	ConnectionString connectionString = new ConnectionString("mongodb+srv://arso:Arso2023@cluch.2l0gzgu.mongodb.net/?retryWrites=true&w=majority");
	//ConnectionString connectionString = new ConnectionString("mongodb+srv://raul:arso@cluster0.n9oo3nv.mongodb.net/?retryWrites=true&w=majority");
	MongoClientSettings settings = MongoClientSettings.builder()
	        .applyConnectionString(connectionString)
	        .build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("test");
	
	MongoCollection<Restaurante> coleccion = database.getCollection("restaurantes", Restaurante.class);
	
	
	
	@Override
	public String add(T entity) throws RepositorioException{
		
		String id = Utils.createId();
		entity.setId(id);
		coleccion.insertOne((Restaurante) entity);
		
		return id;
	}

	@Override
	public void update(T entity) throws RepositorioException, EntidadNoEncontrada {
		
		Bson query = Filters.eq("id", entity.getId());
		
		if (query == null) {
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		}
		
		coleccion.findOneAndReplace(query, (Restaurante) entity);
	
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		Bson query = Filters.eq("id", entity.getId());

		if (query == null) {
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		}
		
		coleccion.deleteOne(query);

	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		
		Bson query = Filters.eq("id", id);
		
		if (query == null) {
			throw new EntidadNoEncontrada("El restaurante con id " + id + " no existe en el repositorio");
		}
		
		Restaurante restaurante = null;
		
		for (Restaurante r : coleccion.find(query)) {
			restaurante = r;
		}
		
		return (T) restaurante;
		
	}

	@Override
	public List<T> getAll() throws RepositorioException {
		
		List<Restaurante> restaurantes = new LinkedList<>();
		
		FindIterable<Restaurante> rests = coleccion.find();
		
		for (Restaurante r : rests) {
			restaurantes.add(r);
		}
		
		return (List<T>) restaurantes;
		
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		
		List<String> ids = new LinkedList<>();

		FindIterable<Restaurante> rests = coleccion.find();

		for (Restaurante r : rests) {
			ids.add(r.getId());
		}

		return ids;


	}


	
	

}
