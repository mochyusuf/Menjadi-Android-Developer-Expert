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
import mocha.yusuf.film5.Model.TVModel;
import mocha.yusuf.film5.R;

public class DetailTV extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";

    public static final String DB_NAME = "favorit";
    private static AppDatabase db;
    private static TVModel tvModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DB_NAME).build();

        tvModel = getIntent().getParcelableExtra(EXTRA_TV);

        TextView tv_title = findViewById(R.id.tv_title);
        ImageView tv_image = findViewById(R.id.tv_image);
        TextView tv_first_air_date = findViewById(R.id.tv_first_air_date);
        TextView tv_score = findViewById(R.id.tv_score);
        TextView tv_overview = findViewById(R.id.tv_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        new checkFavoriteTV(menu).execute();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            insertFavoriteTV();
            return true;
        }else if(id == R.id.action_un_favorite){
            deleteFavoriteTV();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertFavoriteTV(){
        new saveFavoriteTV().execute();
    }

    private void deleteFavoriteTV(){
        new deleteFavoriteTV().execute();
    }

    class saveFavoriteTV extends AsyncTask<Void, Void, Long> {
        @Override
        protected Long doInBackground(Void... voids) {
            return db.tvDao().insertTV(tvModel);
        }

        @Override
        protected void onPostExecute(Long status) {
            menu.findItem(R.id.action_favorite).setVisible(false);
            menu.findItem(R.id.action_un_favorite).setVisible(true);
        }
    }

    class deleteFavoriteTV extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return db.tvDao().deleteTV(tvModel);
        }

        @Override
        protected void onPostExecute(Integer status) {
            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_un_favorite).setVisible(false);
        }
    }

    class checkFavoriteTV extends AsyncTask<Void, Void, TVModel> {

        Menu menu;

        checkFavoriteTV(Menu menu){
            this.menu = menu;
        }

        @Override
        protected TVModel doInBackground(Void... voids) {
            return db.tvDao().checkTV(tvModel.getId());
        }

        @Override
        protected void onPostExecute(TVModel movie) {
            if (movie == null){
                menu.findItem(R.id.action_un_favorite).setVisible(false);
            }else{
                menu.findItem(R.id.action_favorite).setVisible(false);
            }
        }
    }
}
