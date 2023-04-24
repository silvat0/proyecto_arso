package arso.restaurantes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.servicios.ServicioRestaurante;

public class ServicioRestauranteTest {
	
	ServicioRestaurante servicio = new ServicioRestaurante();
	
	@Before
	public void setUp() throws Exception {
		servicio = new ServicioRestaurante();
	}
	
	
	
	@Test
	public void testCreateRestaurante() throws RepositorioException{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		assertNotNull(servicio.create(restaurante));
	}
	
	@Test
	public void testUpdateRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		servicio.update(idRestaurante, "Restaurante Emboka Actualizada", "JUAN DE BORBON, 29, 30007 Murcia");
		assertEquals("Restaurante Emboka Actualizada", servicio.getRestaurante(idRestaurante).getNombre());
	}
	
	
//	@Test
//	public void testGetSitiosTuristidos() throws RepositorioException, EntidadNoEncontrada{
//		
//	}
	
//	@Test
//	public void testEstablecerSitiosTuristicos() throws RepositorioException, EntidadNoEncontrada{
//		
//	}
	
	@Test
	public void tesAddPlato() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		Plato plato = new Plato();
		plato.setNombre("VIOLETA POCHE");
		plato.setDescripcion("Huevo trufado con patata francesa violeta y seta de cardo emulsionada con aceite de trufa\r\n"
				+ "\r\n"
				+ "puede ser sin gluten\r\n"
				+ "\r\n"
				+ "contine huevo");
		plato.setPrecio(14.95);
		
		servicio.addPlato(idRestaurante, plato);
		
		assertTrue(servicio.getRestaurante(idRestaurante).getPlatos().size() > 0);
	}
	
	@Test
	public void tesRemovePlato() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		Plato plato = new Plato();
		plato.setNombre("VIOLETA POCHE");
		plato.setDescripcion("Huevo trufado con patata francesa violeta y seta de cardo emulsionada con aceite de trufa\r\n"
				+ "\r\n"
				+ "puede ser sin gluten\r\n"
				+ "\r\n"
				+ "contine huevo");
		plato.setPrecio(14.95);
		
		servicio.addPlato(idRestaurante, plato);
		servicio.removePlato(idRestaurante, "VIOLETA POCHE");
		
		assertTrue(servicio.getRestaurante(idRestaurante).getPlatos().size() == 0);
	}
	
	@Test
	public void testUpdatePlato() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		Plato plato1 = new Plato();
		plato1.setNombre("VIOLETA POCHE");
		plato1.setDescripcion("Huevo trufado con patata francesa violeta y seta de cardo emulsionada con aceite de trufa\r\n"
				+ "\r\n"
				+ "puede ser sin gluten\r\n"
				+ "\r\n"
				+ "contine huevo");
		plato1.setPrecio(14.95);
		
		Plato plato2 = new Plato();
		plato2.setNombre("VIOLETA POCHE");
		plato2.setDescripcion("Huevo trufado con patata francesa violeta y seta de cardo emulsionada con aceite de trufa\r\n"
				+ "\r\n"
				+ "puede ser sin gluten\r\n"
				+ "\r\n"
				+ "contine huevo");
		plato2.setPrecio(12);
		
		servicio.addPlato(idRestaurante, plato1);
		servicio.updatePlato(idRestaurante, plato2);
		
		assertTrue(servicio.getRestaurante(idRestaurante).getPlatos().get(0).getPrecio() == 12);
	}
	
	@Test
	public void testGetRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		assertEquals(servicio.getRestaurante(idRestaurante), restaurante);
	}
	
	@Test(expected = EntidadNoEncontrada.class)
	public void testRemoveRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		servicio.removeRestaurante(idRestaurante);
		
		servicio.getRestaurante(idRestaurante);
	}
	
	@Test
	public void testGetListadoRestaurantes() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNombre("Restaurante Emboka");
		restaurante1.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia"); 
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNombre("Restaurante El Patio I");
		restaurante2.setCoordenadas("Av. de la Inmaculada, 3, 30007 Murcia");
		servicio.create(restaurante1);
		servicio.create(restaurante2);
		assertNotNull(servicio.getListadoRestaurantes());
	}
	
	

}