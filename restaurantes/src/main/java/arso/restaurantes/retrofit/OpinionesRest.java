package arso.restaurantes.retrofit;

import arso.opiniones.modelo.Opinion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpinionesRest {

	@Headers({"Accept: application/json"})
	@POST("opiniones")
    Call<Void> create(@Body Opinion opinion);

    @GET("opiniones/{id}")
    Call<Opinion> getOpinion(@Path("id") String id);
}
