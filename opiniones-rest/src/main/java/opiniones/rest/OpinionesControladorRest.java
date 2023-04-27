package opiniones.rest;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.opiniones.servicios.FactoriaServicios;
import arso.opiniones.servicios.IServicioOpiniones;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@Path("opiniones")
public class OpinionesControladorRest {

	private IServicioOpiniones servicio = FactoriaServicios.getServicio(IServicioOpiniones.class);
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Crea una opinion", notes = "Retorna el codigo 201 indicando que ha creado el recurso")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "")})
	public Response create(@ApiParam(value = "Opinión a crear", required = true) Opinion opinion) throws Exception {

		String id = servicio.create(opinion);
		
		URI nuevaURL = uriInfo.getAbsolutePathBuilder().path(id).build();

		return Response.created(nuevaURL).build();
	}
	
	@POST
	@Path("/{id}/valoraciones")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Añade una valoración", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Opinión no encontrada")})
	
	public Response addValoracion( @ApiParam(value = "id de la opinión", required = true) @PathParam("id") String idOpinion, 
			@ApiParam(value = "Valoración a añadir", required = true) Valoracion valoracion) throws Exception{

		servicio.addValoracion(idOpinion, valoracion);

		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Obtener una opinion", notes = "Retorna la opinión por el id dado", response = Opinion.class)
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Opinión no encontrada") })
	public Response getOpinion(@ApiParam(value = "id de la opinión", required = true) @PathParam("id") String id ) throws Exception {
		
		return Response.status(Response.Status.OK).entity(servicio.getOpinion(id)).build();	
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Eliminar una opinion", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Opinión no encontrada") })
	public Response removeOpinion(@ApiParam(value = "id de la opinión", required = true) @PathParam("id") String id ) throws Exception {
		
		servicio.removeOpinion(id);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
}
