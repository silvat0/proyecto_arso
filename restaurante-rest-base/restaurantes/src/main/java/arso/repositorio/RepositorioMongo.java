package arso.repositorio;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.LinkedList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;

public class RepositorioMongo <T extends Identificable> implements RepositorioString<T> {
	
	private MongoCollection<T> coleccion;
	
	
	
	public RepositorioMongo(Class<T> clazz) {
		
		coleccion = getColeccion(clazz);
	}
	
	public MongoCollection<T> getColeccion(Class<T> clazz) {
	
		ConnectionString connectionString = new ConnectionString("mongodb://arso:dSttrG0xMgQz0utM@ac-ydw58ag-shard-00-00.2l0gzgu.mongodb.net:27017,ac-ydw58ag-shard-00-01.2l0gzgu.mongodb.net:27017,ac-ydw58ag-shard-00-02.2l0gzgu.mongodb.net:27017/?ssl=true&replicaSet=atlas-6ph0y9-shard-0&authSource=admin&retryWrites=true&w=majority");
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		MongoClientSettings clientSettings = MongoClientSettings.builder()
	            .applyConnectionString(connectionString)
	            .codecRegistry(codecRegistry)
	            .build();
		
		
		MongoClient mongoClient = MongoClients.create(clientSettings);
		MongoDatabase database = mongoClient.getDatabase("restaurantes");
		coleccion = database.getCollection("Restaurantes", clazz);
		

		return coleccion;
	}
	
	
	
	@Override
	public String add(T entity) throws RepositorioException {
		
	    return coleccion.insertOne(entity).getInsertedId().asObjectId().getValue().toString();
	}

	@Override
	public void update(T entity) throws RepositorioException, EntidadNoEncontrada {
		
		ObjectId objectId = new ObjectId( entity.getId());
		Bson query = Filters.eq("_id", objectId);
		
		T resul = coleccion.findOneAndReplace(query, (T) entity);
		
		if (resul == null) {
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		}
	
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		ObjectId objectId = new ObjectId( entity.getId());
		Bson query = Filters.eq("_id", objectId);

		DeleteResult resul = coleccion.deleteOne(query);
		
		if (resul == null) {
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		}

	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		
		ObjectId objectId = new ObjectId(id);
		Bson query = Filters.eq("_id", objectId);
		
		T entity = (T) coleccion.find(query).first();
		
		if (entity == null) {
		    throw new EntidadNoEncontrada("El restaurante con id " + id + " no existe en el repositorio");
		}

		
		return entity;
		
	}

	@Override
	public List<T> getAll() throws RepositorioException {
		
		List<T> entitites = new LinkedList<>();
		
		FindIterable<T> rests = (FindIterable<T>) coleccion.find();
		
		for (T e : rests) {
			entitites.add(e);
		}
		
		return (List<T>) entitites;
		
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		
		List<String> ids = new LinkedList<>();

		FindIterable<T> rests = (FindIterable<T>) coleccion.find();

		for (T r : rests) {
			ids.add(r.getId());
		}

		return ids;


	}
	
	

}
