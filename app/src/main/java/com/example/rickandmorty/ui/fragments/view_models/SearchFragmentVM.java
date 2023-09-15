package com.example.rickandmorty.ui.fragments.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.domain.use_cases.SearchUseCase;
import com.example.rickandmorty.domain.use_cases.StoreDataUseCase;
import com.example.rickandmorty.ui.fragments.ui_state.Empty;
import com.example.rickandmorty.ui.fragments.ui_state.Loading;
import com.example.rickandmorty.ui.fragments.ui_state.ScreenState;
import com.example.rickandmorty.ui.fragments.ui_state.ShowData;
import com.example.rickandmorty.ui.fragments.util.SearchDebouncer;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SearchFragmentVM extends ViewModel {

    private final SearchUseCase searchUseCase;
    private StoreDataUseCase storeDataUseCase;

    @Inject
    public SearchFragmentVM(
        SearchUseCase searchUseCase,
        StoreDataUseCase storeDataUseCase
    ) {
        this.searchUseCase = searchUseCase;
        this.storeDataUseCase = storeDataUseCase;
    }

    private final MutableLiveData<ScreenState> screenState = new MutableLiveData<>(new Empty());


    public LiveData<ScreenState> getScreenState() {
        return screenState;
    }



    @NotNull
    private String name = "";

    @NotNull
    private TypeOfData type = TypeOfData.LOCATION;

    private final Consumer<Page> consumer = page -> {
        if (page != null) {
            screenState.postValue(new ShowData(page.getData()));
        } else {
            screenState.postValue(new Empty());
        }
    };

    @NotNull
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            screenState.postValue(new Loading());
            searchUseCase.doSearch(name, type, consumer);
        }
    };
    public void search(String name, TypeOfData type) {
        if (!this.type.equals(type)) {
            this.type = type;
        }
        if (!this.name.equals(name)) {
            this.name = name;
            SearchDebouncer.searchDebounce(runnable);
        }
    }

    public void clearSearch() {
        if (screenState.getValue() instanceof ShowData) {
            screenState.postValue(new Empty());
        }
    }

}
