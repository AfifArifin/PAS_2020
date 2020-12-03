package com.example.footballteam.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.footballteam.R;
import com.example.footballteam.activities.DetailTeamsActivity;
import com.example.footballteam.adapter.SoccerTeamsHorizontalAdapter;
import com.example.footballteam.adapter.TeamsAdapter;
import com.example.footballteam.model.ModelSoccerTeams;
import com.example.footballteam.networking.ApiEndpoint;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentTeams extends Fragment implements SoccerTeamsHorizontalAdapter.onSelectData, TeamsAdapter.onSelectData {

    RecyclerView rvNowPlaying, rvFilmRecommend;
    SoccerTeamsHorizontalAdapter movieHorizontalAdapter;
    TeamsAdapter movieAdapter;
    ProgressDialog progressDialog;
    List<ModelSoccerTeams> moviePlayNow = new ArrayList<>();
    List<ModelSoccerTeams> moviePopular = new ArrayList<>();

    public FragmentTeams() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvNowPlaying = rootView.findViewById(R.id.rvNowPlaying);
        rvNowPlaying.setHasFixedSize(true);
        rvNowPlaying.setLayoutManager(new CardSliderLayoutManager(getActivity()));
        new CardSnapHelper().attachToRecyclerView(rvNowPlaying);

        rvFilmRecommend = rootView.findViewById(R.id.rvFilmRecommend);
        rvFilmRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFilmRecommend.setHasFixedSize(true);

        getMovieHorizontal();
        getMovie();

        return rootView;
    }


    private void getMovieHorizontal() {
        AndroidNetworking.get(ApiEndpoint.UPCOMINGMATCH)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Get Horizontal : ", response.toString());
                            JSONArray jsonArray = response.getJSONArray("events");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelSoccerTeams dataApi = new ModelSoccerTeams();
                                dataApi.setHomeTeam(jsonObject.getString("strHomeTeam"));
                                dataApi.setAwayTeam(jsonObject.getString("strAwayTeam"));
                                dataApi.setLeague(jsonObject.getString("strLeague"));
                                dataApi.setDate(jsonObject.getString("dateEventLocal"));
                                dataApi.setTime(jsonObject.getString("strTimeLocal"));

                                moviePlayNow.add(dataApi);
                                showMovieHorizontal();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getMovie() {
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.ALLTEAMS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("GetAllTeams :", response.toString());
                            moviePopular = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("teams");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelSoccerTeams dataApi = new ModelSoccerTeams();

                                dataApi.setIdTeam(Integer.parseInt(jsonObject.getString("idTeam")));
                                dataApi.setDescription(jsonObject.getString("strDescriptionEN"));
                                dataApi.setCountry(jsonObject.getString("strCountry"));
                                dataApi.setTeamName(jsonObject.getString("strTeam"));
                                dataApi.setSubName(jsonObject.getString("strAlternate"));
                                dataApi.setLogoTeams(jsonObject.getString("strTeamBadge"));
                                dataApi.setYears(jsonObject.getString("intFormedYear"));
                                dataApi.setYoutube(jsonObject.getString("strYoutube"));
                                dataApi.setTwitter(jsonObject.getString("strTwitter"));
                                dataApi.setInstagram(jsonObject.getString("strInstagram"));
                                dataApi.setWebsite(jsonObject.getString("strWebsite"));
                                dataApi.setFacebook(jsonObject.getString("strFacebook"));
                                dataApi.setStadium(jsonObject.getString("strStadiumThumb"));
                                dataApi.setBanner(jsonObject.getString("strTeamBanner"));
                                dataApi.setStadiumDescription(jsonObject.getString("strStadiumDescription"));
                                dataApi.setStadiumName(jsonObject.getString("strStadium"));
                                dataApi.setStadiumLocation(jsonObject.getString("strStadiumLocation"));
                                dataApi.setStadiumCapacity(jsonObject.getString("intStadiumCapacity"));

                                progressDialog.dismiss();

                                moviePopular.add(dataApi);
                                showMovie();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showMovieHorizontal() {
        movieHorizontalAdapter = new SoccerTeamsHorizontalAdapter(getActivity(), moviePlayNow, this);
        rvNowPlaying.setAdapter(movieHorizontalAdapter);
        movieHorizontalAdapter.notifyDataSetChanged();
    }

    private void showMovie() {
        movieAdapter = new TeamsAdapter(getActivity(), moviePopular, this);
        rvFilmRecommend.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    public void onSelected(ModelSoccerTeams modelMovie) {
        Intent intent = new Intent(getActivity(), DetailTeamsActivity.class);
        intent.putExtra("detailMovie", modelMovie);
        startActivity(intent);
    }
}