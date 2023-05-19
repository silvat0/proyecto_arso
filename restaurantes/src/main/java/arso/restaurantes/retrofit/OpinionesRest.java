package arso.restaurantes.retrofit;

import arso.eventos.Opinion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpinionesRest {


	@POST("opiniones")
    Call<Void> create(@Body Opinion opinion);

    @GET("opiniones/{id}")
    Call<Opinion> getOpinion(@Path("id") String id);
}
