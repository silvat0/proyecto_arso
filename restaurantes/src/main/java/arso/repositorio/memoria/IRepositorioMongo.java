package arso.repositorio.memoria;

import java.util.List;

import arso.restaurantes.modelo.Restaurante;

public interface IRepositorioMongo {
	
	String add(Restaurante restaurante) throws RepositorioException;

	void update(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada;

	void delete(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada;

	String getById(String id) throws RepositorioException, EntidadNoEncontrada;

	List<Restaurante> getAll() throws RepositorioException;

	List<Restaurante> getIds() throws RepositorioException;

}
