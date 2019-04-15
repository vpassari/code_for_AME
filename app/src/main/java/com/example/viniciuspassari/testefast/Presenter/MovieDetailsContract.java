package com.example.viniciuspassari.testefast.Presenter;

import com.example.viniciuspassari.testefast.Data.Model.Movie;

public interface MovieDetailsContract extends Contract<Movie> {

    interface Presenter{
        void getMovieById(long id);
    }

}
