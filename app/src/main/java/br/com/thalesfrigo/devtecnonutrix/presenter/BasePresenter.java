package br.com.thalesfrigo.devtecnonutrix.presenter;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();
}
