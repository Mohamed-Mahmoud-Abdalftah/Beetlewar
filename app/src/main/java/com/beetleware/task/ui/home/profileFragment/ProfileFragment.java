package com.beetleware.task.ui.home.profileFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.beetleware.task.ui.login.Login;
import com.beetleware.task.utils.Dialogues;
import com.beetleware.task.utils.UserInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProfileFragment extends Fragment {
    profileViewModel profileViewModel;
    TextView name;
    ImageView avatar;
    View view;
    UserInfo userInfo;
    LinearLayout logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=   inflater.inflate(R.layout.profile_fragment, container, false)  ;
        initViews();
        obtainViewModel();
        subscribeToUI();
        userInfo=new UserInfo(getContext());
        profileViewModel.isDataValid(userInfo.getAccessToken()+"");

        return   view;
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @SuppressLint("FragmentLiveDataObserve")
    private void subscribeToUI() {
        profileViewModel.getUser().observe(this, new Observer<modelProfile>() {
            @Override
            public void onChanged(@Nullable modelProfile model) {
                name.setText(model.getData().getName());

                Glide.with(getContext()).load(model.getData().getAvatar())
                        .thumbnail(0.5f)
                        .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                        .into(avatar);


            }
        });

        profileViewModel.onFailureData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {


                Dialogues.CreatePoPup(getContext(), "Try Again", "Try Again");
            }
        });

        profileViewModel.UserDateError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                Dialogues.CreatePoPup(getContext(), "Try Again", "Try Again");
            }
        });
    }


    private void obtainViewModel() {
        profileViewModel = ViewModelProviders.of(this).get(profileViewModel.class);
        profileViewModel.setWebService(ApiFactory.createApi());
    }

    private void initViews() {
        name=view.findViewById(R.id.name);
        avatar=view.findViewById(R.id.avatar);
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfo.clearUserInfo();
                userInfo.remove();
                Intent intent=new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }
}
