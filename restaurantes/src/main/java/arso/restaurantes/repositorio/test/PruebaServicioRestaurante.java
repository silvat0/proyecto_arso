package arso.restaurantes.repositorio.test;

import arso.repositorio.EntidadEncontrada;
import arso.repositorio.EntidadNoEncontrada;
import arso.repositorio.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.servicios.ServicioRestaurante;

public class PruebaServicioRestaurante {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada, EntidadEncontrada {
		
		ServicioRestaurante servicio = new ServicioRestaurante();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Prueba");
		restaurante.setCoordenadas("Coordenadas de prueba");
		
		Plato plato = new Plato();
		plato.setNombre("prueba");
		plato.setDescripcion("plato de prueba");
		plato.setPrecio(20.0);
		
		Plato plato2 = new Plato();
		plato2.setNombre("prueba2");
		plato2.setDescripcion("plato2 de prueba");
		plato2.setPrecio(10.0);
		
		
		String id = servicio.create(restaurante);
		
		servicio.addPlato(id, plato);
		
		Restaurante restaurante2 = servicio.getRestaurante(id);
		System.out.println(restaurante2);
		
		servicio.addPlato(id, plato);
		
		Restaurante restaurante3 = servicio.getRestaurante(id);
		System.out.println(restaurante3);
		
		servicio.addPlato(id, plato2);
		
		Restaurante restaurante4 = servicio.getRestaurante(id);
		System.out.println(restaurante4);
		
		servicio.removePlato(id, "prueba2");
		
		Restaurante restaurante5 = servicio.getRestaurante(id);
		System.out.println(restaurante5);
		

	}

}
