package com.example.footballteam.fragment;


import android.app.ProgressDialog;
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
import com.example.footballteam.adapter.EventAdapter;
import com.example.footballteam.model.ModelSoccerTeams;
import com.example.footballteam.networking.ApiEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentLastEvent extends Fragment {

    RecyclerView rvMovieFav;
    List<ModelSoccerTeams> modelMovie = new ArrayList<>();
    EventAdapter eventAdapter;
    ProgressDialog progressDialog;

    public FragmentLastEvent() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvMovieFav = rootView.findViewById(R.id.rvEventUpComing);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieFav.setHasFixedSize(true);

        getMovieHorizontal();
        return rootView;
    }

    private void getMovieHorizontal() {
        progressDialog.show();
        AndroidNetworking.get(ApiEndpoint.LASTMATCH)
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
                                dataApi.setHomeGoals(String.valueOf(jsonObject.getInt("intHomeScore")));
                                dataApi.setAwayGoals(String.valueOf(jsonObject.getInt("intAwayScore")));
                                dataApi.setLeague(jsonObject.getString("strLeague"));
                                dataApi.setDate(jsonObject.getString("dateEventLocal"));

                                progressDialog.dismiss();

                                modelMovie.add(dataApi);
                                showMovie();

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

    private void showMovie() {
        eventAdapter = new EventAdapter(getActivity(), modelMovie);
        rvMovieFav.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }

}
