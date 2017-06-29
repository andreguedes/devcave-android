package br.com.andreguedes.filmes_mvp.contracts;

/**
 * Created by andreguedes on 25/06/17.
 */

public interface APICallback<T> {

    void onSuccess(T model);
    void onFailed();

}