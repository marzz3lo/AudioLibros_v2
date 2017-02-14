package com.mostudios.audiolibros;

/**
 * Created by marzzelo on 31/1/2017.
 */

public class MainPresenter {
    private final View view;
    private final BooksRespository repository;

    public MainPresenter(BooksRespository repository, MainPresenter.View view) {
        this.repository = repository;
        this.view = view;
    }

    public void clickFavoriteButton() {
        if (repository.hasLastBook()) {
            view.mostrarFragmentDetalle(repository.getLastBook());
        } else {
            view.mostrarNoUltimaVisita();
        }
    }

    public void openDetalle(String key) {
        repository.saveLastBook(key);
        view.mostrarFragmentDetalle(key);
    }

    public interface View {
        void mostrarFragmentDetalle(String lastBook);
        void mostrarNoUltimaVisita();
    }
}
