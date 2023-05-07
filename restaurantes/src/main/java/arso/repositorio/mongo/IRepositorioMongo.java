package arso.repositorio.mongo;

import java.util.List;
import arso.repositorio.memoria.RepositorioException;
import arso.repositorio.memoria.RepositorioString;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.restaurantes.modelo.Restaurante;

public interface IRepositorioMongo extends RepositorioString<Restaurante>{
	
	String add(Restaurante restaurante) throws RepositorioException;

	void update(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada;

	void delete(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada;

	Restaurante getById(String id) throws RepositorioException, EntidadNoEncontrada;

	List<Restaurante> getAll() throws RepositorioException;

	List<String> getIds() throws RepositorioException;

}
