package com.example.viniciuspassari.testefast.Data;


import com.example.viniciuspassari.testefast.Data.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieConverter {

    public static final String INDEX_RESULTS = "results";
    public static final String INDEX_ADULT = "adult";
    public static final String INDEX_BACKDROP_PATH = "backdrop_path";
    public static final String INDEX_GENRE_IDS = "genre_ids";
    public static final String INDEX_ID = "id";
    public static final String INDEX_ORIGINAL_LANGUAGE = "original_language";
    public static final String INDEX_ORIGINAL_TITLE = "original_title";
    public static final String INDEX_OVERVIEW = "overview";
    public static final String INDEX_RELEASE_DATE = "release_date";
    public static final String INDEX_POSTER_PATH = "poster_path";
    public static final String INDEX_TITLE = "title";
    public static final String INDEX_VOTE_AVERATE = "vote_average";

    public ArrayList<Movie> getMovies(String json) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        JSONObject jsonObjectMovies = new JSONObject(json);

        JSONArray jsonArrayMovies = jsonObjectMovies.getJSONArray(INDEX_RESULTS);

        for(int i = 0; i < jsonArrayMovies.length(); i++){
            JSONObject jsonObjectMovie = jsonArrayMovies.getJSONObject(i);

            Movie movie = new Movie();

            boolean isAdult = jsonObjectMovie.getBoolean(INDEX_ADULT);
            String backdropPath = jsonObjectMovie.getString(INDEX_BACKDROP_PATH);
            JSONArray jsonGenreIds = jsonObjectMovie.getJSONArray(INDEX_GENRE_IDS);

            List<Integer> genreIds = new ArrayList<>();

            if(jsonGenreIds != null) {
                for (int j = 0; j < jsonGenreIds.length(); j++){
                    genreIds.add(jsonGenreIds.optInt(j));
                }
            }

            long id = jsonObjectMovie.getLong(INDEX_ID);
            String originalLanguage = jsonObjectMovie.getString(INDEX_ORIGINAL_LANGUAGE);
            String originalTitle = jsonObjectMovie.getString(INDEX_ORIGINAL_TITLE);
            String overview = jsonObjectMovie.getString(INDEX_OVERVIEW);
            String releaseDate = jsonObjectMovie.getString(INDEX_RELEASE_DATE);
            String posterPath = jsonObjectMovie.getString(INDEX_POSTER_PATH);
            String title = jsonObjectMovie.getString(INDEX_TITLE);
            double voteAverage = jsonObjectMovie.getDouble(INDEX_VOTE_AVERATE);

            movie.setAdult(isAdult);
            movie.setBackdropPath(backdropPath);
            movie.setGenreIds(genreIds);
            movie.setId(id);
            movie.setOriginalLanguage(originalLanguage);
            movie.setOriginalTitle(originalTitle);
            movie.setOverview(overview);
            movie.setPosterPath(posterPath);
            movie.setReleaseDate(releaseDate);
            movie.setTitle(title);
            movie.setVoteAverage(voteAverage);

            movies.add(movie);
        }

        return movies;
    }

}
