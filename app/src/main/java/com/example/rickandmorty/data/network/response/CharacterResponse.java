package com.example.rickandmorty.data.network.response;

import com.example.rickandmorty.domain.entities.data.CharacterData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterResponse extends ApiResponse {

    @SerializedName("info")
    Info info;
    @SerializedName("results")
    List<CharacterData> results;

    public CharacterResponse(
            Info info,
            List<CharacterData> results
    ) {
        this.info = info;
        this.results = results;
    }
    public List<CharacterData> getResults() {
        return results;
    }

    public Info getInfo() {
        return info;
    }
}
