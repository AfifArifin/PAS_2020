package com.example.footballteam.realm;

import android.content.Context;

import com.example.footballteam.model.ModelSoccerTeams;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;
    RealmResults<ModelSoccerTeams> modelMovie;

    public RealmHelper(Context mContext) {
        Realm.init(mContext);
        realm = Realm.getDefaultInstance();
    }

    public ArrayList<ModelSoccerTeams> showFavoriteMovie() {
        ArrayList<ModelSoccerTeams> data = new ArrayList<>();
        modelMovie = realm.where(ModelSoccerTeams.class).findAll();

        if (modelMovie.size() > 0) {
            for (int i = 0; i < modelMovie.size(); i++) {
                ModelSoccerTeams movie = new ModelSoccerTeams();
                movie.setIdTeam(modelMovie.get(i).getIdTeam());
                movie.setStadium(modelMovie.get(i).getStadium());
                movie.setDate(modelMovie.get(i).getDate());
                movie.setDescription(modelMovie.get(i).getDescription());
                movie.setCountry(modelMovie.get(i).getCountry());
                movie.setLogoTeams(modelMovie.get(i).getLogoTeams());
                movie.setTeamName(modelMovie.get(i).getTeamName());
                movie.setSubName(modelMovie.get(i).getSubName());
                movie.setYears(modelMovie.get(i).getYears());
                movie.setFacebook(modelMovie.get(i).getFacebook());
                movie.setYoutube(modelMovie.get(i).getYoutube());
                movie.setWebsite(modelMovie.get(i).getWebsite());
                movie.setInstagram(modelMovie.get(i).getInstagram());
                movie.setTwitter(modelMovie.get(i).getTwitter());
                movie.setBanner(modelMovie.get(i).getBanner());
                movie.setStadiumDescription(modelMovie.get(i).getStadiumDescription());
                movie.setStadiumName(modelMovie.get(i).getStadiumName());
                movie.setStadiumLocation(modelMovie.get(i).getStadiumLocation());
                movie.setStadiumCapacity(modelMovie.get(i).getStadiumCapacity());
                data.add(movie);
            }
        }
        return data;
    }

    public void deleteFavoriteMovie(int id) {
        realm.beginTransaction();
        RealmResults<ModelSoccerTeams> modelMovie = realm.where(ModelSoccerTeams.class).equalTo("idTeam", id).findAll();
        modelMovie.deleteAllFromRealm();
        realm.commitTransaction();

    }


    public void addFavoriteMovie(String Stadium, String Date, Integer idTeam, String Description, String Country, String LogoTeams, String TeamName, String SubName, String Years, String Youtube, String Facebook, String Twitter, String Instagram, String Website, String Banner, String StadiumDescription, String StadiumName, String StadiumLocation, String StadiumCapacity) {

        ModelSoccerTeams movie = new ModelSoccerTeams();
        movie.setIdTeam(idTeam);
        movie.setStadium(Stadium);
        movie.setDate(Date);
        movie.setDescription(Description);
        movie.setCountry(Country);
        movie.setLogoTeams(LogoTeams);
        movie.setTeamName(TeamName);
        movie.setSubName(SubName);
        movie.setYears(Years);
        movie.setFacebook(Facebook);
        movie.setYoutube(Youtube);
        movie.setWebsite(Website);
        movie.setInstagram(Instagram);
        movie.setTwitter(Twitter);
        movie.setBanner(Banner);
        movie.setStadiumDescription(StadiumDescription);
        movie.setStadiumName(StadiumName);
        movie.setStadiumLocation(StadiumLocation);
        movie.setStadiumCapacity(StadiumCapacity);

        realm.beginTransaction();
        realm.copyToRealm(movie);
        realm.commitTransaction();

    }

}
