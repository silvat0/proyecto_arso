package arso.opiniones.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.servicios.FactoriaServicios;
import arso.opiniones.servicios.IServicioOpiniones;
import arso.repositorio.memoria.RepositorioException;

public class Mutation implements GraphQLRootResolver {
	
	private IServicioOpiniones servicio = FactoriaServicios.getServicio(IServicioOpiniones.class);
	
	public String create(Opinion opinion) throws RepositorioException {
		
		return servicio.create(opinion);
	}

}
