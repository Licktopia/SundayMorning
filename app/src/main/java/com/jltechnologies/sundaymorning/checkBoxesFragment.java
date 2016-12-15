package com.jltechnologies.sundaymorning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by jeff on 12/14/16.
 */

public abstract class checkBoxesFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    private CheckBox[] mCheckboxes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);//gets ingredient position from View PagerFragment

        View view = inflater.inflate(R.layout.fragment_checkboxes,container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkboxesLayout);
        String[] contents = getContents(index);



        mCheckboxes = new CheckBox[contents.length];
        boolean[] checkedBoxes = new boolean[mCheckboxes.length];
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }

        setUpCheckBoxes(contents, linearLayout, checkedBoxes);


        return view;
    }

    public abstract String[] getContents(int index);


    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkedBoxes){
        int i = 0;
        for(String content : contents){
            mCheckboxes[i] = new CheckBox(getActivity());
            mCheckboxes[i].setPadding(8, 16, 8, 16);
            mCheckboxes[i].setTextSize(20f);
            mCheckboxes[i].setText(content);
            container.addView(mCheckboxes[i]);
            if(checkedBoxes[i]){
                mCheckboxes[i].toggle();
            }
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckboxes.length];
        int i = 0;
        for(CheckBox checkBox : mCheckboxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
