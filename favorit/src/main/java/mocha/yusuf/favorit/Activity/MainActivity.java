package mocha.yusuf.favorit.Activity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import mocha.yusuf.favorit.Adapter.MovieAdapter;
import mocha.yusuf.favorit.Database.MovieContract;
import mocha.yusuf.favorit.Model.MovieModel;
import mocha.yusuf.favorit.R;

import static mocha.yusuf.favorit.Database.MovieContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final String DB_NAME = "favorit";
    private MovieAdapter adapter;
    private ArrayList<MovieModel> movie_models;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_LANG = "extra_lang";
    public static final String TAG = "Movie_TAG";
    public static String lang = "en";
    private RecyclerView recyclerView;
    private final int MOVIE_ID = 110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new MovieAdapter(this);
        recyclerView = findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportLoaderManager().initLoader(MOVIE_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        movie_models = new ArrayList<>();
        return new CursorLoader(this, CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        ArrayList<MovieModel> movie_model_temp = getItem(cursor);
        movie_models.clear();
        movie_models.addAll(movie_model_temp);
        adapter.setMovies(movie_model_temp);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        movie_models.clear();
        recyclerView.setAdapter(adapter);
        adapter.setMovies(movie_models);
        adapter.notifyDataSetChanged();

        getSupportLoaderManager().restartLoader(MOVIE_ID, null, this);
    }

    private ArrayList<MovieModel> getItem(Cursor cursor) {
        ArrayList<MovieModel> movieModels = new ArrayList<>();
        cursor.moveToFirst();
        MovieModel favorite;
        if (cursor.getCount() > 0) {
            do {
                favorite = new MovieModel(cursor);
                movieModels.add(favorite);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        return movieModels;
    }
}
