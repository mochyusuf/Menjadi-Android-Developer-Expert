package mocha.yusuf.film5.Fragment;

import android.database.Cursor;
import android.os.AsyncTask;
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

import mocha.yusuf.film5.Adapter.TVAdapter;
import mocha.yusuf.film5.Database.TVContract;
import mocha.yusuf.film5.Model.TVModel;
import mocha.yusuf.film5.R;

public class FavoriteTV extends Fragment {

    public static final String DB_NAME = "favorit";
    private TVAdapter adapter;
    private ArrayList<TVModel> tv_Models;

    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_LANG = "extra_lang";
    public static final String TAG = "TV_TAG";
    private RecyclerView recyclerView;

    public FavoriteTV() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tv, container, false);

        adapter = new TVAdapter(view.getContext());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tv_Models = new ArrayList<>();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<TVModel> tvModels = new ArrayList<>();
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(TVContract.CONTENT_URI, null,
                null, null, null, null);
        Objects.requireNonNull(cursor).moveToFirst();
        if (Objects.requireNonNull(cursor).getCount() > 0) {
            do {
                TVModel tvModel = new TVModel(cursor);
                tvModels.add(tvModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        tv_Models.clear();
        tv_Models.addAll(tvModels);
        recyclerView.setAdapter(adapter);
        adapter.setTvs(tv_Models);
    }
}
