package com.example.footballteam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballteam.R;
import com.example.footballteam.activities.DetailTeamsFavoriteActivity;
import com.example.footballteam.adapter.TeamsAdapter;
import com.example.footballteam.model.ModelSoccerTeams;
import com.example.footballteam.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavoriteTeams extends Fragment implements TeamsAdapter.onSelectData {

    RecyclerView rvMovieFav;
    List<ModelSoccerTeams> modelMovie = new ArrayList<>();
    RealmHelper helper;
    TextView txtNoData;

    public FragmentFavoriteTeams() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite_soccer, container, false);

        helper = new RealmHelper(getActivity());

        txtNoData = rootView.findViewById(R.id.tvNotFound);
        rvMovieFav = rootView.findViewById(R.id.rvMovieFav);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieFav.setAdapter(new TeamsAdapter(getActivity(), modelMovie, this));
        rvMovieFav.setHasFixedSize(true);

        setData();

        return rootView;
    }

    private void setData() {
        modelMovie = helper.showFavoriteMovie();
        if (modelMovie.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            rvMovieFav.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            rvMovieFav.setVisibility(View.VISIBLE);
            rvMovieFav.setAdapter(new TeamsAdapter(getActivity(), modelMovie, this));
        }
    }

    @Override
    public void onSelected(ModelSoccerTeams modelMovie) {
        Intent intent = new Intent(getActivity(), DetailTeamsFavoriteActivity.class);
        intent.putExtra("detailMovie", modelMovie);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }


}
