package com.example.episodates.model.reponse;

import android.text.format.Time;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class Serie {

    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("name")
    @Expose
    private String name = "";

    @SerializedName("language")
    @Expose
    private String language = "";

    @SerializedName("genres")
    @Expose
    private List<String> genres  = Collections.emptyList();

    @SerializedName("status")
    @Expose
    private String status = "";

    @SerializedName("premiered")
    @Expose
    private Date premiered;

    @SerializedName("officialSite")
    @Expose
    private String officialSite = "";

    @SerializedName("schedule")
    @Expose
    private Schedule schedule = null;

    private class Schedule{
        @SerializedName("time")
        @Expose
        private Time time;

        @SerializedName("days")
        @Expose
        private List<String> days  = Collections.emptyList();
    }

    @SerializedName("rating")
    @Expose
    private Rating rating = null;

    private class Rating{
        @SerializedName("average")
        @Expose
        private float average;
    }

    @SerializedName("webChannel")
    @Expose
    private WebChannel webChannel = null;

    private class WebChannel{
        @SerializedName("name")
        @Expose
        private String name = null;
    }

    @SerializedName("image")
    @Expose
    private ImageSerie image = null;

    private class ImageSerie {

        @SerializedName("original")
        @Expose
        private String original = "";
    }

    @SerializedName("summary")
    @Expose
    private String summary = "";

    @SerializedName("_embedded")
    @Expose
    private _Embedded _embedded = null;

    private class _Embedded {

        @SerializedName("original")
        @Expose
        private String original = "";

        @SerializedName("episodes")
        @Expose
        private List<Episode> episodes  = Collections.emptyList();

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public Date getPremiered() {
        return premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public WebChannel getWebChannel() {
        return webChannel;
    }

    public ImageSerie getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public _Embedded get_embedded() {
        return _embedded;
    }
}
