package arso.restaurantes.repositorio.test;

import arso.repositorio.EntidadNoEncontrada;
import arso.repositorio.FactoriaRepositorios;
import arso.repositorio.Repositorio;
import arso.repositorio.RepositorioException;
import arso.restaurantes.modelo.Restaurante;

public class PruebasUnitarias {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {
		
		Repositorio<Restaurante, String> repositorio = FactoriaRepositorios.getRepositorio(Restaurante.class);
		
		//ServicioRestaurante servicio = new ServicioRestaurante();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Prueba");
		restaurante.setCoordenadas("Coordenadas de prueba");
		
		
		String id = repositorio.add(restaurante);
		
		Restaurante restaurante2 = repositorio.getById(id);
		System.out.println(restaurante2);
		

	}

}
