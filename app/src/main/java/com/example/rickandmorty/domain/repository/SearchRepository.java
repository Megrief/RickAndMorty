package com.example.rickandmorty.domain.repository;

import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;

public interface SearchRepository {
    Page search(String name, TypeOfData type);

//    Page getPage(String url);
}
