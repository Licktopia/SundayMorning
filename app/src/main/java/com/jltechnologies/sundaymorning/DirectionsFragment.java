package com.jltechnologies.sundaymorning;

/**
 * Created by jeff on 12/15/16.
 */

public class DirectionsFragment extends checkBoxesFragment{
    @Override
    public String[] getContents(int index) {
        return Recipes.directions[index].split("`");
    }
}
