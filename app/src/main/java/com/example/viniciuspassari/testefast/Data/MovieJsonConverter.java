package com.example.viniciuspassari.testefast.Data;

import com.example.viniciuspassari.testefast.Data.GenreConverter;
import com.example.viniciuspassari.testefast.Data.Model.Genre;
import com.example.viniciuspassari.testefast.Data.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieJsonConverter {

    public static final String INDEX_ADULT = "adult";
    public static final String INDEX_BACKDROP_PATH = "backdrop_path";
    public static final String INDEX_GENRES = "genres";
    public static final String INDEX_ID = "id";
    public static final String INDEX_ORIGINAL_LANGUAGE = "original_language";
    public static final String INDEX_ORIGINAL_TITLE = "original_title";
    public static final String INDEX_OVERVIEW = "overview";
    public static final String INDEX_RELEASE_DATE = "release_date";
    public static final String INDEX_POSTER_PATH = "poster_path";
    public static final String INDEX_TITLE = "title";
    public static final String INDEX_VOTE_COUNT = "vote_count";
    public static final String INDEX_VOTE_AVERATE = "vote_average";

    public Movie getMovie(String json) throws JSONException {
        JSONObject jsonObjectMovie = new JSONObject(json);

        Movie movie = new Movie();

        boolean isAdult = jsonObjectMovie.getBoolean(INDEX_ADULT);
        String backdropPath = jsonObjectMovie.getString(INDEX_BACKDROP_PATH);
        JSONArray jsonGenres = jsonObjectMovie.getJSONArray(INDEX_GENRES);

        ArrayList<Genre> genres = new ArrayList<>();

        if(jsonGenres != null) {
            for (int j = 0; j < jsonGenres.length(); j++){
                Genre genre = new Genre();

                JSONObject jsonGenre = jsonGenres.getJSONObject(j);
                int id = jsonGenre.getInt(GenreConverter.INDEX_ID);
                String name = jsonGenre.getString(GenreConverter.INDEX_NAME);

                genre.setId(id);
                genre.setName(name);

                genres.add(genre);
            }
        }

        long id = jsonObjectMovie.getLong(INDEX_ID);
        String originalLanguage = jsonObjectMovie.getString(INDEX_ORIGINAL_LANGUAGE);
        String originalTitle = jsonObjectMovie.getString(INDEX_ORIGINAL_TITLE);
        String overview = jsonObjectMovie.getString(INDEX_OVERVIEW);
        String releaseDate = jsonObjectMovie.getString(INDEX_RELEASE_DATE);
        String posterPath = jsonObjectMovie.getString(INDEX_POSTER_PATH);
        String title = jsonObjectMovie.getString(INDEX_TITLE);
        int voteCount = jsonObjectMovie.getInt(INDEX_VOTE_COUNT);
        double voteAverage = jsonObjectMovie.getDouble(INDEX_VOTE_AVERATE);

        movie.setAdult(isAdult);
        movie.setBackdropPath(backdropPath);
        movie.setGenres(genres);
        movie.setId(id);
        movie.setOriginalLanguage(originalLanguage);
        movie.setOriginalTitle(originalTitle);
        movie.setOverview(overview);
        movie.setPosterPath(posterPath);
        movie.setReleaseDate(releaseDate);
        movie.setTitle(title);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);

        return movie;
    }

}
