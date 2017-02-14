package com.mostudios.audiolibros.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.toolbox.NetworkImageView;
import com.mostudios.audiolibros.Aplicacion;
import com.mostudios.audiolibros.Lecturas;
import com.mostudios.audiolibros.Libro;
import com.mostudios.audiolibros.LibrosSingleton;
import com.mostudios.audiolibros.R;

import java.io.IOException;

/**
 * Created by marzzelo on 16/1/2017.
 */

public class DetalleFragment extends Fragment implements View.OnTouchListener, MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {
    public static String ARG_ID_LIBRO = "id_libro";
    MediaPlayer mediaPlayer;
    MediaController mediaController;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState) {
        View vista = inflador.inflate(R.layout.fragment_detalle, contenedor, false);
        Bundle args = getArguments();
        if (args != null) {
            String key = args.getString(ARG_ID_LIBRO);
            ponInfoLibro(key, vista);
        } else {
            ponInfoLibro("", vista);
        }
        return vista;
    }

    private void ponInfoLibro(final String key, View vista) {
        LibrosSingleton librosSingleton = LibrosSingleton.getInstance(getContext());
        Libro libro = librosSingleton.getAdaptador().getItemByKey(key);

        Aplicacion aplicacion = (Aplicacion) getActivity().getApplication();

        final Lecturas lectura = aplicacion.getLecturas();

        final ToggleButton esLeido = (ToggleButton) vista.findViewById(R.id.leido);
        esLeido.setChecked(lectura.esLeido(key));
        esLeido.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lectura.libroLeidoPorMi(key, esLeido.isChecked());
                    }
                }
        );

        ((TextView) vista.findViewById(R.id.titulo)).setText(libro.getTitulo());
        ((TextView) vista.findViewById(R.id.autor)).setText(libro.getAutor());
//        ((ImageView) vista.findViewById(R.id.portada))
//                .setImageResource(libro.getRecursoImagen());

        ((NetworkImageView) vista.findViewById(R.id.portada)).setImageUrl(libro.getUrlImagen(), aplicacion.getLectorImagenes());

        vista.setOnTouchListener(this);

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaController = new MediaController(getActivity());
        Uri audio = Uri.parse(libro.getUrlAudio());
        try {
            mediaPlayer.setDataSource(getActivity(), audio);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.e("Audiolibros", "ERROR: No se puede reproducir " + audio, e);
        }
    }

    public void ponInfoLibro(String key) {
        ponInfoLibro(key, getView());
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("Audiolibros", "Entramos en onPrepared de MediaPlayer");
        mediaPlayer.start();
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(getView().findViewById(
                R.id.fragment_detalle));
        mediaController.setEnabled(true);
        mediaController.setPadding(0, 0, 0, 110);
        mediaController.show();
    }

    @Override
    public boolean onTouch(View vista, MotionEvent evento) {
        mediaController.show();
        return false;
    }

    @Override
    public void onStop() {
        mediaController.hide();
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
        } catch (Exception e) {
            Log.d("Audiolibros", "Error en mediaPlayer.stop()");
        }
        super.onStop();
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        try {
            return mediaPlayer.getCurrentPosition();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}