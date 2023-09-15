package com.example.rickandmorty.data.network.response;

import com.example.rickandmorty.domain.entities.data.EpisodeData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class EpisodeResponse extends ApiResponse {
    @SerializedName("info")
    Info info;
    @SerializedName("results")
    List<EpisodeData> results;

    public EpisodeResponse(
            Info info,
            List<EpisodeData> results
    ) {
        this.info = info;
        this.results = results;
    }
    public List<EpisodeData> getResults() {
        return results;
    }
    public Info getInfo() {
        return info;
    }
}
