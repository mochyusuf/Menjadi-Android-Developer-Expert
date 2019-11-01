package mocha.yusuf.film5.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Database.MovieContract;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;

public class DetailMovie extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String TAG = "DetailMovie_TAG";
    public static final String DB_NAME = "favorit";
    private static MovieModel movie;
    private boolean isfavorite;
    private String id_movie;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TextView movie_title = findViewById(R.id.movie_title);
        ImageView movie_image = findViewById(R.id.movie_image);
        TextView movie_release_date = findViewById(R.id.movie_release_date);
        TextView movie_score = findViewById(R.id.movie_score);
        TextView movie_overview = findViewById(R.id.movie_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.id_movie = String.valueOf(movie.getId());

        movie_title.setText(movie.getTitle());
        Glide.with(this)
                .load(BuildConfig.API_IMAGE +movie.getPoster_path())
                .into(movie_image);
        movie_release_date.setText(movie.getRelease_date());
        movie_score.setText(String.valueOf((int)movie.getVote_average()*10));
        movie_overview.setText(movie.getOverview());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorite, menu);
        this.menu = menu;
        if (!isFavorite(this.id_movie)){
            menu.findItem(R.id.action_un_favorite).setVisible(false);
        }else{
            menu.findItem(R.id.action_favorite).setVisible(false);
        }
        return true;
    }

    private boolean isFavorite(String id) {
        Uri uri = MovieContract.CONTENT_URI.buildUpon().appendPath(id).build();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        return cursor.moveToFirst();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MovieContract.MovieColumns.MOVIE_id, id);
            contentValues.put(MovieContract.MovieColumns.MOVIE_title, movie.getTitle());
            contentValues.put(MovieContract.MovieColumns.MOVIE_popularity, movie.getPopularity());
            contentValues.put(MovieContract.MovieColumns.MOVIE_vote_count, movie.getVote_count());
            contentValues.put(MovieContract.MovieColumns.MOVIE_poster_path, movie.getPoster_path());
            contentValues.put(MovieContract.MovieColumns.MOVIE_backdrop_path, movie.getBackdrop_path());
            contentValues.put(MovieContract.MovieColumns.MOVIE_original_language, movie.getOriginal_language());
            contentValues.put(MovieContract.MovieColumns.MOVIE_original_title, movie.getOriginal_language());
            contentValues.put(MovieContract.MovieColumns.MOVIE_vote_average, movie.getVote_average());
            contentValues.put(MovieContract.MovieColumns.MOVIE_overview, movie.getOverview());
            contentValues.put(MovieContract.MovieColumns.MOVIE_release_date, movie.getRelease_date());
            Uri uri = getContentResolver().insert(MovieContract.CONTENT_URI, contentValues);
            if (uri != null) {
                Log.i(TAG, "Uri " + uri);
            }
            menu.findItem(R.id.action_favorite).setVisible(false);
            menu.findItem(R.id.action_un_favorite).setVisible(true);
            return true;
        }else if(id == R.id.action_un_favorite){
            Uri uri = MovieContract.CONTENT_URI.buildUpon().appendPath(this.id_movie).build();
            getContentResolver().delete(uri, null, null);
            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_un_favorite).setVisible(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
