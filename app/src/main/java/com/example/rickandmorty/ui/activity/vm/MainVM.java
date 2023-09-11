package com.example.rickandmorty.ui.activity.vm;

import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.domain.use_cases.GetPageUseCase;
import com.example.rickandmorty.domain.use_cases.SearchUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainVM extends ViewModel {
    private final SearchUseCase searchUseCase;
    private GetPageUseCase getPageUseCase;

    @Inject
    public MainVM(
            SearchUseCase searchUseCase,
            GetPageUseCase getPageUseCase
    ) {
        this.searchUseCase = searchUseCase;
        this.getPageUseCase = getPageUseCase;
    }

}
