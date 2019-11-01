package mocha.yusuf.favorit.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mocha.yusuf.favorit.BuildConfig;
import mocha.yusuf.favorit.Database.MovieContract;
import mocha.yusuf.favorit.Model.MovieModel;
import mocha.yusuf.favorit.R;

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
}
