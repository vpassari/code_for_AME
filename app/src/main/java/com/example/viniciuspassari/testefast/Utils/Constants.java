package com.example.viniciuspassari.testefast.Utils;

public class Constants {

    public static final String THE_MOVIE_DB_KEY = "37421a200ad9a22e7db56ba8dfd0b51a";

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/";
    public static final String URL_GET_GENRES = "genre/movie/list";
    public static final String URL_GET_MOVIES_BY_GENRE = "genre/{id}/movies";
    public static final String URL_GET_MOVIE_BY_ID = "movie/{id}";

    public static final int TIMEOUT = 120; //Seconds
    public static final int NUMBER_OF_THREADS = 20;

    public static final int STATUS_CODE_SUCCESS = 200;
    public static final int STATUS_CODE_CREATED = 201;
    public static final int STATUS_CODE_SUCCESS_NON_AUTHORITATIVE = 203;
    public static final int STATUS_CODE_SUCCESS_NO_BODY = 204;
    public static final int STATUS_CODE_ERROR = 500;
    public static final int STATUS_CODE_NOT_FOUND = 404;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_AVAILABLE = 503;

}