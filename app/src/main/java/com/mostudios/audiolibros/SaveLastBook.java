package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 2/2/2017.
 */

public class SaveLastBook {
    private final LibroStorage libroStorage;

    public SaveLastBook(LibroStorage librosStorage)
    {
        this.libroStorage = librosStorage;
    }

    public void execute(String key)
    {
        libroStorage.saveLastBook(key);
    }

}
