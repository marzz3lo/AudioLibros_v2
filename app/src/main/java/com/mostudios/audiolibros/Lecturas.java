package com.mostudios.audiolibros;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by marzzelo on 7/2/2017.
 */

public class Lecturas implements ChildEventListener {
    private String UIDActual;
    private DatabaseReference referenciaMisLecturas;
    private ArrayList<String> idLibros;

    public Lecturas() {
        UIDActual = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        referenciaMisLecturas = database.getReference().child("lecturas").child(UIDActual);
        idLibros = new ArrayList<String>();
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        idLibros.add(dataSnapshot.getKey());
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        idLibros.remove(dataSnapshot.getKey());
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void activaEscuchadorMisLecturas() {
        referenciaMisLecturas.addChildEventListener(this);
    }

    public void desactivaEscuchadorMisLecturas() {
        referenciaMisLecturas.removeEventListener(this);
    }

    public boolean esLeido(String key) {
        return idLibros.contains(key);
    }

    public void libroLeidoPorMi(String key, boolean leido) {
        if (leido)
            referenciaMisLecturas.child(key).setValue(true);
        else {
            referenciaMisLecturas.child(key).removeValue();
            idLibros.remove(key);
        }
    }
}
