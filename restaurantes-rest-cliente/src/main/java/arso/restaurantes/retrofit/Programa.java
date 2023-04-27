package arso.restaurantes.retrofit;


import java.util.LinkedList;

import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.jaxb.JaxbConverterFactory;

public class Programa {
	
	public static void main(String[] args) throws Exception {
	
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JaxbConverterFactory.create()).build();
		
		RestauranteRestCliente service = retrofit.create(RestauranteRestCliente.class);
		
		// 1. Creación de un restaurante
		
		Plato plato1 = new Plato();
		plato1.setNombre("Pizza peperoni");
		plato1.setDescripcion("Pizza mediana con peperoni y queso");
		plato1.setPrecio(8.0);
		
		Plato plato2 = new Plato();
		plato2.setNombre("Pizza margarita");
		plato2.setDescripcion("Pizza mediana con tomate y queso");
		plato2.setPrecio(4.0);
		
		LinkedList<Plato> platos = new LinkedList<>();
		platos.add(plato1);
		platos.add(plato2);
		
		SitioTuristico sitio1 = new SitioTuristico();
		sitio1.setResumen("Catedral de Murcia");
		String cat1 = "categoria1";
		String cat2 = "categoria2";
		LinkedList<String> categorias = new LinkedList<>();
		categorias.add(cat1);
		categorias.add(cat2);
		sitio1.setCategorias(categorias);
		String im1 = "imagen1.png";
		String im2 = "imagen2.png";
		LinkedList<String> imagenes = new LinkedList<>();
		imagenes.add(im1);
		imagenes.add(im2);
		sitio1.setImagen(imagenes);
		String enl1 = "enlace1";
		String enl2 = "enlace2";
		LinkedList<String> enlaces = new LinkedList<>();
		enlaces.add(enl1);
		enlaces.add(enl2);
		sitio1.setEnlaces(enlaces);
		
		LinkedList<SitioTuristico> sitios = new LinkedList<>();
		sitios.add(sitio1);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Pieroti");
		restaurante.setCoordenadas("-5478141, 6548425");
		restaurante.setPlatos(platos);
		restaurante.setSitiosTuristicos(sitios);
		
		
		Response<Void> resultado = service.create(restaurante).execute();
		
		String url1 = resultado.headers().get("Location");
		
		//String id1 = url1.substring(url1.lastIndexOf("/") + 1);
		
		
		// Recuperación
		//Restaurante restaurante2 = service.getRestaurante(id1).execute().body();
		
		//System.out.println("Restaurante: " + restaurante2.getNombre() + " - " + restaurante2.getCoordenadas());
		
	}

}
