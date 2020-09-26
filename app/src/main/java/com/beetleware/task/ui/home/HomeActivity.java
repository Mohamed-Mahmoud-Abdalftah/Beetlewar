package com.beetleware.task.ui.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.beetleware.task.R;
import com.beetleware.task.ui.home.homeFragment.HomeFragment;
import com.beetleware.task.ui.home.profileFragment.ProfileFragment;
import com.beetleware.task.ui.home.searchFragment.SearchFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class HomeActivity  extends AppCompatActivity {
    private  static  int ID_SEARCH = 1;
    private static  int ID_HOME = 2;
    private static  int ID_Profile = 3;

    private static  String ID_SEARCH_Name = "SEARCH";
    private static  String ID_HOME_Name = "HOME";
    private static  String  ID_Profile_Name = "Profile";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        initViews();
    }
     void initViews(){


         MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);


         bottomNavigation.add(new MeowBottomNavigation.Model(ID_SEARCH, R.drawable.ic_search_deactive));
         bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
         bottomNavigation.add(new MeowBottomNavigation.Model(ID_Profile, R.drawable.ic_user));



         bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
             @Override
             public void onClickItem(MeowBottomNavigation.Model item) {

             }
         });

         bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
             @Override
             public void onShowItem(MeowBottomNavigation.Model item) {

                 String name;
                 switch (item.getId()) {
                     case 1:
                         name = ID_SEARCH_Name;

                         break;
                     case 2:
                         name = ID_HOME_Name;
                         break;
                     case 3:
                         name = ID_Profile_Name;
                         break;
                     default:
                         name = "";
                 }
                 if (name.equals(ID_SEARCH_Name)){
                     replaceFragment(SearchFragment.newInstance(),"SearchFragment");
                 }else if(name.equals(ID_HOME_Name)){

                     replaceFragment(HomeFragment.newInstance(),"HomeFragment");



                 }else if(name.equals(ID_Profile_Name)){

                     replaceFragment(ProfileFragment.newInstance(),"AccountFragment");

                 }
             }
         });

         bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
             @Override
             public void onReselectItem(MeowBottomNavigation.Model item) {

             }
         });


         bottomNavigation.show(ID_HOME,true);

    }

    private void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment,tag);
        fragmentTransaction.commit();
    }

}
