package arso.restaurantes.retrofit;


import java.util.LinkedList;

import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Programa {
	
	public static void main(String[] args) throws Exception {
	
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JacksonConverterFactory.create()).build();
		
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
		
		
		// (1) --> Creacion de un restaurante.
		
		Response<Void> resultado = service.create(restaurante).execute();
		
		String url1 = resultado.headers().get("Location");
		
		String id1 = url1.substring(url1.lastIndexOf("/") + 1);
		
		
		System.out.println("Restaurante creado: " + url1);
		System.out.println("Id: " + id1);
		
		Restaurante restaurante2 = service.getRestaurante(id1).execute().body();
		
		System.out.println("Restaurante: " + restaurante2.getNombre() + " - " + restaurante2.getCoordenadas() + "\n");
		
		
		
		// (2) --> Actualización del restaurante creado
		
		restaurante2.setNombre("Nueva Pieroti");
		
		Response<Void> respuesta = service.update(id1, restaurante2).execute();
		
		System.out.println("Restaurante actualizado");
		
		// Recuperamos el nuevo restaurante
		
		Restaurante restaurante3 = service.getRestaurante(id1).execute().body();

		System.out.println("Restaurante actualizado: " + restaurante3.getNombre() + " - " + restaurante3.getCoordenadas());
		
		System.out.println("Codigo de respuesta: " + respuesta.code() + "\n");
		
		
		// (2) --> Actualizar un restaurante con identificador erroneo
		
		restaurante3.setNombre("Burguer");
		
		Response<Void> respuesta2 = service.update("sdfsdf5sd4fs5d4f", restaurante3).execute();	
		
		System.out.println("Restaurante no actualizado por mal id");
		
		// Recuperamos el restaurante
		
		Restaurante restaurante4 = service.getRestaurante(id1).execute().body();
		
		System.out.println("Restaurante no actualizado: " + restaurante4.getNombre() + " - " + restaurante4.getCoordenadas());
		
		System.out.println("Codigo de respuesta: " + respuesta2.code() + "\n");
		
		
		
		// (3) --> Añadir un plato 
		
		// Creamos plato
		
		Plato newPlato = new Plato();
		newPlato.setNombre("Pizza carbonara");
		newPlato.setDescripcion("Pizza mediana con nata y bacon");
		newPlato.setPrecio(8.0);
		
		Response<Void> respuesta3 = service.addPlato(id1, newPlato).execute();
		
		System.out.println("Añadimos un tercer plato al restaurante");
		
		// Recuperamos los platos del restaurante
		
		Restaurante restaurante5 = service.getRestaurante(id1).execute().body();
		
		LinkedList<Plato> platos1 = restaurante5.getPlatos();
		
		for (Plato p : platos1) 
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());
		
		System.out.println("Codigo de respuesta: " + respuesta3.code() + "\n" );
		
		
		// (3) --> Añadir un plato con un nombre que ya existe
		
		Response<Void> respuesta4 = service.addPlato(id1, newPlato).execute();
		
		System.out.println("Añadimos plato cuyo nombre ya existe");
		
		// Recuperamos los platos del restaurante

		Restaurante restaurante6 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos2 = restaurante6.getPlatos();

		for (Plato p : platos2)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta4.code() + "\n");
		
		
		// (3) --> Añadir un plato cuyo id del restaurante no existe.
		
		Plato platoRest = new Plato();
		newPlato.setNombre("Pizza barbacoa");
		newPlato.setDescripcion("Pizza mediana con carne picada y queso");
		newPlato.setPrecio(8.0);
		
		Response<Void> respuesta5 = service.addPlato("sdbfsagfaf45", platoRest).execute();
		
		System.out.println("Añadimos plato cuyo id del restaurante no existe");
		
		// Recuperamos los platos del restaurante

		Restaurante restaurante7 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos3 = restaurante7.getPlatos();

		for (Plato p : platos3)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta5.code() + "\n");
		
		
		// (4) --> Eliminar un plato
		
		Response<Void> respuesta6 = service.removePlato(id1, newPlato.getNombre()).execute();
		
		System.out.println("Eliminamos un plato");
		
		// Recupetamos los platos del restaurante
		
		Restaurante restaurante8 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos4 = restaurante6.getPlatos();

		for (Plato p : platos4)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta6.code() + "\n");
		
	}

}
