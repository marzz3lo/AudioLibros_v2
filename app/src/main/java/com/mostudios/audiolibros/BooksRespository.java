package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 2/2/2017.
 */

public class BooksRespository {
    private final LibroStorage librosStorage;

    public BooksRespository(LibroStorage librosStorge)
    {
        this.librosStorage = librosStorge;
    }

    public String getLastBook()
    {
        return librosStorage.getLastBook();
    }

    public void saveLastBook(String key)
    {
    librosStorage.saveLastBook(key);
    }

    public boolean hasLastBook()
    {
     return librosStorage.hasLastBook();
    }

}


