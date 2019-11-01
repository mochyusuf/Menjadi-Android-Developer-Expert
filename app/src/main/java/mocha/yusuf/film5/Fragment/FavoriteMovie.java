package mocha.yusuf.film5.Fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mocha.yusuf.film5.Adapter.MovieAdapter;
import mocha.yusuf.film5.Database.MovieContract;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;

public class FavoriteMovie extends Fragment {

    public static final String DB_NAME = "favorit";
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

        ArrayList<MovieModel> movieModels = new ArrayList<>();
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(MovieContract.CONTENT_URI, null,
                null, null, null, null);
        Objects.requireNonNull(cursor).moveToFirst();
        if (Objects.requireNonNull(cursor).getCount() > 0) {
            do {
                MovieModel movieModel = new MovieModel(cursor);
                movieModels.add(movieModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        movie_models.clear();
        movie_models.addAll(movieModels);
        recyclerView.setAdapter(adapter);
        adapter.setMovies(movie_models);
    }
}