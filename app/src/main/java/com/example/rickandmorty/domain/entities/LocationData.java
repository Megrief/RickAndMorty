package com.example.rickandmorty.domain.entities;

import com.google.gson.annotations.SerializedName;

public class LocationData extends Data {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("type")
    String type;
    @SerializedName("dimension")
    String dimension;
    @SerializedName("residents")
    String[] residents;
    @SerializedName("url")
    String url;
    @SerializedName("created")
    String created;

    LocationData(
        int id,
        String name,
        String type,
        String dimension,
        String[] residents,
        String url,
        String created
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.residents = residents;
        this.url = url;
        this.created = created;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDimension() {
        return dimension;
    }
    public String[] getResidents() {
        return residents;
    }
    public String getUrl() {
        return url;
    }
    public String getCreated() {
        return created;
    }

}

/*
id	-   int     -	The id of the location.
name	-   string  - 	The name of the location.
type	-   string  -	The type of the location.
dimension	-   string  -	The dimension in which the location is located.
residents	-   array (urls)	-   List of character who have been last seen in the location.
url	    -   string (url)	-   Link to the location's own endpoint.
created	    -   string	-   Time at which the location was created in the database.
 */
