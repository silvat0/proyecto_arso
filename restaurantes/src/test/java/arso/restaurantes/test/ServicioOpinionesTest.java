package arso.restaurantes.test;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.retrofit.OpinionesRest;
import arso.restaurantes.servicios.ServicioRestaurante;

class ServicioOpinionesTest {
	
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	private final OpinionesRest opinionesRestMock = context.mock(OpinionesRest.class);
	
	ServicioRestaurante servicio = new ServicioRestaurante();
	
	@Before
	public void setUp() throws Exception {
		servicio = new ServicioRestaurante();
	}
	
	@Test
	public void testCreate() throws RepositorioException, EntidadNoEncontrada, IOException {

		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("-55, 66");
		String id = servicio.create(restaurante);
		
		context.checking(new Expectations() {
			{
				oneOf(opinionesRestMock).create(restaurante.getNombre());
			}
		});

		opinionesRestMock.create(restaurante.getNombre());
		
		context.assertIsSatisfied();
		
		servicio.removeRestaurante(id);
	}
	
	@Test
	public void testGetOpinion() throws RepositorioException, EntidadNoEncontrada {

		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("-55, 66");
		String id = servicio.create(restaurante);

		context.checking(new Expectations() {
			{
				oneOf(opinionesRestMock).getOpinion(id);
			}
		});

		opinionesRestMock.getOpinion(restaurante.getNombre());
		
		context.assertIsSatisfied();
		
		servicio.removeRestaurante(id);
	}

}
