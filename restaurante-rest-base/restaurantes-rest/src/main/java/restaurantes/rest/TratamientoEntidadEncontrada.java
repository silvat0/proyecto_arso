package restaurantes.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import arso.repositorio.EntidadEncontrada;



@Provider
public class TratamientoEntidadEncontrada implements ExceptionMapper<EntidadEncontrada> {

	@Override
	public Response toResponse(EntidadEncontrada exception) {
		
		return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()) .build();
	}
	
}
