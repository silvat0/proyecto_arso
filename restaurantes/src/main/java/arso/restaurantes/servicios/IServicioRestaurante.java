package arso.restaurantes.servicios;

import java.util.List;

import arso.repositorio.EntidadEncontrada;
import arso.repositorio.EntidadNoEncontrada;
import arso.repositorio.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;

public interface IServicioRestaurante {

	// Metodo para dar de alta a un restaurante
	String create(Restaurante restaurente) throws RepositorioException;
	
	// Metodo para actulizar un restaurante
	void update(String restaurante, String nombre, String coordenadas) throws RepositorioException, EntidadNoEncontrada;
	// Obtener sitios turísticos próximos de un restaurante
	List<SitioTuristico> getSitiosTuristidos(String idRestaurante) throws RepositorioException, EntidadNoEncontrada;
	
	// Establecer sitios turísticos destacados
	void establecerSitiosTuristicos(String idRestaurante, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada;
	
	// Añadir plato a un restaurante
	boolean addPlato(String idRestaurante, Plato plato) throws RepositorioException, EntidadNoEncontrada, EntidadEncontrada;
	// Borrar plato de un restaurante
	boolean removePlato(String idRestaurante, String plato) throws RepositorioException, EntidadNoEncontrada;
	
	// Actualizar un plato del restaurante 
	void updatePlato(String idRestaurante, Plato plato) throws RepositorioException, EntidadNoEncontrada;
	
	// Recuperar un restaurante
	Restaurante getRestaurante(String idRestaurante) throws RepositorioException, EntidadNoEncontrada;
	
	// Borrar un restaurante
	void removeRestaurante(String idRestaurante) throws RepositorioException, EntidadNoEncontrada;
	
	// Listado de restaurantes
	List<RestauranteResumen> getListadoRestaurantes() throws RepositorioException;
	
	
	
	
	
	
}
