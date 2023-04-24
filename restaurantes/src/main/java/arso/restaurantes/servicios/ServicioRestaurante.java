package arso.restaurantes.servicios;

import java.util.LinkedList;
import java.util.List;

import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.FactoriaRepositorios;
import arso.repositorio.memoria.IRepositorioMemoria;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;

public class ServicioRestaurante implements IServicioRestaurante {

	private IRepositorioMemoria<Restaurante, String> repositorio = FactoriaRepositorios.getRepositorio(Restaurante.class);

	@Override
	public String create(Restaurante restaurente) throws RepositorioException {
		return repositorio.add(restaurente);
	}

	@Override
	public void update(Restaurante restaurante) throws RepositorioException, EntidadNoEncontrada {
		repositorio.update(restaurante);
	}

	@Override
	public void addPlato(String idRestaurante, Plato plato) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		restaurante.addPlato(plato);

		update(restaurante);
	}

	@Override
	public void removePlato(String idRestaurante, String plato) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		restaurante.removePlato(plato);

		update(restaurante);
	}

	@Override
	public void updatePlato(String idRestaurante, Plato plato) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		restaurante.updatePlato(plato);

		update(restaurante);

	}

	@Override
	public Restaurante getRestaurante(String idRestaurante) throws RepositorioException, EntidadNoEncontrada {
		return repositorio.getById(idRestaurante);
	}

	@Override
	public void removeRestaurante(String idRestaurante) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		repositorio.delete(restaurante);
	}

	@Override
	public List<SitioTuristico> getSitiosTuristidos(String idRestaurante)
			throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		return restaurante.getSitiosTuristicos();
	}

	@Override
	public void establecerSitiosTuristicos(String idRestaurante, List<SitioTuristico> sitiosTuristicos) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		restaurante.setSitiosTuristicos((LinkedList<SitioTuristico>) sitiosTuristicos);

		update(restaurante);

	}

	@Override
	public List<RestauranteResumen> getListadoRestaurantes() throws RepositorioException {
		
		LinkedList<RestauranteResumen> resultado = new LinkedList<>();
		
		for(String id : repositorio.getIds()) {
			try {
				Restaurante restaurante = getRestaurante(id);
				RestauranteResumen resumen = new RestauranteResumen();
				resumen.setId(restaurante.getId());
				resumen.setCoordenadas(restaurante.getCoordenadas());
				resumen.setNombre(restaurante.getNombre());
				resultado.add(resumen);
				
			} catch  (Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultado;
	}

}
