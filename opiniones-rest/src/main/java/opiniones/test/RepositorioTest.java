package opiniones.test;

import java.time.LocalDate;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.repositorio.memoria.RepositorioException;
import arso.repositorio.memoria.RepositorioMemoria;


public class RepositorioTest extends RepositorioMemoria<Opinion> {

	public RepositorioTest() {
		
		 // Opinión 1
        
   		Opinion opinion1 = new Opinion();
   		opinion1.setRecurso("Restaurante");
   		

		// Valoración 1

		Valoracion valoracion1 = new Valoracion();		
		valoracion1.setCorreo("pedro@um.es");
		valoracion1.setCalificacion(4);
		valoracion1.setComentario("Todo muy bien");
		valoracion1.setFecha(LocalDate.now());
		
		opinion1.addValoracion(valoracion1);
		
		try {
			add(opinion1);
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
		
	}
	
}
