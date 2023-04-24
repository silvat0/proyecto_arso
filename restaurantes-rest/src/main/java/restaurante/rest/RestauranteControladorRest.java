package restaurante.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import arso.restaurantes.servicios.FactoriaServicios;
import arso.restaurantes.servicios.IServicioRestaurante;
import io.swagger.annotations.Api;

@Api
@Path("restaurantes")
public class RestauranteControladorRest {
	
	private IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Path("/{id}")s
	@Produces(MediaType.APPLICATION_XML);
	
}
