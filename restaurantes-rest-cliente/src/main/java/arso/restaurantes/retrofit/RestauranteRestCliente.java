package arso.restaurantes.retrofit;

import java.util.List;

import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestauranteRestCliente {

	
	// (1)
	
	@POST("restaurantes")
	Call<Void> create(@Body Restaurante restaurante);
	
	// (2)
	
	@PUT("restaurantes/{id}")
	Call<Void> update(@Path("id") String id, @Body Restaurante restaurante);
	
	// (3)
	
	@POST("restaurantes/{id}/platos")
	Call<Void> addPlato(@Path("id") String id, @Body Plato plato);
	
	// (4)
	
	@DELETE("restaurantes/{id}/platos/{nombre}")
	Call<Void> removePlato(@Path("id") String id, @Path("nombre") String nombrePlato);
	
	// (5)
	
	@PUT("restaurantes/{id}/platos/{nombre}")
	Call<Void> updatePlato(@Path("id") String id, @Path("nombre") String nombrePlato, @Body Plato plato);
	
	// (6)
	
	@GET("restaurantes/{id}")
	Call<Restaurante> getRestaurante(@Path("id") String id);
	
	// (7)
	
	@DELETE("restaurantes/{id}")
	Call<Void> removeRestaurante(@Path("id") String id);
	
	// (8)
	
	@GET("restaurantes/{id}/sitiosTuristicos")
	Call<List<SitioTuristico>> getSitiosTuristicos(@Path("id") String id);
	
	// (9)
	
	@PUT("restaurantes/{id}/sitiosTuristicos")
	Call<Void> establecerSitiosTuristicos(@Path("id") String id, @Body List<SitioTuristico> sitiosTuristicos);
	
	// (10)
	
	@GET("restaurantes")
	Call<List<ResumenExtendido>> getListadoRestaurantes();
	
}
