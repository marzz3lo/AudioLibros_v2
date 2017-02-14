package com.mostudios.audiolibros;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.mostudios.audiolibros.fragments.PreferenciasFragment;

/**
 * Created by marzzelo on 18/1/2017.
 */

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenciasFragment()).commit();
    }
}
