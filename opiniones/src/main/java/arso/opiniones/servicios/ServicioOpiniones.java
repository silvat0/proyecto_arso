package arso.opiniones.servicios;

import java.util.LinkedList;
import java.util.List;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.FactoriaRepositorios;
import arso.repositorio.memoria.IRepositorioMemoria;
import arso.repositorio.memoria.RepositorioException;

public class ServicioOpiniones implements IServicioOpiniones {

	private IRepositorioMemoria<Opinion, String> repositorio = FactoriaRepositorios.getRepositorio(Opinion.class);
	
	@Override
	public String create(Opinion opinion) throws RepositorioException {
		return repositorio.add(opinion);
	}
	
	@Override
	public void addValoracion(String idOpinion, Valoracion valoracion) throws RepositorioException, EntidadNoEncontrada {
		Opinion opinion = repositorio.getById(idOpinion);
		opinion.addValoracion(valoracion);
	}
		
	@Override	
	public Opinion getOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada{
		return repositorio.getById(idOpinion);
	}
	
	@Override
	public void removeOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada{
		Opinion opinion = repositorio.getById(idOpinion);
		
		repositorio.delete(opinion);
	}
	
	@Override
	public List<OpinionResumen> getListadoOpiniones() throws RepositorioException {
		
		LinkedList<OpinionResumen> resultado = new LinkedList<>();
		
		for(String id : repositorio.getIds()) {
			try {
				Opinion opinion = getOpinion(id);
				OpinionResumen resumen = new OpinionResumen();
				resumen.setId(opinion.getId());
				resumen.setRecurso(opinion.getRecurso());
				resultado.add(resumen);
				
			} catch  (Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	
}
