package com.beetleware.task.ui.home.homeFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.beetleware.task.ui.home.HomeActivity;
import com.beetleware.task.ui.login.Login;
import com.beetleware.task.ui.login.LoginViewModel;
import com.beetleware.task.ui.login.modelLogin;
import com.beetleware.task.utils.AppUtils;
import com.beetleware.task.utils.Dialogues;
import com.beetleware.task.utils.UserInfo;

public class HomeFragment  extends Fragment {
    private homeViewModel homeViewModel;
View view;
TextView  sold_item ,product_item;
UserInfo userInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=   inflater.inflate(R.layout.home_fragment, container, false)  ;
        initViews();
        obtainViewModel();
        subscribeToUI();
        userInfo=new UserInfo(getContext());
        homeViewModel.isDataValid(userInfo.getAccessToken()+"");
        homeViewModel.isDataValidProducts(userInfo.getAccessToken()+"");

        return view;

    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void subscribeToUI() {
        homeViewModel.getUser().observe(this, new Observer<model>() {
            @Override
            public void onChanged(@Nullable model model) {

                sold_item.setText(model.getData()+"");

            }
        });
        homeViewModel.getUserProducts().observe(this, new Observer<model>() {
            @Override
            public void onChanged(@Nullable model model) {

                product_item.setText(model.getData()+"");

            }
        });
        homeViewModel.onFailureData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {


                Dialogues.CreatePoPup(getContext(), "Try Again", "Try Again");
            }
        });

        homeViewModel.UserDateError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                Dialogues.CreatePoPup(getContext(), "Try Again", "Try Again");
            }
        });
    }


    private void obtainViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(homeViewModel.class);
        homeViewModel.setWebService(ApiFactory.createApi());
    }

    private void initViews() {
        sold_item=view.findViewById(R.id.sold_item);
        product_item=view.findViewById(R.id.product_item);

    }
}
