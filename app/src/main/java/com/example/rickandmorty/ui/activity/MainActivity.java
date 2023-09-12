package com.example.rickandmorty.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.ActivityMainBinding;
import com.example.rickandmorty.ui.activity.vm.MainVM;
import com.example.rickandmorty.ui.fragments.SearchFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        MainVM vm = new ViewModelProvider(this).get(MainVM.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_view, SearchFragment.newInstance())
                    .commit();
        }

    }
}

// TODO проверить код
// переделать entities
// еще раз проверить взамодействие между слоями
// реализовать переключение страниц
// проверить: возможно ли переключение с использованием ViewPager,
// при том, что каждая новая страница загружается динамически
// прописать запрос на получение страницы, возможно перегрузить функцию в networkService
