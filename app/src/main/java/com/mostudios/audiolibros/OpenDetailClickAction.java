package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 29/1/2017.
 */

public class OpenDetailClickAction implements ClickAction {
    private final MainActivity mainActivity;

    public OpenDetailClickAction(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void execute(String key) {
        mainActivity.mostrarDetalle(key);
    }
}
