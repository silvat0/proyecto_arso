package rest.test;

import static org.junit.Assert.*;

import org.junit.Test;

import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Restaurante;
import restaurantes.rest.RestauranteControladorRest;

public class RestTest {

	RestauranteControladorRest restauranteControladorRest = new RestauranteControladorRest();
	
	@Test
	public void testCreate() throws RepositorioException{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		restauranteControladorRest.create(restaurante);
	}

}
