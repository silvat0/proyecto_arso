package arso.restaurantes.retrofit;

import java.io.IOException;

import arso.opiniones.modelo.Opinion;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.servicios.ServicioRestaurante;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Interceptor.Chain;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Prueba {

	public static void main(String[] args) throws RepositorioException, IOException {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8090")
				.addConverterFactory(JacksonConverterFactory.create()).build();

		OpinionesRest opinionesRest = retrofit.create(OpinionesRest.class);

		ServicioRestaurante servicio = new ServicioRestaurante();

		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Emboka");
		restaurante.setCoordenadas("JUAN DE BORBON, 29, 30007 Murcia");

		String id = servicio.create(restaurante);

		Opinion opinion = new Opinion();

		// (1) --> Creacion de una opinion.

		opinion.setRecurso(restaurante.getNombre());

		Response<Void> resultado = opinionesRest.create(opinion).execute();

		String url1 = resultado.headers().get("Location");

		String id1 = url1.substring(url1.lastIndexOf("/") + 1);

		System.out.println("Opinion creada: " + url1);
		System.out.println("Id: " + id1);

		Opinion opinion2 = opinionesRest.getOpinion(id1).execute().body();
		
		System.out.println("Opinion: " + opinion2.getRecurso());

	}

}
