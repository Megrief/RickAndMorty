package com.example.rickandmorty.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.databinding.ActivityMainBinding;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.ui.main.adapter.DataAdapter;
import com.example.rickandmorty.ui.main.vm.MainVM;
import com.example.rickandmorty.ui.main.vm.state.ScreenState;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        MainVM vm = new ViewModelProvider(this).get(MainVM.class);
        configureVM(vm);

        adapter = new DataAdapter(url -> {
//            Intent intent = new Intent(getApplicationContext(), ShowDataActivity.class);
//            intent.putExtra(URL, url);
//            startActivity(intent);
        });
        binding.dataListRv.setAdapter(adapter);


        binding.clearIv.setOnClickListener(v -> {
            adapter.clear();
        });
        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s != "") {
                    vm.search(String.valueOf(s), TypeOfData.CHARACTER);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    private void configureVM(MainVM vm) {
        vm.getScreenState().observe(this, screenState -> {
            if (screenState instanceof ScreenState.Loading) {
                onLoading();
            } else if (screenState instanceof ScreenState.Empty) {
                onEmpty();
            } else {
                onResult((ScreenState.ShowData) screenState);
            }
        });
    }

    private void onLoading() {
        binding.dataListRv.setVisibility(View.GONE);
        binding.loadingPb.setVisibility(View.VISIBLE);
    }

    private void onEmpty() {
        binding.loadingPb.setVisibility(View.GONE);
        binding.dataListRv.setVisibility(View.GONE);
        Toast.makeText(this, "No List", Toast.LENGTH_SHORT).show();
    }

    private void onResult(ScreenState.ShowData screenState) {
        binding.loadingPb.setVisibility(View.GONE);
        binding.dataListRv.setVisibility(View.VISIBLE);
        adapter.setDataList(screenState.getData());
    }

    private void configureSpinner() {
        binding.searchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}