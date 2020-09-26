package com.beetleware.task.ui.home.searchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.beetleware.task.R;
import com.beetleware.task.data.network.ApiFactory;
import com.beetleware.task.ui.home.homeFragment.homeViewModel;
import com.beetleware.task.ui.home.homeFragment.model;
import com.beetleware.task.utils.Dialogues;

public class SearchFragment extends Fragment {
View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=   inflater.inflate(R.layout.search_fragment, container, false)  ;

        return view;

    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
