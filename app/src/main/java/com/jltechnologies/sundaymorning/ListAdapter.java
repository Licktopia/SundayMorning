package com.jltechnologies.sundaymorning;

/**
 * Created by jeff on 12/14/16.
 */

public class ListAdapter extends RecyclerAdapter{
    private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        mListener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        mListener.onListRecipeSelected(index);
    }

}
