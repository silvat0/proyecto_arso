package arso.restaurantes.servicios;

import java.util.List;

import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;

public interface IServicioRestaurante {

	// Metodo para dar de alta a un restaurante
	int create(Restaurante restaurente);
	
	// Metodo para actulizar un restaurante
	void update(Restaurante restaurante);
	
	// Obtener sitios turísticos próximos de un restaurante
	List<SitioTuristico> getSitiosTuristidos(int idRestaurante);
	
	// Establecer sitios turísticos destacados
	void establecerSitiosTuristicos(int idRestaurante, List<SitioTuristico> sitiosTuristicos);
	
	// Añadir plato a un restaurante
	void addPlato(int idRestaurante, Plato plato);
	
	// Borrar plato de un restaurante
	void removePlato(int idRestaurante, String plato);
	
	// Actualizar un plato del restaurante 
	void updatePlato(int idRestaurante, Plato plato);
	
	// Recuperar un restaurante
	Restaurante getRestaurante(int idRestaurante);
	
	// Borrar un restaurante
	void removeRestaurante(int idRestaurante);
	
	// Listado de restaurantes
	List<RestauranteResumen> getListadoRestaurantes();
	
	
	
	
	
	
}
