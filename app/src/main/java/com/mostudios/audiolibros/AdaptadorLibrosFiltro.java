package com.mostudios.audiolibros;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by marzzelo on 17/1/2017.
 */

public class AdaptadorLibrosFiltro extends AdaptadorLibros implements Observer {
    private int librosUltimoFiltro;
    private Vector<Integer> indiceFiltro; // Índice en vectorSinFiltro de
    // Cada elemento de vectorLibros
    private String busqueda = ""; // Búsqueda sobre autor o título
    private String genero = ""; // Género seleccionado
    private boolean novedad = false; // Si queremos ver solo novedades
    private boolean leido = false; // Si queremos ver solo leidos
    private Lecturas lecturas;

    public AdaptadorLibrosFiltro(Context contexto, DatabaseReference reference) {
        super(contexto, reference);
        recalculaFiltro();
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda.toLowerCase();
        recalculaFiltro();
    }

    public void setGenero(String genero) {
        this.genero = genero;
        recalculaFiltro();
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
        recalculaFiltro();
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
        recalculaFiltro();
    }

    public void recalculaFiltro() {
        indiceFiltro = new Vector<Integer>();
        librosUltimoFiltro = super.getItemCount();

        boolean connected = (FirebaseAuth.getInstance().getCurrentUser() != null);

        if (connected) {
            if (lecturas == null)
                Aplicacion.fillLecturas();

            lecturas = Aplicacion.getLecturas();
        }

        for (int i = 0; i < librosUltimoFiltro; i++) {
            Libro libro = super.getItem(i);

            boolean isReaded = false;

            if (connected)
                isReaded = lecturas.esLeido(super.getItemKey(i));

            if ((libro.getTitulo().toLowerCase().contains(busqueda) || libro.getAutor().toLowerCase().contains(busqueda))
                    && (libro.getGenero().startsWith(genero))
                    && (!novedad || (novedad && libro.getNovedad()))
                    && (!leido || (leido && isReaded))) {
                indiceFiltro.add(i);
            }
        }
    }

    public Libro getItem(int posicion) {
        if (librosUltimoFiltro != super.getItemCount()) {
            recalculaFiltro();
        }
        return super.getItem(indiceFiltro.elementAt(posicion));
    }

    public int getItemCount() {
        if (librosUltimoFiltro != super.getItemCount()) {
            recalculaFiltro();
        }
        return indiceFiltro.size();
    }

    public long getItemId(int posicion) { //Este no cambia
        return indiceFiltro.elementAt(posicion); //Se incluye por claridad
    }

    public Libro getItemById(int id) {
        return super.getItem(id);
    }

    public void borrar(int posicion) {
        DatabaseReference referencia = getRef(indiceFiltro.elementAt(posicion));
        referencia.removeValue();
        recalculaFiltro();
    }

    public void insertar(Libro libro) {
        booksReference.push().setValue(libro);
        recalculaFiltro();
    }

    @Override
    public void update(Observable observable, Object data) {
        setBusqueda((String) data);
        notifyDataSetChanged();
    }

    public String getItemKey(int posicion) {
        if (librosUltimoFiltro != super.getItemCount()) {
            recalculaFiltro();
        }
        int id = indiceFiltro.elementAt(posicion);
        return super.getItemKey(id);
    }
}