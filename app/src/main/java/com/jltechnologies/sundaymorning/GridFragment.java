package com.jltechnologies.sundaymorning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jeff on 12/15/16.
 */

public class GridFragment extends Fragment{

    public interface OnRecipeSelectedInterface{
        void onGridRecipeSelected(int index);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();

        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);//fixes illegal state error

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        GridAdapter gridAdapter = new GridAdapter(listener);
        recyclerView.setAdapter(gridAdapter);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();//gets dimensions of phone
        float dpWidth = displayMetrics.widthPixels/displayMetrics.density;
        int numColumns = (int) (dpWidth/200);//sets width of columns
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), numColumns);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}

