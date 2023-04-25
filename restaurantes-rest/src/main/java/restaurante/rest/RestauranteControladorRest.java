package restaurante.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import arso.restaurantes.servicios.FactoriaServicios;
import arso.restaurantes.servicios.IServicioRestaurante;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import arso.restaurantes.servicios.RestauranteResumen;
import arso.restaurantes.modelo.Plato;
import io.swagger.annotations.Api;

@Api
@Path("restaurantes")
public class RestauranteControladorRest {
	
	private IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Restaurante restaurente) throws Exception {

		String id = servicio.create(restaurente);
		
		URI nuevaURL = uriInfo.getAbsolutePathBuilder().path(id).build();

		return Response.created(nuevaURL).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id1, String id2, String nombre, String coordenadas) throws Exception {

		if (!id1.equals(id2))
			throw new IllegalArgumentException("El identificador no coincide: " + id1);

		servicio.update(id2, nombre, coordenadas);

		return Response.status(Response.Status.NO_CONTENT).build();

	}
	
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPlato(@PathParam("id") String idRestaurante, Plato plato) throws Exception{
		
		servicio.addPlato(idRestaurante, plato);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removePlato(@PathParam("id") String idRestaurante, @PathParam("nombre") String nombrePlato) throws Exception {
		
		servicio.removePlato(idRestaurante, nombrePlato);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updatePlato(@PathParam("id") String idRestaurante, Plato plato) throws Exception {
		
	}
	
	
	// (6) 
	
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRestaurante( @PathParam("id") String id ) throws Exception {
		
		return Response.status(Response.Status.OK).entity(servicio.getRestaurante(id)).build();	
	}
	
	
	// (7)
	
	@DELETE
	@Path("/{id}")
	public Response removeRestaurante( @PathParam("id") String id ) throws Exception {
		
		servicio.removeRestaurante(id);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
	// (8)
	
	@GET
	@Path("/{id}/SitiosTuristicos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSitiosTuristicos( @PathParam("id") String id ) throws Exception {
		
		List<SitioTuristico> sitios = servicio.getSitiosTuristidos(id);
		
		return Response.ok(sitios).build();
	}
	
	
	// (9)
	
	
	// (10)
	
	
}
