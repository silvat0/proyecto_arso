package restaurantes.rest;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import arso.restaurantes.servicios.FactoriaServicios;
import arso.restaurantes.servicios.IServicioRestaurante;
import arso.restaurantes.servicios.RestauranteResumen;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api
@Path("restaurantes")
public class RestauranteControladorRest {
	
	private IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
	
	@Context
	private UriInfo uriInfo;
	
	// (1)
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Crea un restaurante", notes = "Retorna un 201 created si se ha podido crear el objeto restaurante")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "") })
	public Response create(@ApiParam(value = "restaurante que se quiere crear", required = true) Restaurante restaurente) throws Exception {

		String id = servicio.create(restaurente);
		
		URI nuevaURL = uriInfo.getAbsolutePathBuilder().path(id).build();

		return Response.created(nuevaURL).build();
	}
	
	// (2)
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Actualiza un restaurante", notes = "Retorna un 204 no content indicando que todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "El identificador no coincide") })
	public Response update(@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id1, 
							@ApiParam(value = "id del restaurante", required = true) String id2, 
							@ApiParam(value = "nombre del restaurante", required = true) String nombre, 
							@ApiParam(value = "coordenadas del restaurante", required = true) String coordenadas) throws Exception {

		if (!id1.equals(id2))
			throw new IllegalArgumentException("El identificador no coincide: " + id1);

		servicio.update(id2, nombre, coordenadas);

		return Response.status(Response.Status.NO_CONTENT).build();

	}
	
	// (3)
	
	@POST
	@Path("/{id}/Platos")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Añade un plato a un restaurante", notes = "Retorna un 204 no content indicando que todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "El id no existe en el repositorio") })
	public Response addPlato(@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String idRestaurante, 
							 @ApiParam(value = "plato a añadir", required = true) Plato plato) throws Exception{

		servicio.addPlato(idRestaurante, plato);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{id}/Platos/{nombre}")
	public Response removePlato(@PathParam("id") String idRestaurante, @PathParam("nombre") String nombrePlato) throws Exception {
		
		servicio.removePlato(idRestaurante, nombrePlato);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("/{id}/Platos/{nombre}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updatePlato(@PathParam("id") String idRestaurante, @PathParam("nombre") String nombrePlato, Plato plato) throws Exception {
		
		if (!nombrePlato.equals(plato.getNombre()))
			throw new IllegalArgumentException("El nombre del plato no coincide: " + nombrePlato);

		servicio.updatePlato(idRestaurante, plato);

		return Response.status(Response.Status.NO_CONTENT).build();
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
	
	@PUT
	@Path("/{id}/SitiosTuristicos")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response establecerSitiosTuristicos( @PathParam("id") String id, List<SitioTuristico> sitiosTuristicos) throws Exception {
		
		servicio.establecerSitiosTuristicos(id, sitiosTuristicos);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListadoRestaurantes() throws Exception {

		List<RestauranteResumen> resultado = servicio.getListadoRestaurantes();

		LinkedList<ResumenExtendido> extendido = new LinkedList<ResumenExtendido>();

		for (RestauranteResumen restauranteResumen : resultado) {

			ResumenExtendido resumenExtendido = new ResumenExtendido();

			resumenExtendido.setResumen(restauranteResumen);

			String id = restauranteResumen.getId();
			URI nuevaURL = uriInfo.getAbsolutePathBuilder().path(id).build();

			resumenExtendido.setUrl(nuevaURL.toString());

			extendido.add(resumenExtendido);

		}	

		return Response.ok(extendido).build();

	}
	
	
	
}
