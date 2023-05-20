package arso.restaurantes.retrofit;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import arso.eventos.modelo.Opinion;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.servicios.ServicioRestaurante;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import arso.eventos.modelo.Valoracion;

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
		
		//LinkedList<Valoracion> valoraciones = new LinkedList<>();
		
		
//		Opinion opinion = new Opinion();
//		opinion.setRecurso("HOLA");
//		
//		
//		opinion.setValoraciones(valoraciones);
		

		// (1) --> Creacion de una opinion.

		//opinion.setRecurso(restaurante.getNombre());
		String recurso = "Restaurante Emboka";
		
		Response<Void> resultado = opinionesRest.create(recurso).execute();
		
		System.out.println(resultado.code());
		System.out.println(resultado.message());

		String url1 = resultado.headers().get("Location");

		String id1 = url1.substring(url1.lastIndexOf("/") + 1);

		System.out.println("Opinion creada: " + url1);
		System.out.println("Id: " + id1);

		Opinion opinion2 = opinionesRest.getOpinion(id1).execute().body();
		
		System.out.println("Opinion: " + opinion2.getRecurso());

	}

}
