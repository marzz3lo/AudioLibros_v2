package com.mostudios.audiolibros;

import android.content.Context;
import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;

/**
 * Created by marzzelo on 30/1/2017.
 */
public class LibrosSingleton {
    private DatabaseReference booksReference;
    private AdaptadorLibrosFiltro adaptador;
    private final static String BOOKS_CHILD = "libros";

    private static LibrosSingleton ourInstance;

    public static LibrosSingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LibrosSingleton(context);
        }
        return ourInstance;
    }

    private LibrosSingleton(Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //database.setPersistenceEnabled(true);

        DatabaseReference booksReference = database.getReference().child(BOOKS_CHILD);

        adaptador = new AdaptadorLibrosFiltro(context, booksReference);
    }

    public AdaptadorLibrosFiltro getAdaptador() {
        return adaptador;
    }

}
