package com.example.footballteam.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.footballteam.R;
import com.example.footballteam.adapter.MatchOnDetailAdapter;
import com.example.footballteam.model.ModelSoccerTeams;
import com.example.footballteam.realm.RealmHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.RealmConfiguration;

public class DetailTeamsFavoriteActivity extends AppCompatActivity {

     RecyclerView recyclerView;
     MatchOnDetailAdapter adapter;
     ArrayList<ModelSoccerTeams> DataArrayList;

    Toolbar toolbar;
    TextView tvName;
    TextView tvStadiumNme;
    TextView tvStadiumLocation;
    TextView tvOverview;
    TextView tvSubJudul;
    TextView tvOverview2;
    TextView tvStadiumCapacity;
    TextView tvCountry;
    TextView tvDate;
    TextView delete;
    TextView tvTitle;
    ImageView imgCover, imgPhoto, imgBanner, imgYoutube, imgTwitter, imgWebsite, imgInstagram, imgFacebook;

    Integer idTeam;
    String Stadium;
    String Description;
    String Country;
    String LogoTeams;
    String TeamName;
    String SubName;
    String Years;
    String Youtube;
    String Facebook;
    String Twitter;
    String Instagram;
    String Website;
    String Date;
    String Banner;
    String StadiumDescription;
    String StadiumName;
    String StadiumLocation;
    String StadiumCapacity;
    ModelSoccerTeams modelMovie;
    ProgressDialog progressDialog;

    RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan trailer");

        imgCover = findViewById(R.id.imgCover);
        imgPhoto = findViewById(R.id.imgPhoto);
        delete = findViewById(R.id.delete);
        tvTitle = findViewById(R.id.tvTitle);
        tvName = findViewById(R.id.tvName);
        tvSubJudul = findViewById(R.id.tvSubJudul);
        tvStadiumNme = findViewById(R.id.tvStadiumName);
        tvStadiumCapacity = findViewById(R.id.tvStadiumCapacity);
        tvStadiumLocation = findViewById(R.id.tvStadiumLocation);
        tvOverview = findViewById(R.id.tvOverview);
        tvOverview2 = findViewById(R.id.tvOverview2);
        imgBanner = findViewById(R.id.imgBanner);
        tvCountry = findViewById(R.id.tvCountry);
        tvDate = findViewById(R.id.tvRealeseDate);
        imgFacebook = findViewById(R.id.imgFacebook);
        imgInstagram = findViewById(R.id.imgInstagram);
        imgTwitter = findViewById(R.id.imgTwitter);
        imgWebsite = findViewById(R.id.imgWebsite);
        imgYoutube = findViewById(R.id.imgYoutube);

        recyclerView = findViewById(R.id.rvMatchDetail);

        helper = new RealmHelper(this);

        modelMovie = (ModelSoccerTeams) getIntent().getSerializableExtra("detailMovie");
        if (modelMovie != null) {

            Stadium = modelMovie.getStadium();
            Date = modelMovie.getDate();
            idTeam = modelMovie.getIdTeam();
            Description = modelMovie.getDescription();
            Country = modelMovie.getCountry();
            LogoTeams = modelMovie.getLogoTeams();
            TeamName = modelMovie.getTeamName();
            SubName = modelMovie.getSubName();
            Years = modelMovie.getYears();
            Facebook = modelMovie.getFacebook();
            Youtube = modelMovie.getYoutube();
            Website = modelMovie.getWebsite();
            Instagram = modelMovie.getInstagram();
            Twitter = modelMovie.getTwitter();
            Banner = modelMovie.getBanner();
            StadiumDescription = modelMovie.getStadiumDescription();
            StadiumName = modelMovie.getStadiumName();
            StadiumLocation = modelMovie.getStadiumLocation();
            StadiumCapacity = modelMovie.getStadiumCapacity();

            tvName.setText(TeamName);
            tvSubJudul.setText(SubName);
            tvOverview.setText(Description);
            tvOverview2.setText(StadiumDescription);
            tvStadiumNme.setText(StadiumName);
            tvStadiumLocation.setText(StadiumLocation);
            tvStadiumCapacity.setText(StadiumCapacity);
            tvCountry.setText(Country + ", ");
            tvDate.setText(Years);

            Glide.with(this)
                    .load(Stadium)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCover);

            Glide.with(this)
                    .load(LogoTeams)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPhoto);

            Glide.with(this)
                    .load(Banner)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgBanner);

            imgYoutube.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://" + Youtube);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            imgFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://" + Facebook);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            imgWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://" + Website);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            imgInstagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://" + Instagram);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            imgTwitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://" + Twitter);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    helper.deleteFavoriteMovie(idTeam);
                                    Toast.makeText(DetailTeamsFavoriteActivity.this, TeamName+" deleted", Toast.LENGTH_SHORT).show();
                                    finish();
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(DetailTeamsFavoriteActivity.this);
                    builder.setMessage("Are you sure want to delete ?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }
            });

        }
        addOnlineData();
    }


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addOnlineData() {
        DataArrayList = new ArrayList<>();
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id="+idTeam)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelSoccerTeams dataApi = new ModelSoccerTeams();
                                dataApi.setHomeTeam(jsonObject.getString("strHomeTeam"));
                                dataApi.setAwayTeam(jsonObject.getString("strAwayTeam"));
                                dataApi.setHomeGoals(String.valueOf(jsonObject.getInt("intHomeScore")));
                                dataApi.setAwayGoals(String.valueOf(jsonObject.getInt("intAwayScore")));
                                dataApi.setLeague(jsonObject.getString("strLeague"));
                                dataApi.setDate(jsonObject.getString("dateEventLocal"));

                                DataArrayList.add(dataApi);
                            }

                            adapter = new MatchOnDetailAdapter(DataArrayList);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailTeamsFavoriteActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());

                    }
                });
    }
}
