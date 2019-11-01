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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Database.TVContract;
import mocha.yusuf.film5.Model.TVModel;
import mocha.yusuf.film5.R;

public class DetailTV extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";
    public static final String TAG = "DetailTV_TAG";

    public static final String DB_NAME = "favorit";
    private static TVModel tvModel;
    private boolean isfavorite;
    private String id_tv;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        tvModel = getIntent().getParcelableExtra(EXTRA_TV);

        TextView tv_title = findViewById(R.id.tv_title);
        ImageView tv_image = findViewById(R.id.tv_image);
        TextView tv_first_air_date = findViewById(R.id.tv_first_air_date);
        TextView tv_score = findViewById(R.id.tv_score);
        TextView tv_overview = findViewById(R.id.tv_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.id_tv = String.valueOf(tvModel.getId());

        tv_title.setText(tvModel.getName());
        Glide.with(this)
                .load(BuildConfig.API_IMAGE +tvModel.getPoster_path())
                .into(tv_image);
        tv_first_air_date.setText(tvModel.getFirst_air_date());
        tv_score.setText(String.valueOf((int)tvModel.getVote_average()*10));
        tv_overview.setText(tvModel.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorite, menu);
        this.menu = menu;
        if (!isFavorite(this.id_tv)){
            isfavorite = true;
            menu.findItem(R.id.action_un_favorite).setVisible(false);
        }else{
            menu.findItem(R.id.action_favorite).setVisible(false);
        }
        return true;
    }

    private boolean isFavorite(String id) {
        Uri uri = TVContract.CONTENT_URI.buildUpon().appendPath(id).build();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        return cursor.moveToFirst();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            isfavorite = true;
            ContentValues contentValues = new ContentValues();
            contentValues.put(TVContract.TVColumns.TV_id, id);
            contentValues.put(TVContract.TVColumns.TV_original_name, tvModel.getOriginal_name());
            contentValues.put(TVContract.TVColumns.TV_name, tvModel.getName());
            contentValues.put(TVContract.TVColumns.TV_popularity, tvModel.getPopularity());
            contentValues.put(TVContract.TVColumns.TV_vote_count, tvModel.getVote_count());
            contentValues.put(TVContract.TVColumns.TV_first_air_date, tvModel.getFirst_air_date());
            contentValues.put(TVContract.TVColumns.TV_backdrop_path, tvModel.getBackdrop_path());
            contentValues.put(TVContract.TVColumns.TV_original_language, tvModel.getOriginal_language());
            contentValues.put(TVContract.TVColumns.TV_vote_average, tvModel.getVote_average());
            contentValues.put(TVContract.TVColumns.TV_overview, tvModel.getOverview());
            contentValues.put(TVContract.TVColumns.TV_poster_path, tvModel.getPoster_path());
            Uri uri = getContentResolver().insert(TVContract.CONTENT_URI, contentValues);
            if (uri != null) {
                Log.i(TAG, "Uri " + uri);
            }
            menu.findItem(R.id.action_favorite).setVisible(false);
            menu.findItem(R.id.action_un_favorite).setVisible(true);
            return true;
        }else if(id == R.id.action_un_favorite){
            isfavorite = false;
            Uri uri = TVContract.CONTENT_URI.buildUpon().appendPath(this.id_tv).build();
            getContentResolver().delete(uri, null, null);
            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_un_favorite).setVisible(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
