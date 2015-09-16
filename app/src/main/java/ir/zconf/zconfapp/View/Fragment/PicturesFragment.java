package ir.zconf.zconfapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ir.zconf.zconfapp.Adapter.PictureAdapter;
import ir.zconf.zconfapp.DB.DBAdapter;
import ir.zconf.zconfapp.DB.Model.PictureModel;
import ir.zconf.zconfapp.Entity.PictureEntity;
import ir.zconf.zconfapp.Entity.PictureRespunse;
import ir.zconf.zconfapp.Interface.Retrofit.GetPictureApi;
import ir.zconf.zconfapp.R;
import ir.zconf.zconfapp.StaticField;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PicturesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PictureAdapter adapter;
    private DBAdapter db;
    private SwipeRefreshLayout swipeContainer;
    private ArrayList<PictureEntity> pictures;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pictures, container, false);

        pictures = new ArrayList<>();
        db = new DBAdapter(getActivity());

        progressBar = (ProgressBar) view.findViewById(R.id.loading);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PictureAdapter(getActivity(), pictures);
        recyclerView.setAdapter(adapter);

        showData();

        return view;
    }

    public void getFromServer(boolean progress) {
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
            swipeContainer.setVisibility(View.GONE);
        }
        swipeContainer.setRefreshing(true);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(StaticField.domain)
                .build();

        GetPictureApi getPictureApi = restAdapter.create(GetPictureApi.class);
        getPictureApi.getPicture(
                new Callback<PictureRespunse>() {
                    @Override
                    public void success(PictureRespunse pictureRespunse, Response response) {
                        pictures.clear();
                        Collections.addAll(pictures, pictureRespunse.getPicture());

                        db.insertOrUpdatePictures(pictureRespunse.getPicture());
                        swipeContainer.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        swipeContainer.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressBar.setVisibility(View.GONE);
                        swipeContainer.setRefreshing(false);
                        swipeContainer.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), getString(R.string.error_connection),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void showData() {
        List<PictureModel> pictureModels = db.getAllPicture();
        pictures.clear();

        for (int i = 0; i < pictureModels.size(); i++) {
            PictureEntity pictureEntity = new PictureEntity();
            pictureEntity.setUrl(pictureModels.get(i).getUrl());
            pictureEntity.setId(pictureModels.get(i).getId());
            pictureEntity.setDescription(pictureModels.get(i).getDescription());
            pictures.add(pictureEntity);
        }

        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.VISIBLE);

        if (pictures.size() == 0) {
            getFromServer(true);
        }
    }

    @Override
    public void onRefresh() {
        getFromServer(false);
    }
}