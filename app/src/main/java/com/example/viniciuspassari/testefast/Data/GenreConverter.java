package com.example.viniciuspassari.testefast.Data;

import com.example.viniciuspassari.testefast.Data.Model.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenreConverter {

    public static final String INDEX_GENRES = "genres";
    public static final String INDEX_ID = "id";
    public static final String INDEX_NAME = "name";

    public ArrayList<Genre> getGenres(String json) throws JSONException {
        ArrayList<Genre> genres = new ArrayList<>();

        JSONObject jsonObjGenres = new JSONObject(json);

        JSONArray jsonArrayGenres = jsonObjGenres.getJSONArray(INDEX_GENRES);

        for(int i = 0; i < jsonArrayGenres.length(); i++){
            Genre genre = new Genre();

            JSONObject jsonObjGenre = jsonArrayGenres.getJSONObject(i);

            int id = jsonObjGenre.getInt(INDEX_ID);
            String name = jsonObjGenre.getString(INDEX_NAME);

            genre.setId(id);
            genre.setName(name);

            genres.add(genre);
        }

        return genres;
    }

}
