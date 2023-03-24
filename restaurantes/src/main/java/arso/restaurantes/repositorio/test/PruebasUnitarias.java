package arso.restaurantes.repositorio.test;

import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.FactoriaRepositorios;
import arso.repositorio.memoria.IRepositorioMemoria;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Restaurante;

public class PruebasUnitarias {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {
		
		IRepositorioMemoria<Restaurante, String> repositorio = FactoriaRepositorios.getRepositorio(Restaurante.class);
		
		//ServicioRestaurante servicio = new ServicioRestaurante();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Prueba");
		restaurante.setCoordenadas("Coordenadas de prueba");
		
		
		String id = repositorio.add(restaurante);
		
		Restaurante restaurante2 = repositorio.getById(id);
		System.out.println(restaurante2);
		

	}

}
