package arso.restaurantes.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.servicios.ServicioRestaurante;

public class ServicioRestauranteTest {
	
	ServicioRestaurante servicio;
	
	@Before
	public void setUp() throws Exception {
		servicio = new ServicioRestaurante();
	}
	
	
	
	@Test
	public void testDarAltaRestaurante() throws RepositorioException{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		
		assertNotNull(servicio.create(restaurante));
	}
	

}