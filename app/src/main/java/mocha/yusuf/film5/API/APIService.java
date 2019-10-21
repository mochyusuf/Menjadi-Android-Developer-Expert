package mocha.yusuf.film5.API;

import mocha.yusuf.film5.Model.ListMovieModel;
import mocha.yusuf.film5.Model.ListTVModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("movie")
    Call<ListMovieModel> get_movies(
                @Query("api_key") String api_key,
                @Query("language") String language
        );

    @GET("tv")
    Call<ListTVModel> get_tvs(
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}
