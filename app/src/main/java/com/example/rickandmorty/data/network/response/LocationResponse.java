package com.example.rickandmorty.data.network.response;

import com.example.rickandmorty.domain.entities.data.LocationData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationResponse extends ApiResponse {
    @SerializedName("info")
    Info info;
    @SerializedName("results")
    List<LocationData> results;

    public LocationResponse(
            Info info,
            List<LocationData> results
    ) {
        this.info = info;
        this.results = results;
    }
    public List<LocationData> getResults() {
        return results;
    }
    public Info getInfo() {
        return info;
    }
}
