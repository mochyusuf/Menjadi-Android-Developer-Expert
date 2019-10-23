package mocha.yusuf.film5.API;

import mocha.yusuf.film5.Model.ListMovieModel;
import mocha.yusuf.film5.Model.ListTVModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("discover/movie")
    Call<ListMovieModel> get_movies(
                @Query("api_key") String api_key,
                @Query("language") String language
        );

    @GET("discover/tv")
    Call<ListTVModel> get_tvs(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("search/movie")
    Call<ListMovieModel> search_movies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("search/tv")
    Call<ListTVModel> search_tvs(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );
}
