package com.example.rickandmorty.ui.fragments.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.domain.entities.data.CharacterData;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.domain.entities.data.EpisodeData;
import com.example.rickandmorty.domain.entities.data.LocationData;
import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.domain.use_cases.SearchUseCase;
import com.example.rickandmorty.domain.use_cases.StoreDataUseCase;
import com.example.rickandmorty.ui.fragments.adapters.rv.DataObject;
import com.example.rickandmorty.ui.fragments.util.SearchDebouncer;
import com.example.rickandmorty.ui.fragments.ui_state.Empty;
import com.example.rickandmorty.ui.fragments.ui_state.Loading;
import com.example.rickandmorty.ui.fragments.ui_state.ScreenState;
import com.example.rickandmorty.ui.fragments.ui_state.ShowData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    private final Consumer<Page> consumer = page -> {
        ArrayList<DataObject> list = new ArrayList<>();
        if (page != null) {
            ArrayList<? extends Data> dataList = page.getData();
            switch (page.getTypeOfData()) {
                case CHARACTER: {
                    for (Data data : dataList) {
                        CharacterData item = (CharacterData) data;
                        DataObject dataObject = new DataObject(
                                item.getName(),
                                item.getSpecies(),
                                item.getStatus(),
                                page.getTypeOfData(),
                                item.getUrl(),
                                item.getImage()
                        );
                        list.add(dataObject);
                    }
                    if (!list.isEmpty()) {
                    }

                    screenState.postValue(new ShowData(list));
                    break;
                }
                case LOCATION: {
                    for (Data data: dataList) {
                        LocationData item = (LocationData) data;
                        DataObject dataObject = new DataObject(
                                item.getName(),
                                item.getType(),
                                item.getDimension(),
                                page.getTypeOfData(),
                                item.getUrl(),
                                ""
                        );
                        list.add(dataObject);
                    }
                    screenState.postValue(new ShowData(list));
                }
                case EPISODE: {
                    for (Data data: dataList) {
                        EpisodeData item = (EpisodeData) data;
                        DataObject dataObject = new DataObject(
                                item.getName(),
                                item.getAirDate(),
                                item.getEpisodeCode(),
                                page.getTypeOfData(),
                                item.getUrl(),
                                ""
                        );
                        list.add(dataObject);
                    }
                    screenState.postValue(new ShowData(list));
                    break;
                }
            }

        } else {
            screenState.postValue(new Empty());
        }
    };

    @NotNull
    private String name = "";

    @NotNull
    private TypeOfData type = TypeOfData.CHARACTER;
    @NotNull
    private final Runnable runnable = new Runnable() {

        @Override
        public void run() {
            screenState.postValue(new Loading());
            searchUseCase.doSearch(name, type, consumer);
        }
    };

    public void search(String name, TypeOfData type) {
        if (!this.name.equals(name)) {
            this.name = name;
            this.type = type;
            SearchDebouncer.searchDebounce(runnable);
        }
    }

    public void clearSearch() {
        if (screenState.getValue() instanceof ShowData) {
            screenState.postValue(new Empty());
        }
    }

}
