package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 31/1/2017.
 */

public class MainController {

    LibroStorage libroStorage;

    public MainController(LibroStorage libroStorage) {
        this.libroStorage = libroStorage;
    }

    public void saveLastBook(String key)
    {
        libroStorage.saveLastBook(key);
    }

    public boolean hasLastBook()
    {
        return libroStorage.hasLastBook();
    }

    public String getLastBook()
    {
        return libroStorage.getLastBook();
    }
}
