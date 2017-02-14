package com.mostudios.audiolibros;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;

/**
 * Created by marzzelo on 12/1/2017.
 */

public class Aplicacion extends Application {
    private AdaptadorLibrosFiltro adaptador;
    private static RequestQueue colaPeticiones;
    private static ImageLoader lectorImagenes;
    private FirebaseAuth auth;
    private final static String BOOKS_CHILD = "libros";
    private final static String USERS_CHILD = "usuarios";
    private DatabaseReference usersReference;
    private static Lecturas lecturas;

    public static RequestQueue getColaPeticiones() {
        return colaPeticiones;
    }

    public static void setColaPeticiones(RequestQueue colaPeticiones) {
        Aplicacion.colaPeticiones = colaPeticiones;
    }

    public static ImageLoader getLectorImagenes() {
        return lectorImagenes;
    }

    public static void setLectorImagenes(ImageLoader lectorImagenes) {
        Aplicacion.lectorImagenes = lectorImagenes;
    }

    public static void fillLecturas()
    {
        lecturas = new Lecturas();
    }

    public static Lecturas getLecturas() {
        return lecturas;
    }
    @Override
    public void onCreate() {
        auth = FirebaseAuth.getInstance();

        colaPeticiones = Volley.newRequestQueue(this);
        lectorImagenes = new ImageLoader(colaPeticiones, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        DatabaseReference booksReference = database.getReference().child(BOOKS_CHILD);

        adaptador = new AdaptadorLibrosFiltro(this, booksReference);
        usersReference = database.getReference().child(USERS_CHILD);
    }

    public DatabaseReference getUsersReference() {
        return usersReference;
    }

    public FirebaseAuth getAuth() {
        return auth;
    }


//    public AdaptadorLibrosFiltro getAdaptador() {
//        return adaptador;
//    }
//
//    public Vector<Libro> getVectorLibros() {
//        return vectorLibros;
//    }
}
