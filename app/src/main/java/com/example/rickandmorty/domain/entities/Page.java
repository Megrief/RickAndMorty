package com.example.rickandmorty.domain.entities;

import com.example.rickandmorty.domain.entities.data.Data;

import java.util.List;

public class Page {
    final String previousUrl;
    final String nextUrl;
    final List<? extends Data> data;


    public Page(
            String previousUrl,
            String nextUrl,
            List<? extends Data> data
    ) {
        this.previousUrl = previousUrl;
        this.nextUrl = nextUrl;
        this.data = data;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public List<? extends Data> getData() {
        return data;
    }

}
