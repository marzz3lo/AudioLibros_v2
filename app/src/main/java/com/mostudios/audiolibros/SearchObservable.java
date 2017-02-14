package com.mostudios.audiolibros;


import android.support.v7.widget.SearchView;

import java.util.Observable;

/**
 * Created by marzzelo on 29/1/2017.
 */

public class SearchObservable extends Observable implements SearchView.OnQueryTextListener
{
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        setChanged();
        notifyObservers(query);
        return true;
    }
}
