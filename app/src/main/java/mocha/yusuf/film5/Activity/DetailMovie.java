package mocha.yusuf.film5.Activity;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Database.AppDatabase;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;

public class DetailMovie extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String DB_NAME = "favorit";
    private static AppDatabase db;
    private static MovieModel movie;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DB_NAME).build();

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TextView movie_title = findViewById(R.id.movie_title);
        ImageView movie_image = findViewById(R.id.movie_image);
        TextView movie_release_date = findViewById(R.id.movie_release_date);
        TextView movie_score = findViewById(R.id.movie_score);
        TextView movie_overview = findViewById(R.id.movie_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        new checkFavoriteMovie().execute();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            insertFavoriteMovie();
            return true;
        }else if(id == R.id.action_un_favorite){
            deleteFavoriteMovie();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertFavoriteMovie(){
        new saveFavoriteMovie().execute();
    }

    private void deleteFavoriteMovie(){
        new deleteFavoriteMovie().execute();
    }

    class saveFavoriteMovie extends AsyncTask<Void, Void, Long> {
        @Override
        protected Long doInBackground(Void... voids) {
            return db.movieDao().insertMovie(movie);
        }

        @Override
        protected void onPostExecute(Long status) {
            menu.findItem(R.id.action_favorite).setVisible(false);
            menu.findItem(R.id.action_un_favorite).setVisible(true);
        }
    }

    class deleteFavoriteMovie extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return db.movieDao().deleteMovie(movie);
        }

        @Override
        protected void onPostExecute(Integer status) {
            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_un_favorite).setVisible(false);
        }
    }

    class checkFavoriteMovie extends AsyncTask<Void, Void, MovieModel> {

        @Override
        protected MovieModel doInBackground(Void... voids) {
            return db.movieDao().checkMovies(movie.getId());
        }

        @Override
        protected void onPostExecute(MovieModel movie) {
            if (movie == null){
                menu.findItem(R.id.action_un_favorite).setVisible(false);
            }else{
                menu.findItem(R.id.action_favorite).setVisible(false);
            }
        }
    }
}
