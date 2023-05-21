package restaurantes.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import arso.repositorio.GestorNoAutorizado;

@Provider
public class TratamientoGestorNoAutorizado implements ExceptionMapper<GestorNoAutorizado> {

	@Override
	public Response toResponse(GestorNoAutorizado exception) {
		
		return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()) .build();
	}
	
}