package com.example.rickandmorty.ui.main.vm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.domain.entities.CharacterData;
import com.example.rickandmorty.domain.entities.Data;
import com.example.rickandmorty.domain.entities.EpisodeData;
import com.example.rickandmorty.domain.entities.LocationData;
import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.domain.use_cases.GetPageUseCase;
import com.example.rickandmorty.domain.use_cases.SearchUseCase;
import com.example.rickandmorty.ui.main.adapter.DataObject;
import com.example.rickandmorty.ui.main.util.SearchDebouncer;
import com.example.rickandmorty.ui.main.vm.state.ScreenState;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Consumer;

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

    private final MutableLiveData<ScreenState> screenState = new MutableLiveData<>(new ScreenState.Empty());

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
                                item.getUrl()
                        );
                        list.add(dataObject);
                    }
                    if (!list.isEmpty()) {
                        Log.e("AAA", "list with data is not empty");
                    }
                    screenState.postValue(new ScreenState.ShowData(
                            list
                    ));
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
                                item.getUrl()
                        );
                        list.add(dataObject);
                    }
                    screenState.postValue(new ScreenState.ShowData(
                            list
                    ));
                }
                case EPISODE: {
                    for (Data data: dataList) {
                        EpisodeData item = (EpisodeData) data;
                        DataObject dataObject = new DataObject(
                                item.getName(),
                                item.getAirDate(),
                                item.getEpisodeCode(),
                                page.getTypeOfData(),
                                item.getUrl()
                        );
                        list.add(dataObject);
                    }
                    screenState.postValue(new ScreenState.ShowData(
                            list
                    ));
                    break;
                }
            }
        } else {
            screenState.postValue(new ScreenState.Empty());
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
            screenState.postValue(new ScreenState.Loading());
            searchUseCase.doSearch(name, type, consumer);
        }
    };

    public void search(String name, TypeOfData type) {
        Log.e("AAA", "InSearch");
        if (!this.name.equals(name)) {
            this.name = name;
            this.type = type;
            SearchDebouncer.searchDebounce(runnable);
        }
    }

}
