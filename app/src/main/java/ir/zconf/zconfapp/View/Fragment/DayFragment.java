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

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;

import ir.zconf.zconfapp.Adapter.DayAdapter;
import ir.zconf.zconfapp.DB.DBAdapter;
import ir.zconf.zconfapp.DB.Model.DayModel;
import ir.zconf.zconfapp.Entity.DataEntity;
import ir.zconf.zconfapp.Entity.SubjectEntity;
import ir.zconf.zconfapp.Interface.Retrofit.GetDataApi;
import ir.zconf.zconfapp.R;
import ir.zconf.zconfapp.StaticField;
import ir.zconf.zconfapp.Util.Tools;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DayFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DayAdapter adapter;
    private DBAdapter db;
    private SwipeRefreshLayout swipeContainer;
    private ArrayList<SubjectEntity> subjects;
    private Tools tools;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        tools = new Tools(getActivity());
        subjects = new ArrayList<>();
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
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DayAdapter(getActivity(), subjects);
        recyclerView.setAdapter(adapter);


        if (tools.isOnline()) {
            getFromServer(true);
        } else {
            showData();
        }

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

        GetDataApi getDataApi = restAdapter.create(GetDataApi.class);
        getDataApi.getData(
                new Callback<DataEntity>() {
                    @Override
                    public void success(DataEntity dataEntity, Response response) {
                        subjects.clear();
                        Collections.addAll(subjects, dataEntity.getDay()[0].getSubject());

                        db.insertOrUpdateDay(dataEntity.getDay()[0]);
                        swipeContainer.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        swipeContainer.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
//                        Toast.makeText(getActivity(), getString(R.string.error_connection),
//                                Toast.LENGTH_LONG).show();
                        showData();
                    }
                }
        );
    }


    private void showData() {
        DayModel day = db.getDay("0");
        subjects.clear();

        if (day != null) {

            for (int i = 0; i < day.getSubject().size(); i++) {
                SubjectEntity subject = new SubjectEntity();
                subject.setTitle(day.getSubject().get(i).getTitle());
                subject.setImage(day.getSubject().get(i).getImage());
                subject.setSpeaker(day.getSubject().get(i).getSpeaker());
                subject.setStart(day.getSubject().get(i).getStart());
                subject.setTime(day.getSubject().get(i).getTime());
                subject.setType(day.getSubject().get(i).getType());
                subjects.add(subject);
            }
        }

        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        swipeContainer.setVisibility(View.VISIBLE);

        if (subjects.size() == 0) {
//            Toast.makeText(getActivity(), getString(R.string.list_empty),
//                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRefresh() {
        getFromServer(false);
    }
}