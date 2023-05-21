package arso.restaurantes.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import arso.eventos.modelo.Opinion;
import arso.eventos.servicios.IOpinionesRestMock;
import arso.repositorio.EntidadEncontrada;
import arso.repositorio.EntidadNoEncontrada;
import arso.repositorio.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.servicios.ServicioRestaurante;

public class ServicioRestauranteTest {
	
	ServicioRestaurante servicio = new ServicioRestaurante();
	
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	private final IOpinionesRestMock OpinionRestMock = context.mock(IOpinionesRestMock.class);
	
	@Before
	public void setUp() throws Exception {
		servicio = new ServicioRestaurante();
	}
	
	
	@Test
	public void testCreateRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		assertNotNull(servicio.create(restaurante));
		servicio.removeRestaurante(restaurante.getId());
	}
	
	@Test
	public void testUpdateRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		servicio.update(idRestaurante, "Restaurante Emboka Actualizada", "JUAN DE BORBON, 29, 30007 Murcia");
		assertEquals("Restaurante Emboka Actualizada", servicio.getRestaurante(idRestaurante).getNombre());
		servicio.removeRestaurante(restaurante.getId());
	}
	
	
	@Test(expected = EntidadNoEncontrada.class)
    public void testEntidadNoEncontrada() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		servicio.update(idRestaurante, "Restaurante Emboka Actualizada", "JUAN DE BORBON, 29, 30007 Murcia");
		servicio.getRestaurante("646a33efc9d38a3189784a45").getNombre();
		
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
	public void tesAddPlato() throws RepositorioException, EntidadNoEncontrada, EntidadEncontrada{
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
		servicio.removeRestaurante(restaurante.getId());
	}
	
	
	
	@Test
	public void tesRemovePlato() throws RepositorioException, EntidadNoEncontrada, EntidadEncontrada{
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
		servicio.removeRestaurante(restaurante.getId());
	}
	
	@Test
	public void testUpdatePlato() throws RepositorioException, EntidadNoEncontrada, EntidadEncontrada{
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
		servicio.removeRestaurante(restaurante.getId());
	}
	
	@Test
	public void testGetRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		assertEquals(servicio.getRestaurante(idRestaurante), restaurante);
		servicio.removeRestaurante(restaurante.getId());
	}
	
	@Test(expected = EntidadNoEncontrada.class)
	public void testRemoveRestaurante() throws RepositorioException, EntidadNoEncontrada{
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");
		String idRestaurante = servicio.create(restaurante);
		
		servicio.removeRestaurante(idRestaurante);
		
		servicio.getRestaurante(idRestaurante);
		servicio.removeRestaurante(restaurante.getId());
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
		servicio.removeRestaurante(restaurante1.getId());
		servicio.removeRestaurante(restaurante2.getId());
	}
	
	@Test
	public void testCreateOpinion() throws RepositorioException, EntidadNoEncontrada, IOException {

		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("-55, 66");
		String id = servicio.create(restaurante);
		
		final String expectedReturnValue = null;
		
		context.checking(new Expectations() {{
			oneOf(OpinionRestMock).create(restaurante.getNombre());
			will(returnValue(expectedReturnValue));
		}});
		
		String actualReturnValue = OpinionRestMock.create(restaurante.getNombre());
		
		assertEquals(expectedReturnValue, actualReturnValue);
		
		servicio.removeRestaurante(id);
	}

	@Test
	public void testGetOpinion() throws RepositorioException, EntidadNoEncontrada {

		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("-55, 66");
		String id = servicio.create(restaurante);
		//String idOpinion = OpinionRestMock.create(restaurante.getNombre());
		String idOpinion = "1";
		Opinion opinion = new Opinion();
		opinion.setId(idOpinion);
		opinion.setMediaValoraciones(0);
		opinion.setNumValoraciones(0);

		context.checking(new Expectations() {
			{	
				oneOf(OpinionRestMock).getOpinion(idOpinion);
				will(returnValue(opinion));
			}
		});

		Opinion actualReturnValue = OpinionRestMock.getOpinion(idOpinion);
		
		assertEquals(opinion, actualReturnValue);

		servicio.removeRestaurante(id);
	}
	

}