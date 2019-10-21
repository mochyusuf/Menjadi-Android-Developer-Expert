package mocha.yusuf.film5.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

import mocha.yusuf.film5.API.APIService;
import mocha.yusuf.film5.API.APIUrl;
import mocha.yusuf.film5.Adapter.MovieAdapter;
import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Model.ListMovieModel;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie extends Fragment {
    private MovieAdapter adapter;
    private ArrayList<MovieModel> movie_models;
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_LANG = "extra_lang";
    public static final String TAG = "Movie_TAG";
    public static String lang = "en";
    private RecyclerView recyclerView;
    public Movie() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);
        adapter = new MovieAdapter(getActivity());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState == null || !(Locale.getDefault().getLanguage().equals(lang))){
            lang = Locale.getDefault().getLanguage();
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.process));
            progressDialog.show();
            progressDialog.setCancelable(false);

            APIService apiService = APIUrl.getClient().create(APIService.class);

            Call<ListMovieModel> call = apiService.get_movies(BuildConfig.API_KEY,getString(R.string.lang));
            call.enqueue(new Callback<ListMovieModel>() {
                @Override
                public void onResponse(Call<ListMovieModel> call, Response<ListMovieModel> response) {
                    movie_models = response.body().getResults();
                    SetData();
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ListMovieModel>call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }else{
            movie_models = savedInstanceState.getParcelableArrayList(EXTRA_MOVIE);
            SetData();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(EXTRA_MOVIE, movie_models);
        outState.putString(EXTRA_LANG, lang);
    }

    public void SetData(){
        recyclerView.setAdapter(adapter);
        adapter.setMovies(movie_models);
    }
}
