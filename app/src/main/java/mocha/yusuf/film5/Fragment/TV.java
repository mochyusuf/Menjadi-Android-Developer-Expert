package mocha.yusuf.film5.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

import mocha.yusuf.film5.API.APIService;
import mocha.yusuf.film5.API.APIUrl;
import mocha.yusuf.film5.Adapter.TVAdapter;
import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Model.ListTVModel;
import mocha.yusuf.film5.Model.TVModel;
import mocha.yusuf.film5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TV extends Fragment {
    private TVAdapter adapter;
    private ArrayList<TVModel> tv_models;
    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_LANG = "extra_lang";
    public static String lang = "en";
    private RecyclerView recyclerView;
    public TV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_tv, container, false);
        adapter = new TVAdapter(getActivity());
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState == null || !(Locale.getDefault().getLanguage().equals(lang))){
            lang = Locale.getDefault().getLanguage();
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.process));
            progressDialog.show();
            progressDialog.setCancelable(false);

            APIService apiService = APIUrl.getClient().create(APIService.class);
            Call<ListTVModel> call = apiService.get_tvs(BuildConfig.API_KEY,getString(R.string.lang));
            call.enqueue(new Callback<ListTVModel>() {
                @Override
                public void onResponse(Call<ListTVModel> call, Response<ListTVModel> response) {
                    tv_models = response.body().getResults();
                    SetData();
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ListTVModel>call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }else{
            tv_models = savedInstanceState.getParcelableArrayList(EXTRA_TV);
            SetData();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(EXTRA_TV, tv_models);
        outState.putString(EXTRA_LANG, lang);
    }

    public void SetData(){
        recyclerView.setAdapter(adapter);
        adapter.setTvs(tv_models);
    }
}

