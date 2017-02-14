package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 2/2/2017.
 */

public class GetLastBook {

    private final LibroStorage librosStorage;

    public GetLastBook(LibroStorage libroStorage)
    {
        this.librosStorage = libroStorage;
    }

    public String execute()
    {
        return librosStorage.getLastBook();
    }
}
