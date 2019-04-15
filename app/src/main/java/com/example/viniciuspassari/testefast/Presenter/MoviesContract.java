package com.example.viniciuspassari.testefast.Presenter;

import com.example.viniciuspassari.testefast.Data.Model.Movie;

import java.util.ArrayList;

public interface MoviesContract extends Contract<ArrayList<Movie>> {

    interface Presenter{
        void getMoviesList(int genreId);
    }

}