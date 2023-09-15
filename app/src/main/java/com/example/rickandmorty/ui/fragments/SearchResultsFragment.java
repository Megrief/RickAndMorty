package com.example.rickandmorty.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.databinding.FragmentSearchResultsBinding;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.ui.fragments.adapters.rv.DataDiffAdapter;
import com.example.rickandmorty.ui.fragments.ui_state.Empty;
import com.example.rickandmorty.ui.fragments.ui_state.Loading;
import com.example.rickandmorty.ui.fragments.ui_state.ShowData;
import com.example.rickandmorty.ui.fragments.view_models.SearchFragmentVM;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchResultsFragment extends Fragment {

    public SearchResultsFragment() {
        // Required empty public constructor
    }


    SearchFragmentVM vm;
    FragmentSearchResultsBinding binding;
    DataDiffAdapter adapter;

    public static SearchResultsFragment newInstance() {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false);

        vm = new ViewModelProvider(requireParentFragment()).get(SearchFragmentVM.class);
        vm.getScreenState().observe(getViewLifecycleOwner(), screenState -> {
            if (screenState instanceof Loading) {
                onLoading();
            } else if (screenState instanceof Empty) {
                onEmpty();
            } else {
                onResult((ShowData) screenState);
            }
        });

        adapter = new DataDiffAdapter();
        binding.dataListRv.setAdapter(adapter);

        return binding.getRoot();
    }

    private void onLoading() {
        binding.dataListRv.setVisibility(View.GONE);
        binding.loadingPb.setVisibility(View.VISIBLE);
    }

    private void onEmpty() {
        binding.loadingPb.setVisibility(View.GONE);
        binding.dataListRv.setVisibility(View.GONE);
        Toast.makeText(requireContext(), "No List", Toast.LENGTH_SHORT).show();
    }

    private void onResult(ShowData screenState) {
        binding.loadingPb.setVisibility(View.GONE);
        binding.dataListRv.setVisibility(View.VISIBLE);
        adapter.setItems(screenState.getData());
    }
}