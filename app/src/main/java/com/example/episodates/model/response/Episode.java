package com.example.episodates.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Episode {

    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("name")
    @Expose
    private String name = "";

    @SerializedName("season")
    @Expose
    private int season = -1;

    @SerializedName("number")
    @Expose
    private String number = "";

    @SerializedName("airdate")
    @Expose
    private Date airdate;

    @SerializedName("image")
    @Expose
    private ImageEpisode imageEpisode = null;

    private class ImageEpisode {

        @SerializedName("original")
        @Expose
        private String original = "";

        public String getOriginal() {
            return original;
        }
    }

    @SerializedName("summary")
    @Expose
    private String summary = "";

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public String getNumber() {
        return number;
    }

    public Date getAirdate() {
        return airdate;
    }

    public ImageEpisode getImageEpisode() {
        return imageEpisode;
    }

    public String getSummary() {
        return summary;
    }
}
