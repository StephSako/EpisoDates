package com.example.episodates.model.response;

import android.text.format.Time;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Date;
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
    private Date premiered = null;

    @SerializedName("officialSite")
    @Expose
    private String officialSite = "";

    @SerializedName("schedule")
    @Expose
    private Schedule schedule = new Schedule();

    public class Schedule{
        @SerializedName("time")
        @Expose
        private String time;

        @SerializedName("days")
        @Expose
        private List<String> days = Collections.emptyList();

        public String getTime() {
            return time;
        }

        public List<String> getDays() {
            return days;
        }
    }

    @SerializedName("rating")
    @Expose
    private Rating rating = new Rating();

    public class Rating{
        @SerializedName("average")
        @Expose
        private float average = 0;

        public float getAverage() {
            return average;
        }
    }

    @SerializedName("webChannel")
    @Expose
    private WebChannel webChannel = new WebChannel();

    public class WebChannel{
        @SerializedName("name")
        @Expose
        private String name = "";

        public String getName() {
            return name;
        }
    }

    @SerializedName("network")
    @Expose
    private Network network = new Network();

    public class Network{
        @SerializedName("name")
        @Expose
        private String name = "";

        public String getName() {
            return name;
        }
    }

    @SerializedName("image")
    @Expose
    private ImageSerie image = new ImageSerie();

    public class ImageSerie {

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

    @SerializedName("_embedded")
    @Expose
    private _Embedded _embedded = new _Embedded();

    public class _Embedded {

        @SerializedName("episodes")
        @Expose
        private List<Episode> episodes  = Collections.emptyList();

        public List<Episode> getEpisodes() {
            return episodes;
        }
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

    public Network getNetwork() {
        return network;
    }

    public Date getFutureDate(){
        Date today = new Date();
        for(int i = 0; i < this.get_embedded().getEpisodes().size(); i++) {

            if (today.before(this.get_embedded().getEpisodes().get(i).getAirdate())) {
                return this.get_embedded().getEpisodes().get(i).getAirdate();
            }
        }
        return null;
    }

    public String getFutureEpisode(){
        Date today = new Date();
        for(int i = 0; i < this.get_embedded().getEpisodes().size(); i++) {

            if (today.before(this.get_embedded().getEpisodes().get(i).getAirdate())) {
                return "S" + this.get_embedded().getEpisodes().get(i).getSeason() + "E" + this.get_embedded().getEpisodes().get(i).getNumber();
            }
        }
        return "";
    }
}
