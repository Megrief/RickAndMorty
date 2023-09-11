package com.example.rickandmorty.ui.fragments.adapters.vp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.ui.fragments.SearchResultsFragment;

public class VpAdapter extends FragmentStateAdapter {

    int numOfPages = 1;
    Page nextPage;
    public VpAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle
    ) {
        super(fragmentManager, lifecycle);
    }

    public void setNumOfPages(int value) {
        numOfPages = value;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = SearchResultsFragment.newInstance();
        return fragment;
    }

    @Override
    public int getItemCount() {
        return numOfPages;
    }

}
