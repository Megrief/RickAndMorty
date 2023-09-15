package com.example.rickandmorty.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.FragmentSearchBinding;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.ui.fragments.view_models.SearchFragmentVM;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {

    private TypeOfData type = null;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.search_results, SearchResultsFragment.newInstance())
                    .commit();
        }

        setBinding();
    }

    private void setBinding() {

        setEditText();

        setSpinner();

        binding.clearIv.setOnClickListener(v -> {
            binding.searchEt.setText("");
            binding.searchEt.setFocusedByDefault(false);
            vm.clearSearch();
        });

    }

    private void setEditText() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !String.valueOf(s).equals("")) {
                    vm.search(String.valueOf(s), type);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        binding.searchEt.addTextChangedListener(textWatcher);
    }

    private void setSpinner() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.searchEt.setEnabled(true);
                Object item = parent.getItemAtPosition(position);
                if (item instanceof TypeOfData) {
                    type = (TypeOfData) item;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.searchEt.setEnabled(false);
            }
        };
        binding.searchTypeSpinner.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.single_spinner_item, TypeOfData.values()));
        binding.searchTypeSpinner.setOnItemSelectedListener(listener);

    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
}