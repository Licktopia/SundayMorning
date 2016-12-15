package com.jltechnologies.sundaymorning;

import android.app.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface, GridFragment.OnRecipeSelectedInterface {
    public static final String  LIST_FRAGMENT = "list_fragment";
    public static final String  VIEWPAGER_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);//tyed to value config
        if(!isTablet) {//executes on phone
            ListFragment savedFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);//need to add tag to add/replace calls,
            if (savedFragment == null)
                { //makes sure we're not creating two fragments... ie when we rotate
                ListFragment fragment = new ListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
                }
        }
            else
            {//executes on Tablet
                GridFragment savedFragment = (GridFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);//need to add tag to add/replace calls,
                if(savedFragment == null)
                { //makes sure we're not creating two fragments... ie when we rotate
                    GridFragment fragment = new GridFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                    fragmentTransaction.commit();
                }
            }
    }

    @Override
    public void onListRecipeSelected(int index) {

        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null).commit();//goes back one when back button pressed
        //fragmentTransaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {
        DuelPaneFragment fragment = new DuelPaneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null).commit();//goes back one when back button pressed
        //fragmentTransaction.commit();


    }
}
