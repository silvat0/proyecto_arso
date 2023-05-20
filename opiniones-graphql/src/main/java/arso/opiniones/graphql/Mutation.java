package arso.opiniones.graphql;

import java.time.LocalDate;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.opiniones.servicios.FactoriaServicios;
import arso.opiniones.servicios.IServicioOpiniones;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;

public class Mutation implements GraphQLRootResolver {
	
	private IServicioOpiniones servicio = FactoriaServicios.getServicio(IServicioOpiniones.class);
	
	public String create(String recurso) throws RepositorioException {
		
//		Opinion opinion = new Opinion();
//		
//		opinion.setRecurso(recurso);
		
		return servicio.create(recurso);
	}
	
	
	public boolean addValoracion(String id, String correo, int calificacion, String comentario) throws RepositorioException, EntidadNoEncontrada {
		
		Valoracion valoracion = new Valoracion();
		
		valoracion.setCalificacion(calificacion);
		valoracion.setComentario(comentario);
		valoracion.setCorreo(correo);
		valoracion.setFecha(LocalDate.now());
		
		return servicio.addValoracion(id, valoracion);
		
	}
	
	public boolean removeOpinion(String id) throws RepositorioException, EntidadNoEncontrada {
		
		return servicio.removeOpinion(id);
	}

}
