package com.example.footballteam.model;

import java.io.Serializable;

import io.realm.RealmObject;


public class ModelSoccerTeams extends RealmObject implements Serializable {


    String Date;
    Integer idTeam;
    String Description;
    String Country;
    String League;
    String HomeTeam;
    String AwayTeam;
    String HomeGoals;
    String AwayGoals;
    String LogoTeams;
    String TeamName;
    String SubName;
    String Years;
    String Youtube;
    String Facebook;
    String Twitter;
    String Instagram;
    String Website;
    String Stadium;
    String Banner;
    String StadiumDescription;
    String StadiumName;
    String StadiumLocation;
    String StadiumCapacity;
    String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStadiumCapacity() {
        return StadiumCapacity;
    }

    public void setStadiumCapacity(String stadiumCapacity) {
        StadiumCapacity = stadiumCapacity;
    }

    public String getStadiumName() {
        return StadiumName;
    }

    public void setStadiumName(String stadiumName) {
        StadiumName = stadiumName;
    }

    public String getStadiumLocation() {
        return StadiumLocation;
    }

    public void setStadiumLocation(String stadiumLocation) {
        StadiumLocation = stadiumLocation;
    }

    public String getStadiumDescription() {
        return StadiumDescription;
    }

    public void setStadiumDescription(String stadiumDescription) {
        StadiumDescription = stadiumDescription;
    }

    public String getBanner() {
        return Banner;
    }

    public void setBanner(String banner) {
        Banner = banner;
    }

    public String getStadium() {
        return Stadium;
    }

    public void setStadium(String stadium) {
        Stadium = stadium;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public String getYoutube() {
        return Youtube;
    }

    public void setYoutube(String youtube) {
        Youtube = youtube;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String twitter) {
        Twitter = twitter;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getYears() {
        return Years;
    }

    public void setYears(String years) {
        Years = years;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLogoTeams() {
        return LogoTeams;
    }

    public void setLogoTeams(String logoTeams) {
        LogoTeams = logoTeams;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        AwayTeam = awayTeam;
    }

    public String getHomeGoals() {
        return HomeGoals;
    }

    public void setHomeGoals(String homeGoals) {
        HomeGoals = homeGoals;
    }

    public String getAwayGoals() {
        return AwayGoals;
    }

    public void setAwayGoals(String awayGoals) {
        AwayGoals = awayGoals;
    }
}
