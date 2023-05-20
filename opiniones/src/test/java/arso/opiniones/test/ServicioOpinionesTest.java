package arso.opiniones.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.opiniones.servicios.ServicioOpiniones;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;

public class ServicioOpinionesTest {

	ServicioOpiniones servicio = new ServicioOpiniones();
	
	@Before
	public void setUp() throws Exception {
		servicio = new ServicioOpiniones();
	}
	
	@Test
	public void testCreateOpinion() throws RepositorioException{
		Opinion opinion = new Opinion();
		opinion.setRecurso("Restaurante Emboka");
		assertNotNull(servicio.create("Restaurante Emboka"));
	}
	
	@Test
	public void testAddValoracion() throws RepositorioException, EntidadNoEncontrada{
		Opinion opinion = new Opinion();
		opinion.setRecurso("Restaurante Emboka");
		String id = servicio.create("Restaurante Emboka");
		
		Valoracion valoracion = new Valoracion();
		valoracion.setCorreo("pedro@um.es");
		valoracion.setComentario("comentario valoracion");
		valoracion.setCalificacion(2);
		valoracion.setFecha(LocalDate.now());
		servicio.addValoracion(id, valoracion);
		assertTrue(servicio.getOpinion(id).getValoraciones().size() > 0);
	}
	
	//@Test
	/*public void testGetOpinon() throws RepositorioException, EntidadNoEncontrada{
		Opinion opinion = new Opinion();
		opinion.setRecurso("Restaurante Emboka");
		String id = servicio.create(opinion);
		
		assertEquals(servicio.getOpinion(id), opinion);
	}*/
	
	@Test(expected = EntidadNoEncontrada.class)
	public void testRemoveOpinion() throws RepositorioException, EntidadNoEncontrada{
		Opinion opinion = new Opinion();
		opinion.setRecurso("Restaurante Emboka");
		String id = servicio.create("Restaurante Emboka");
		
		servicio.removeOpinion(id);
		
		servicio.getOpinion(id);
	}
	
	@Test(expected = EntidadNoEncontrada.class)
    public void testEntidadNoEncontrada() throws RepositorioException, EntidadNoEncontrada{
        servicio.getOpinion("id");
    }
	
	

}
