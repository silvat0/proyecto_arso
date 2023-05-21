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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.HttpHeaders;

import arso.repositorio.EntidadEncontrada;
import arso.repositorio.EntidadNoEncontrada;
import arso.repositorio.GestorNoAutorizado;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import arso.restaurantes.servicios.FactoriaServicios;
import arso.restaurantes.servicios.IServicioRestaurante;
import arso.restaurantes.servicios.RestauranteResumen;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import restaurantes.rest.seguridad.AvailableRoles;
import restaurantes.rest.seguridad.Secured;

@Api
@Path("restaurantes")
public class RestauranteControladorRest {

	private IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);

	@Context
	private SecurityContext securityContext;

	@Context
	private UriInfo uriInfo;

	// (1) **

	// curl -i -X POST -H "Content-type: application/json" --data
	// "{\"nombre\":\"pizza\", \"coordenadas\":\"-5541784, 54484544\"}"
	// http://localhost:8080/api/restaurantes/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured(AvailableRoles.GESTOR)
	@ApiOperation(value = "Crea un restaurante", notes = "Retorna el codigo 201 indicando que ha creado el recurso")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "") })
	public Response create(@ApiParam(value = "Restaurante a crear", required = true) Restaurante restaurante,
			@Context HttpHeaders headers) throws Exception {

		// Obtener todas las cabeceras en la petición

		MultivaluedMap<String, String> allHeaders = headers.getRequestHeaders();

		allHeaders.get("X-Forwarded-Port");

		restaurante.setIdGestor(this.securityContext.getUserPrincipal().getName());

		String id = servicio.create(restaurante);

		String url = allHeaders.get("X-Forwarded-Proto").get(0) + "://" + allHeaders.get("X-Forwarded-Host").get(0)
				+ allHeaders.get("x-forwarded-prefix").get(0) + "/" + id;

		// System.out.println(url);

		URI nuevaURL = new URI(url);

		// URI nuevaURL =
		// uriInfo.getAbsolutePathBuilder().host(host).port(port).path(id).build();

		return Response.created(nuevaURL).build();
	}

	// (2) **

	// curl -i -X PUT -H "Content-type: application/json" --data
	// "{\"id\":\"643ac9d3ed48b8c9aa007a7d\",\"nombre\":\"Burguer\",\"coordenadas\":\"-5541784,
	// 54484544\"}" http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d

	@PUT
	@Path("/{id}")
	@Secured(AvailableRoles.GESTOR)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Actualiza un restaurante", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Id no encontrado") })
	public Response update(@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id1,
			@ApiParam(value = "Restaurante a actualizar", required = true) Restaurante restaurante) throws Exception {

		if (!id1.equals(restaurante.getId()))
			throw new IllegalArgumentException("El identificador no coincide: " + id1);

		if (!servicio.getRestaurante(id1).getIdGestor().equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado:" + this.securityContext.getUserPrincipal().getName());

		servicio.update(restaurante.getId(), restaurante.getNombre(), restaurante.getCoordenadas());

		this.securityContext.getUserPrincipal().getName();

		return Response.status(Response.Status.NO_CONTENT).build();

	}

	// (3) **

	// curl -i -X POST -H "Content-type: application/json" --data
	// "{\"nombre\":\"roll\",\"descripcion\":\"shushi variado\",\"precio\": 14}"
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d/platos

	@POST
	@Path("/{id}/platos")
	@Secured(AvailableRoles.GESTOR)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Añade un plato", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "El plato ya existe") })

	public Response addPlato(
			@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String idRestaurante,
			@ApiParam(value = "Plato a añadir", required = true) Plato plato) throws Exception {

		if (!servicio.getRestaurante(idRestaurante).getIdGestor()
				.equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado: " + this.securityContext.getUserPrincipal().getName());

		boolean existe = servicio.addPlato(idRestaurante, plato);

		if (!existe)
			throw new EntidadEncontrada("El plato con nombre " + plato.getNombre() + "ya existe");

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// (4) **

	// curl -i -X DELETE
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d/platos/ensalada

	@DELETE
	@Path("/{id}/platos/{nombre}")
	@Secured(AvailableRoles.GESTOR)
	@ApiOperation(value = "Elimina un plato", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "idRestaurante no encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Plato no encontrado") })
	public Response removePlato(
			@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String idRestaurante,
			@ApiParam(value = "nombre del plato", required = true) @PathParam("nombre") String nombrePlato)
			throws Exception {

		if (!servicio.getRestaurante(idRestaurante).getIdGestor()
				.equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado:" + this.securityContext.getUserPrincipal().getName());

		boolean existe = servicio.removePlato(idRestaurante, nombrePlato);

		if (!existe)
			throw new EntidadNoEncontrada("El plato con nombre " + nombrePlato + "no existe");

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// (5) **

	// curl -i -X PUT -H "Content-type: application/json" --data
	// "{\"nombre\":\"Burguer\",\"descripcion\":\"Burguer con patatas\",\"precio\":
	// 14}"
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d/platos/ensalada

	@PUT
	@Path("/{id}/platos/{nombre}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Secured(AvailableRoles.GESTOR)
	@ApiOperation(value = "Actualiza un plato", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "idRestaurante no encontrado"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Nombre del plato no coincide") })
	public Response updatePlato(
			@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String idRestaurante,
			@ApiParam(value = "nombre del plato", required = true) @PathParam("nombre") String nombrePlato,
			@ApiParam(value = "Plato a actualizar", required = true) Plato plato) throws Exception {

		if (!servicio.getRestaurante(idRestaurante).getIdGestor()
				.equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado:" + this.securityContext.getUserPrincipal().getName());

		if (!nombrePlato.equals(plato.getNombre()))
			throw new IllegalArgumentException("El nombre del plato no coincide: " + nombrePlato);

		servicio.updatePlato(idRestaurante, plato);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// (6) **

	// curl -H "Accept: application/json"
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Obtener un restaurante", notes = "Retorna el resturante por el id dado", response = Restaurante.class)
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado") })
	public Response getRestaurante(@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id)
			throws Exception {

		return Response.status(Response.Status.OK).entity(servicio.getRestaurante(id)).build();
	}

	// (7) **

	// curl -i -X DELETE
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d

	@DELETE
	@Path("/{id}")
	@Secured(AvailableRoles.GESTOR)
	@ApiOperation(value = "Eliminar un restaurante", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado") })
	public Response removeRestaurante(
			@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id) throws Exception {

		if (!servicio.getRestaurante(id).getIdGestor().equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado:" + this.securityContext.getUserPrincipal().getName());

		servicio.removeRestaurante(id);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// (8) **

	// curl -H "Accept: application/json"
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d/sitiosTuristicos

	@GET
	@Path("/{id}/sitiosTuristicos")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Obtener los sitios turisticos de un restaurante", notes = "Retorna la lista de sitios turisticos", response = SitioTuristico.class)
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado") })
	public Response getSitiosTuristicos(
			@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id) throws Exception {

		List<SitioTuristico> sitios = servicio.getSitiosTuristicos(id);
		return Response.status(Response.Status.OK).entity(sitios).build();
	}

	// (9)

	// curl -i -X PUT -H "Content-type: application/json" --data "[{"resumen": "La
	// catedral de Murcia","categorias": ["Categoria1","Categoria2"],"enlaces":
	// ["http;//hfhwvhwjvwv"],"imagen": ["imagen1.png"]}]"
	// http://localhost:8080/api/restaurantes/643ac9d3ed48b8c9aa007a7d/sitiosTuristicos

	@PUT
	@Path("/{id}/sitiosTuristicos")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Secured(AvailableRoles.GESTOR)
	@ApiOperation(value = "Establecer sitios turisticos", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado") })
	public Response establecerSitiosTuristicos(
			@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id,
			@ApiParam(value = "Lista de sitios turisticos", required = true) LinkedList<SitioTuristico> sitiosTuristicos)
			throws Exception {

		if (!servicio.getRestaurante(id).getIdGestor().equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado:" + this.securityContext.getUserPrincipal().getName());

		servicio.establecerSitiosTuristicos(id, sitiosTuristicos);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// (10) **

	// curl -H "Accept: application/json" http://localhost:8080/api/restaurantes

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Obtener los restaurantes", notes = "Retorna la lista de todos los restaurantes", response = Restaurante.class)
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "") })
	public Response getListadoRestaurantes(@Context HttpHeaders headers) throws Exception {

		MultivaluedMap<String, String> allHeaders = headers.getRequestHeaders();

		// System.out.println(this.securityContext.getUserPrincipal().getName());

		List<RestauranteResumen> resultado = servicio.getListadoRestaurantes();

		List<ResumenExtendido> extendido = new LinkedList<ResumenExtendido>();

		for (RestauranteResumen restauranteResumen : resultado) {

			ResumenExtendido resumenExtendido = new ResumenExtendido();

			resumenExtendido.setResumen(restauranteResumen);

			String id = restauranteResumen.getId();
			// URI nuevaURL = uriInfo.getAbsolutePathBuilder().path(id).build();

			String url = allHeaders.get("X-Forwarded-Proto").get(0) + "://" + allHeaders.get("X-Forwarded-Host").get(0)
					+ allHeaders.get("x-forwarded-prefix").get(0) + "/" + id;

			URI nuevaURL = new URI(url);

			// System.out.println("NuevaURL : " + nuevaURL.toString());

			resumenExtendido.setUrl(nuevaURL.toString());

			extendido.add(resumenExtendido);

		}

		return Response.ok(extendido).build();

	}

	@POST
	@Path("/{id}/opinion")
	@Secured(AvailableRoles.GESTOR)
	@ApiOperation(value = "Crea una opinion", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado") })
	public Response crearOpinion(@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id)
			throws Exception {
		
		if (!servicio.getRestaurante(id).getIdGestor().equals(this.securityContext.getUserPrincipal().getName()))
			throw new GestorNoAutorizado(
					"No eres un gestor autoizado:" + this.securityContext.getUserPrincipal().getName());

		servicio.crearOpinion(id);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@GET
	@Path("/{id}/opinion")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Obtener valoraciones del restaurante", notes = "Retorna el codigo 204 si todo ha ido bien")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = ""),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Restaurante no encontrado") })
	public Response getValoraciones(@ApiParam(value = "id del restaurante", required = true) @PathParam("id") String id)
			throws Exception {

		return Response.ok(servicio.getValoraciones(id)).build();
	}

}
