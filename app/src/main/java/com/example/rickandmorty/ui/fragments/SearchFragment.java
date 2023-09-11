package com.example.rickandmorty.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.FragmentSearchBinding;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.ui.fragments.adapters.vp.VpAdapter;
import com.example.rickandmorty.ui.fragments.view_models.SearchFragmentVM;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {

    private SearchFragmentVM vm;
    public SearchFragment() {
        // Required empty public constructor
    }

    FragmentSearchBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = new ViewModelProvider(this).get(SearchFragmentVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        if (savedInstanceState == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.search_results, SearchResultsFragment.newInstance())
                    .commit();
        }
        setBinding();
        return binding.getRoot();
    }

    private void setBinding() {
//        FragmentStateAdapter adapterVp = new VpAdapter(getParentFragmentManager(), getLifecycle());
//        binding.pagesVP.setAdapter(adapterVp);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !String.valueOf(s).equals("")) {
                    vm.search(String.valueOf(s), TypeOfData.CHARACTER);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        binding.searchEt.addTextChangedListener(textWatcher);

        setSpinner();

        binding.clearIv.setOnClickListener(v -> {
//            binding.pagesVP.setVisibility(View.GONE);
            binding.searchEt.setText("");
            binding.searchEt.setFocusedByDefault(false);
            vm.clearSearch();
        });

    }

    private void setSpinner() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.searchEt.setEnabled(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.searchEt.setEnabled(false);
            }
        };

        binding.searchTypeSpinner.setOnItemSelectedListener(listener);

    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
}