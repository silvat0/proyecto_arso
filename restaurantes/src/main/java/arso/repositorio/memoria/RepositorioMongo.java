package arso.repositorio.memoria;

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

import arso.repositorio.memoria.RepositorioException;
import arso.repositorio.memoria.RepositorioString;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.restaurantes.modelo.Restaurante;
import arso.utils.Utils;

public class RepositorioMongo implements RepositorioString<Restaurante>{
	
	//MongoPojo pojo = new MongoPojo();
	
	//MongoCollection<Restaurante> coleccion = pojo.getColeccion();
	
	private MongoCollection<Restaurante> coleccion;
	
	public RepositorioMongo() {
		
		coleccion = getColeccion();
	}
	
	public MongoCollection<Restaurante> getColeccion() {
	
		ConnectionString connectionString = new ConnectionString("mongodb+srv://arso:UPHcABdBP1YhNZSq@cluch.2l0gzgu.mongodb.net/?retryWrites=true&w=majority");
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		MongoClientSettings clientSettings = MongoClientSettings.builder()
	            .applyConnectionString(connectionString)
	            .codecRegistry(codecRegistry)
	            .build();
		
		MongoClient mongoClient = MongoClients.create(clientSettings);
		MongoDatabase database = mongoClient.getDatabase("test");
		coleccion = database.getCollection("restaurantes", Restaurante.class);
		
		return coleccion;
	}
	
	
	
	@Override
	public String add(Restaurante restaurante) throws RepositorioException {
		
		String id = Utils.createId();
		restaurante.setId(id);
		coleccion.insertOne(restaurante);
		
		
		return id;
	}

	@Override
	public void update(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada {
		
		Bson query = Filters.eq("_id", restaurante.getId());
		
		if (query == null) {
			throw new EntidadNoEncontrada(restaurante.getId() + " no existe en el repositorio");
		}
		
		coleccion.findOneAndReplace(query, restaurante);
	
	}

	@Override
	public void delete(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada {
		Bson query = Filters.eq("_id", restaurante.getId());

		if (query == null) {
			throw new EntidadNoEncontrada(restaurante.getId() + " no existe en el repositorio");
		}
		
		coleccion.deleteOne(query);

	}

	@Override
	public Restaurante getById(String id) throws RepositorioException, EntidadNoEncontrada {
		
		Bson query = Filters.eq("_id", id);
		
		if (query == null) {
			throw new EntidadNoEncontrada("El restaurante con id " + id + " no existe en el repositorio");
		}
		
		Restaurante restaurante = coleccion.find(query).first();
		
		return restaurante;
		
	}

	@Override
	public List<Restaurante> getAll() throws RepositorioException {
		
		List<Restaurante> restaurantes = new LinkedList<>();
		
		FindIterable<Restaurante> rests = coleccion.find();
		
		for (Restaurante r : rests) {
			restaurantes.add(r);
		}
		
		return restaurantes;
		
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
