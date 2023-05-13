package arso.repositorio.memoria;

import java.util.List;

//Metodos basicos para interactuar con la base de datos.

public interface IRepositorio<T, K> {

	K add(T entity) throws RepositorioException;

	void update(T entity) throws RepositorioException, EntidadNoEncontrada;

	boolean delete(T entity) throws RepositorioException, EntidadNoEncontrada;

	T getById(K id) throws RepositorioException, EntidadNoEncontrada;

	List<T> getAll() throws RepositorioException;

	List<K> getIds() throws RepositorioException;

}
