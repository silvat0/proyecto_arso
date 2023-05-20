package arso.opiniones.servicios;

import java.util.List;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.RepositorioException;

public interface IServicioOpiniones {

	// Metodo para crear una opinion
	String create(String recurso) throws RepositorioException;
	
	// Añadir una valoración sobre un recurso
	boolean addValoracion(String idOpinion, Valoracion valoracion) throws RepositorioException, EntidadNoEncontrada;
	
	// Recuperar la opinión de un recurso.
	Opinion getOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada;
	
	// Eliminar una opinión del servicio
	boolean removeOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada;

	// Dar un listado con un resumen de las opiniones
	List<OpinionResumen> getListadoOpiniones() throws RepositorioException;
}
