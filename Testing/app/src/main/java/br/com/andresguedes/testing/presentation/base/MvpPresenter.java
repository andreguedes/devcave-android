package br.com.andresguedes.testing.presentation.base;

/**
 * Created by andreguedes on 13/09/17.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);
    void detachView();

}