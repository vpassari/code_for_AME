package com.example.viniciuspassari.testefast.Presenter;

import com.example.viniciuspassari.testefast.Data.Model.Genre;

import java.util.ArrayList;

public interface GenresContract extends Contract<ArrayList<Genre>> {

    interface Presenter {
        void getGenresList();
    }
}
