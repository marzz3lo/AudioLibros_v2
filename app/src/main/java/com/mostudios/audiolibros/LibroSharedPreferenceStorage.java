package com.mostudios.audiolibros;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by marzzelo on 29/1/2017.
 */

public class LibroSharedPreferenceStorage implements LibroStorage {
    public static final String PREF_AUDIOLIBROS = "com.mostudios.audiolibros_internal";
    public static final String KEY_ULTIMO_LIBRO = "ultimo";
    private static LibroSharedPreferenceStorage instance;

    public static LibroStorage getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new LibroSharedPreferenceStorage(context);
        }

        return instance;
    }

    private final Context context;

    private LibroSharedPreferenceStorage(Context context) {
        this.context = context;
    }

    public boolean hasLastBook() {
        return getPreference().contains(KEY_ULTIMO_LIBRO);
    }

    private SharedPreferences getPreference() {
        return context.getSharedPreferences(PREF_AUDIOLIBROS, MODE_PRIVATE);
    }

    public String getLastBook() {
        return getPreference().getString(KEY_ULTIMO_LIBRO, "");
    }

    public void saveLastBook(String key)
    {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_AUDIOLIBROS, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_ULTIMO_LIBRO, key);
        editor.commit();
    }

}
