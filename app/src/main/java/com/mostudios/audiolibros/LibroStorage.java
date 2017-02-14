package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 30/1/2017.
 */

public interface LibroStorage {
    boolean hasLastBook();
    String getLastBook();
    void saveLastBook(String key);
}
