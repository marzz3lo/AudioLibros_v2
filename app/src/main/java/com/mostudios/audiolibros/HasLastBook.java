package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 2/2/2017.
 */

public class HasLastBook {
    private final LibroStorage librosStorage;

    public HasLastBook(LibroStorage librosStorage)
    {
        this.librosStorage = librosStorage;
    }

    public boolean execute()
    {
        return librosStorage.hasLastBook();
    }

}
