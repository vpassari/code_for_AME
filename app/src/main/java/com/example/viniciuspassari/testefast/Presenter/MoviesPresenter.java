package com.example.viniciuspassari.testefast.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.viniciuspassari.testefast.Data.MovieConverter;
import com.example.viniciuspassari.testefast.Network.API;
import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.Utils.Constants;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesPresenter implements MoviesContract.Presenter {

    public static final String TAG = MoviesPresenter.class.getName();

    private Context context;
    private MoviesContract moviesView;
    private API api;

    public MoviesPresenter(Context context, MoviesContract moviesContract){
        this.context = context;
        this.moviesView = moviesContract;
        api = API.Builder.build(Constants.BASE_URL);
    }

    @Override
    public void getMoviesList(int genreId) {
        moviesView.showProgress();

        Call<String> moviesListCall = api.getMoviesByGenre(genreId, Constants.THE_MOVIE_DB_KEY);

        moviesListCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                moviesView.hideProgress();

                if(response.isSuccessful()){
                    if(response.code() == Constants.STATUS_CODE_SUCCESS){
                        Log.d(TAG, response.body());
                        MovieConverter converter = new MovieConverter();
                        try {
                            moviesView.onSuccess(converter.getMovies(response.body()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            moviesView.onError(context.getString(R.string.generic_error));
                        }
                    }
                    else{
                        moviesView.onError(context.getString(R.string.error_load_genres));
                    }
                }
                else{
                    Log.d(TAG, "Error: " + response.errorBody()+"");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                moviesView.hideProgress();
            }
        });
    }
}