package com.example.viniciuspassari.testefast.Presenter;

public interface Contract<T> {
    void showProgress();

    void hideProgress();

    void onSuccess(T t);

    void onError(String msg);
}
