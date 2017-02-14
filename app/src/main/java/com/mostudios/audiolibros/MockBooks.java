package com.mostudios.audiolibros;

import java.util.Vector;

/**
 * Created by marzzelo on 30/1/2017.
 */

public class MockBooks {

    public static Vector<Libro> ejemploLibros() {
        final String SERVIDOR =
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/";
        Vector<Libro> libros = new Vector<Libro>();
        libros.add(new Libro.LibroBuilder().withTitulo("Kappa").
                withAutor("Akutagawa").
                withUrlImagen(SERVIDOR+"kappa.jpg").
                withAudio(SERVIDOR + "kappa.mp3").
                withGenero(Libro.G_S_XIX).
                withNuevo(false).
                build());
        libros.add(new Libro.LibroBuilder().withTitulo("Avecilla").
                withAutor("Alas Clarín, Leopoldo").
                withUrlImagen(SERVIDOR +"avecilla.jpg").
                withAudio(SERVIDOR + "avecilla.mp3").
                withGenero(Libro.G_S_XIX).
                withNuevo(true).
                build());

        libros.add(new Libro.LibroBuilder().withTitulo("Divina Comedia").
                withAutor("Dante").
                withUrlImagen(SERVIDOR+"divina_comedia.jpg").
                withAudio(SERVIDOR + "divina_comedia.mp3").
                withGenero(Libro.G_EPICO).
                withNuevo(true).
                build());

        libros.add(new Libro.LibroBuilder().withTitulo("Viejo Pancho, El").
                withAutor("Alonso y Trelles, José").
                withUrlImagen(SERVIDOR+"viejo_pancho.jpg").
                withAudio(SERVIDOR + "viejo_pancho.mp3").
                withGenero(Libro.G_S_XIX).
                withNuevo(true).
                build());

        libros.add(new Libro.LibroBuilder().withTitulo("Canción de Rolando").
                withAutor("Anónimo").
                withUrlImagen(SERVIDOR+"cancion_rolando.jpg").
                withAudio(SERVIDOR + "cancion_rolando.mp3").
                withGenero(Libro.G_EPICO).
                withNuevo(false).
                build());

        libros.add(new Libro.LibroBuilder().withTitulo("Matrimonio de sabuesos").
                withAutor("Agata Christie").
                withUrlImagen(SERVIDOR+"matrim_sabuesos.jpg").
                withAudio(SERVIDOR + "matrim_sabuesos.mp3").
                withGenero(Libro.G_SUSPENSE).
                withNuevo(false).
                build());

        libros.add(new Libro.LibroBuilder().withTitulo("La iliada").
                withAutor("Homero").
                withUrlImagen(SERVIDOR+"la_iliada.jpg").
                withAudio(SERVIDOR + "la_iliada.mp3").
                withGenero(Libro.G_EPICO).
                withNuevo(true).
                build());

        return libros;
    }

}
