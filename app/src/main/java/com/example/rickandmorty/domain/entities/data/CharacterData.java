package com.example.rickandmorty.domain.entities.data;

import com.google.gson.annotations.SerializedName;

public class CharacterData extends Data {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("status")
    String status;
    @SerializedName("species")
    String species;
    @SerializedName("type")
    String type;
    @SerializedName("gender")
    String gender;
    @SerializedName("origin")
    Location origin;
    @SerializedName("location")
    Location location;
    @SerializedName("image")
    String image ;
    @SerializedName("episode")
    String[] episode;
    @SerializedName("url")
    String url;
    @SerializedName("created")
    String created;

    CharacterData(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        Location origin,
        Location location,
        String image ,
        String[] episode,
        String url,
        String created
    ) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image  = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getStatus() {
        return status;
    }
    public String getSpecies() {
        return species;
    }
    public String getType() {
        return type;
    }
    public String getGender() {
        return gender;
    }
    public Location getOrigin() {
        return origin;
    }
    public Location getLocation() {
        return location;
    }
    public String getImage () {
        return image;
    }
    public String[] getEpisode() {
        return episode;
    }
    public String getUrl() {
        return url;
    }
    public String getCreated() {
        return created;
    }

    class Location {
        String name;
        String url;

        Location(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

}

/*
id	int	The id of the character.
name	string	The name of the character.
status	string	The status of the character ('Alive', 'Dead' or 'unknown').
species	string	The species of the character.
type	string	The type or subspecies of the character.
gender	string	The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
origin	object	Name and link to the character's origin location.
location	object	Name and link to the character's last known location endpoint.
image	string (url)	Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
episode	array (urls)	List of episodes in which this character appeared.
url	string (url)	Link to the character's own URL endpoint.
created	string	Time at which the character was created in the database.
 */