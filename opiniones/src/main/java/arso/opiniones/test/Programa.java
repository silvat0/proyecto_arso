package arso.opiniones.test;

import arso.opiniones.servicios.ServicioOpiniones;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;

import java.time.LocalDate;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;

public class Programa {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {
		
		ServicioOpiniones servicio = new ServicioOpiniones();
		
		Opinion opinion1 = new Opinion();
		opinion1.setRecurso("@pieroti");
		
		Opinion opinion2 = new Opinion();
		opinion2.setRecurso("@burguer");
		
		Valoracion valoracion1 = new Valoracion();
		valoracion1.setCalificacion(3);
		valoracion1.setComentario("Muy bueno pero servicio lento");
		valoracion1.setCorreo("silvia@um.es");
		valoracion1.setFecha(LocalDate.now());
		
		
		Valoracion valoracion2 = new Valoracion();
		valoracion2.setCalificacion(4);
		valoracion2.setComentario("Todo buenisimo");
		valoracion2.setCorreo("raul@um.es");
		valoracion2.setFecha(LocalDate.now());
		
		Valoracion valoracion3 = new Valoracion();
		valoracion3.setCalificacion(2);
		valoracion3.setComentario("La hamburguesa estaba fria");
		valoracion3.setCorreo("felipe@um.es");
		valoracion3.setFecha(LocalDate.now());
		
		Valoracion valoracion5 = new Valoracion();
		valoracion5.setCalificacion(2);
		valoracion5.setComentario("La pizza estaba fria");
		valoracion5.setCorreo("silvia@um.es");
		valoracion5.setFecha(LocalDate.now());
		
		
		Valoracion valoracion4 = new Valoracion();
		valoracion4.setCalificacion(4);
		valoracion4.setComentario("Todo buenisimo");
		valoracion4.setCorreo("gon@um.es");
		valoracion4.setFecha(LocalDate.now());
		
		String id1 = servicio.create("@pieroti");
		String id2 = servicio.create("@burguer");
		
		servicio.addValoracion(id1, valoracion1);
		servicio.addValoracion(id1, valoracion2);
		servicio.addValoracion(id1, valoracion5);
		
		servicio.addValoracion(id2, valoracion3);
		servicio.addValoracion(id2, valoracion4);
		
		
		
		

	}

}
