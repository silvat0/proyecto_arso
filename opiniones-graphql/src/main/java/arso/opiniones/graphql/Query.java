package arso.opiniones.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.servicios.OpinionResumen;
import arso.opiniones.servicios.FactoriaServicios;
import arso.opiniones.servicios.IServicioOpiniones;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;

public class Query implements GraphQLRootResolver {
	
	private IServicioOpiniones servicio = FactoriaServicios.getServicio(IServicioOpiniones.class);
	
	public Opinion getOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada {
		
		return servicio.getOpinion(idOpinion);
		
	}
	
	public List<OpinionResumen> getListadoOpiniones() throws RepositorioException {
		
		return servicio.getListadoOpiniones();
			
	}

}
