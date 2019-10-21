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

import mocha.yusuf.film5.Adapter.TVAdapter;
import mocha.yusuf.film5.Database.AppDatabase;
import mocha.yusuf.film5.Model.TVModel;
import mocha.yusuf.film5.R;

public class FavoriteTV extends Fragment {

    public static final String DB_NAME = "favorit";
    private static AppDatabase db;
    private TVAdapter adapter;
    private ArrayList<TVModel> tvModels;

    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_LANG = "extra_lang";
    public static final String TAG = "TV_TAG";
    public static String lang = "en";
    private RecyclerView recyclerView;

    public FavoriteTV() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tv, container, false);

        db = Room.databaseBuilder(view.getContext(),
                AppDatabase.class, DB_NAME).build();

        adapter = new TVAdapter(view.getContext());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvModels = new ArrayList<>();
        new FavoriteTV.getFavoriteTV().execute();
        return view;
    }

    class getFavoriteTV extends AsyncTask<Void, Void, List<TVModel>> {

        @Override
        protected List<TVModel> doInBackground(Void... voids) {
            return db.tvDao().getTVs();
        }

        @Override
        protected void onPostExecute(List<TVModel> tvModel) {
            tvModels.addAll(tvModel);
            recyclerView.setAdapter(adapter);
            adapter.setTvs(tvModels);
        }
    }
}
