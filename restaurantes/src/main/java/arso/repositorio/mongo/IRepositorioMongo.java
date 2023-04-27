package arso.repositorio.mongo;

import java.util.List;

import arso.restaurantes.modelo.Restaurante;

public interface IRepositorioMongo {
	
	String add(Restaurante restaurante) throws RepositorioException;

	void update(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada;

	void delete(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada;

	Restaurante getById(String id) throws RepositorioException, EntidadNoEncontrada;

	List<Restaurante> getAll() throws RepositorioException;

	List<String> getIds() throws RepositorioException;

}
