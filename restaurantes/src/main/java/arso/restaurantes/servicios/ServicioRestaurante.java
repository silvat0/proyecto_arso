package arso.restaurantes.servicios;

import java.util.List;

import arso.repositorio.EntidadNoEncontrada;
import arso.repositorio.FactoriaRepositorios;
import arso.repositorio.Repositorio;
import arso.repositorio.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import bookle.modelo.Actividad;

public class ServicioRestaurante implements IServicioRestaurante {

	private Repositorio<Restaurante, String> repositorio = FactoriaRepositorios.getRepositorio(Restaurante.class);

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
	public void establecerSitiosTuristicos(String idRestaurante, List<SitioTuristico> sitiosTuristicos) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RestauranteResumen> getListadoRestaurantes() {
		// TODO Auto-generated method stub
		return null;
	}

}
