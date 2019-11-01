package mocha.yusuf.favorit.API;

import mocha.yusuf.favorit.Model.ListMovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("discover/movie")
    Call<ListMovieModel> get_movies(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("search/movie")
    Call<ListMovieModel> search_movies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("discover/movie")
    Call<ListMovieModel> upcoming_movie(
            @Query("api_key") String api_key,
            @Query("primary_release_date.gte") String primary_release_date_gte,
            @Query("primary_release_date.lte") String primary_release_date_lte
    );
}
