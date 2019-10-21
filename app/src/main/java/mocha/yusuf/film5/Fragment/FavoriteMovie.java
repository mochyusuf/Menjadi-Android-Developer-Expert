package mocha.yusuf.film5.Fragment;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mocha.yusuf.film5.Adapter.MovieAdapter;
import mocha.yusuf.film5.Database.AppDatabase;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;

public class FavoriteMovie extends Fragment {

    public static final String DB_NAME = "favorit";
    private static AppDatabase db;
    private MovieAdapter adapter;
    private ArrayList<MovieModel> movie_models;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_LANG = "extra_lang";
    public static final String TAG = "Movie_TAG";
    public static String lang = "en";
    private RecyclerView recyclerView;

    public FavoriteMovie() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);

        db = Room.databaseBuilder(view.getContext(),
                AppDatabase.class, DB_NAME).build();

        adapter = new MovieAdapter(view.getContext());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movie_models = new ArrayList<>();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new getFavoriteMovie().execute();
    }

    class getFavoriteMovie extends AsyncTask<Void, Void, List<MovieModel>> {

        @Override
        protected List<MovieModel> doInBackground(Void... voids) {
            return db.movieDao().getMovies();
        }

        @Override
        protected void onPostExecute(List<MovieModel> movies) {
            movie_models.clear();
            movie_models.addAll(movies);
            recyclerView.setAdapter(adapter);
            adapter.setMovies(movie_models);
        }
    }

}