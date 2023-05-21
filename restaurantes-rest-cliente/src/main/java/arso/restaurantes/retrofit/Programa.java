package arso.restaurantes.retrofit;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Programa {
	
	public static void main(String[] args) throws Exception {

		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
		httpClientBuilder.addInterceptor(new Interceptor() {
		    @Override
		    public okhttp3.Response intercept(Chain chain) throws IOException {
		        Request.Builder requestBuilder = chain.request().newBuilder();
		        requestBuilder.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzYjY1NGUzNC1iZjhlLTRiYTYtODg3ZC02NDg4YTczOThlMGIiLCJpc3MiOiJQYXNhcmVsYSBadXVsIiwiZXhwIjoxNjg0NzY2MzI4LCJzdWIiOiJzaWx2YXQwIiwidXN1YXJpbyI6InNpbHZpYS5wZXJlenJAdW0uZXMiLCJyb2wiOiJHRVNUT1IifQ._Y7Alt_t2CyneWR8GXbhd7X7pNx61akbbN9G_jQYBGs");
		        return chain.proceed(requestBuilder.build());
		    }
		});
		OkHttpClient httpClient = httpClientBuilder.build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://restaurante:8090")
				.client(httpClient)
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
		
		SitioTuristico sitio2 = new SitioTuristico();
		sitio1.setResumen("Catedral de Albacete");
		String cat3 = "categoria1";
		String cat4 = "categoria2";
		LinkedList<String> categorias1 = new LinkedList<>();
		categorias.add(cat3);
		categorias.add(cat4);
		sitio1.setCategorias(categorias1);
		String im3 = "imagen1.png";
		String im4 = "imagen2.png";
		LinkedList<String> imagenes1 = new LinkedList<>();
		imagenes.add(im3);
		imagenes.add(im4);
		sitio1.setImagen(imagenes1);
		String enl3 = "enlace1";
		String enl4 = "enlace2";
		LinkedList<String> enlaces1 = new LinkedList<>();
		enlaces.add(enl3);
		enlaces.add(enl4);
		sitio1.setEnlaces(enlaces1);
		
		LinkedList<SitioTuristico> sitios = new LinkedList<>();
		sitios.add(sitio1);
		
		LinkedList<SitioTuristico> sitios5 = new LinkedList<>();
		sitios5.add(sitio2);
		
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
		platoRest.setNombre("Pizza barbacoa");
		platoRest.setDescripcion("Pizza mediana con carne picada y queso");
		platoRest.setPrecio(8.0);
		
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

		LinkedList<Plato> platos4 = restaurante8.getPlatos();

		for (Plato p : platos4)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta6.code() + "\n");
		
		
		// (4) --> Eliminar un plato cuyo id del restaurante no existe
		
		Response<Void> respuesta7 = service.removePlato("sdlgsdfas4fads", plato1.getNombre()).execute();
		
		System.out.println("Eliminamos un plato cuyo id de resturante no existe");
		
		// Recuperamos los platos
		
		Restaurante restaurante9 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos5 = restaurante9.getPlatos();

		for (Plato p : platos5)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta7.code() + "\n");
		
		
		// (4) --> Eliminamos un plato el cual no existe
		
		Response<Void> respuesta8 = service.removePlato(id1, "pizza nueva").execute();
		
		System.out.println("Eliminamos un plato cuyo id de plato no existe");
		
		// Recuperamos los platos
		
		Restaurante restaurante10 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos6 = restaurante10.getPlatos();

		for (Plato p : platos6)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta8.code() + "\n");
		
		
		// (5) --> Actualizar un plato
		
		plato1.setPrecio(12);
		plato1.setDescripcion("Pizza mediana con peperoni y queso, ahora con mas peperoni");
		
		Response<Void> respuesta9 = service.updatePlato(id1, plato1.getNombre(), plato1).execute();
		
		System.out.println("Actualiza un plato");
		
		// Recuperamos los platos
		
		Restaurante restaurante11 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos7 = restaurante11.getPlatos();

		for (Plato p : platos7)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta9.code() + "\n");
		
		
		// (5) --> Actualizar un plato con id de restaurante no existente
		
		plato2.setDescripcion("Pizza mediana con tomate y queso, ahora con doble de queso");
		
		Response<Void> respuesta10 = service.updatePlato("sadjfbafdff5f", plato2.getNombre(), plato2).execute();
		
		System.out.println("Actualiza un plato con id de restaurante no existente");
		
		// Recuperamos los platos
		
		Restaurante restaurante12 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos8 = restaurante12.getPlatos();

		for (Plato p : platos8)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta10.code() + "\n");
		
		
		// (5) Actualizar plato con nombre diferente
		
		Response<Void> respuesta11 = service.updatePlato(id1, "Nueva Pizza", plato2).execute();
		
		System.out.println("Actualiza un plato con nombre distinto");
		
		// Recuperamos los platos

		Restaurante restaurante13 = service.getRestaurante(id1).execute().body();

		LinkedList<Plato> platos9 = restaurante13.getPlatos();

		for (Plato p : platos9)
			System.out.println("Plato: " + p.getNombre() + " - " + p.getDescripcion());

		System.out.println("Codigo de respuesta: " + respuesta11.code() + "\n");
		
		
		// (6) --> Obtener un restaurante
		
		Response<Restaurante> respuesta12 = service.getRestaurante(id1).execute();
		
		System.out.println("Obtener un restaurante por su id");
		
		// Recuperamos el restaurante y la respuesta
		
		String contentType = respuesta12.headers().get("Content-Type");
		
		System.out.println("Formato: " + contentType);
		
		Restaurante restaurante14 = service.getRestaurante(id1).execute().body();
		
		System.out.println("Restaurante: " + restaurante14.getNombre() + " - " + restaurante14.getCoordenadas());

		System.out.println("Codigo de respuesta: " + respuesta12.code() + "\n");
		
		
		// (6) --> Obtener un restaurante con id no existente
		
		Response<Restaurante> respuesta13 = service.getRestaurante("sdfafafsd445").execute();
		
		System.out.println("Obtener un restaurante con id no existente");
		
		// Recuperamos el restaurante y la respuesta
		
		Restaurante restaurante15 = service.getRestaurante(id1).execute().body();

		System.out.println("Restaurante: " + restaurante15.getNombre() + " - " + restaurante15.getCoordenadas());

		System.out.println("Codigo de respuesta: " + respuesta13.code() + "\n");
		
		
		// (7) --> Eliminar un restaurante
		
		Response<Void> respuesta14 = service.removeRestaurante(id1).execute();
		
		System.out.println("Eliminamos un restaurante por su id");
		
		// Recuperamos 
		
		Restaurante restaurante16 = service.getRestaurante(id1).execute().body();

		if (restaurante16 == null) {
			System.out.println("El restaurante se ha eliminado");
		}

		System.out.println("Codigo de respuesta: " + respuesta14.code() + "\n");
		
		
		// -------------- Creamos otro restaurante para seguir con los ejemplos que nos quedan --------------------
		
		Restaurante rest= new Restaurante();
		restaurante.setNombre("Burguer");
		restaurante.setCoordenadas("-5478141, 6548425");
		restaurante.setPlatos(platos);
		restaurante.setSitiosTuristicos(sitios);
		
		Response<Void> resul = service.create(rest).execute();

		String url2 = resul.headers().get("Location");

		String id2 = url2.substring(url2.lastIndexOf("/") + 1);
		
		
		
		// (7) --> Eliminar un restaurante con id no existente
		
		Response<Void> respuesta15 = service.removeRestaurante("sdffsdfsdf544sd").execute();

		System.out.println("Eliminamos un restaurante con un id no existente");

		// Recuperamos

		System.out.println("Codigo de respuesta: " + respuesta15.code() + "\n");
		
		
		// (8) --> Obtener los sitios turisticos de un restaurante.
		
		Response<LinkedList<SitioTuristico>> respuesta16 = service.getSitiosTuristicos(id2).execute();
		
		System.out.println("Obtener los sitios");
		
		// Recuperamos la respuesta.
		
		
		List<SitioTuristico> sitios1 = service.getSitiosTuristicos(id2).execute().body();
		
		for(SitioTuristico st : sitios1) 
			System.out.println("Sitio: " + st.getResumen());
	
		System.out.println("Codigo de respuesta: " + respuesta16.code() + "\n");
		
		
		// (8) --> Obtener los sitios de un restaurante con id mal
		
		Response<LinkedList<SitioTuristico>> respuesta17 = service.getSitiosTuristicos("sdffsfdfsd556").execute();
		
		System.out.println("Obtener los sitios con id de restaurante no existente");
		
		// Recuperas los sitios y ver que no salgan
		
		List<SitioTuristico> sitios2 = service.getSitiosTuristicos("sdffsfdfsd556").execute().body();
		
		if (sitios2 == null) 
			System.out.println("No hay sitios porque no existe el resturante");
		
		System.out.println("Codigo de respuesta: " + respuesta17.code() + "\n");
		
		// (9) --> Establecer sitios turisticos
		
		Response<Void> respuesta18 = service.establecerSitiosTuristicos(id2, sitios).execute();
		
		System.out.println("Establecer los sitios turisticos de un restaurante");
		
		// Recuperar los sitios turisticos
		
		List<SitioTuristico> sitios3 = service.getSitiosTuristicos(id2).execute().body();
		

		for(SitioTuristico st : sitios3) 
			System.out.println("Sitio: " + st.getResumen());
	
		System.out.println("Codigo de respuesta: " + respuesta18.code() + "\n");
		
		
		// (9) --> Establecer sitio turistico con id del restaurante mal
		
		Response<Void> respuesta19 = service.establecerSitiosTuristicos("sdhfus", sitios5).execute();
		
		System.out.println("Establecer los sitios turisticos de un restaurante con id mal");
		
		// Recuperar los sitios turisticos
	
		System.out.println("Codigo de respuesta: " + respuesta19.code() + "\n");
		
		// (10) --> Recuperar todos los restaurantes
		
		// Creamos otro restaurante
		
		service.create(restaurante).execute();
		
		Response<List<ResumenExtendido>> respuesta21 = service.getListadoRestaurantes().execute();
		
		System.out.println("Obtener todos los restaurantes");
		
		// Recuperamos.
		
		String contentType1 = respuesta21.headers().get("Content-Type");
		
		System.out.println("Formato: " + contentType1);
		
		List<ResumenExtendido> resta = service.getListadoRestaurantes().execute().body();
		
		for(ResumenExtendido r : resta)
			System.out.println("Restaurante: " + r.getResumen().getNombre());
		
		System.out.println("Codigo de respuesta: " + respuesta21.code() + "\n");
		
	}
	

}
