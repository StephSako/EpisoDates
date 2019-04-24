package com.example.episodates.model.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Serie {

    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("name")
    @Expose
    private String name = "";

    @SerializedName("nationality")
    @Expose
    private String nationality = "";

    @SerializedName("position")
    @Expose
    private String position = "";

    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth = "";

    @SerializedName("shirtNumber")
    @Expose
    private int shirtNumber = -1;

    @SerializedName("role")
    @Expose
    private String role = "";

    public String getRole() { return role; }

    public String getDateOfBirth() { return dateOfBirth; }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPosition() {
        return position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

}
