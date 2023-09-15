package com.example.rickandmorty.domain.entities.data;

import com.example.rickandmorty.domain.entities.TypeOfData;
import com.google.gson.annotations.SerializedName;

public class EpisodeData extends Data {

    @SerializedName("name")
    String name;
    @SerializedName("air_date")
    String airDate;
    @SerializedName("episode")
    String episodeCode;
    @SerializedName("characters")
    String[] characters;
    @SerializedName("url")
    String url;
    @SerializedName("created")
    String postingDate;

    EpisodeData(
        int id,
        String name,
        String airDate,
        String episodeCode,
        String[] characters,
        String url,
        String postingDate
    ) {
        super(TypeOfData.EPISODE, id);
        this.name = name;
        this.airDate = airDate;
        this.episodeCode = episodeCode;
        this.characters = characters;
        this.url = url;
        this.postingDate = postingDate;
    }


    public String getName() {
        return name;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getEpisodeCode() {
        return episodeCode;
    }

    public String[] getCharacters() {
        return characters;
    }

    public String getUrl() {
        return url;
    }

    public String getPostingDate() {
        return postingDate;
    }

}

/*
id	-   int	 -   The id of the episode.
name    -	string  -	The name of the episode.
air_date    -	string  -	The air date of the episode.
episode -	string  -	The code of the episode.
characters  -	array (urls)    -	List of characters who have been seen in the episode.
url	    -   string (url)    -	Link to the episode's own endpoint.
created -	string  -	Time at which the episode was created in the database.
 */