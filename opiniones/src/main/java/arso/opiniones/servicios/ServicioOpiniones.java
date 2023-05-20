 	package arso.opiniones.servicios;

import java.util.LinkedList;
import java.util.List;

import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.FactoriaRepositorios;
import arso.repositorio.memoria.IRepositorio;
import arso.repositorio.memoria.RepositorioException;

public class ServicioOpiniones implements IServicioOpiniones {

	private IRepositorio<Opinion, String> repositorio = FactoriaRepositorios.getRepositorio(Opinion.class);
	
	@Override
	public String create(String recurso) throws RepositorioException {
		Opinion opinion = new Opinion();
		opinion.setRecurso(recurso);
		return repositorio.add(opinion);
	}
	
	@Override
	public boolean addValoracion(String idOpinion, Valoracion valoracion) throws RepositorioException, EntidadNoEncontrada {
		Opinion opinion = repositorio.getById(idOpinion);
		boolean resul = opinion.addValoracion(valoracion);
		repositorio.update(opinion);
		
		return resul;
			
	}
		
	@Override	
	public Opinion getOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada{
		return repositorio.getById(idOpinion);
	}
	
	@Override
	public boolean removeOpinion(String idOpinion) throws RepositorioException, EntidadNoEncontrada{
		Opinion opinion = repositorio.getById(idOpinion);
		
		boolean oDelete =  repositorio.delete(opinion);
		
		repositorio.update(opinion);
		
		if (oDelete)
			return true;
		return false;
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
