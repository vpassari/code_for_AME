package com.example.viniciuspassari.testefast.Presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.viniciuspassari.testefast.Data.Model.Movie;
import com.example.viniciuspassari.testefast.Data.MovieJsonConverter;
import com.example.viniciuspassari.testefast.Network.API;
import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.Utils.Constants;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    public static final String TAG = MovieDetailsPresenter.class.getName();

    private Context context;
    private MovieDetailsContract movieDetailsView;
    private API api;

    public MovieDetailsPresenter(Context context, MovieDetailsContract movieDetailsView){
        this.context = context;
        this.movieDetailsView = movieDetailsView;
        api = API.Builder.build(Constants.BASE_URL);
    }

    @Override
    public void getMovieById(long id) {
        movieDetailsView.showProgress();

        Call<String> getMovieCall = api.getMovieById((int)id, Constants.THE_MOVIE_DB_KEY);
        getMovieCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    if(response.code() == Constants.STATUS_CODE_SUCCESS){
                        Log.d(TAG, response.body());
                        MovieJsonConverter converter = new MovieJsonConverter();
                        try {
                            final Movie movie = converter.getMovie(response.body());

                            if(movie.getBackdropPath() != null){
                                Glide.with(context)
                                        .load(Constants.BASE_URL_IMAGE + "/w500" + movie.getBackdropPath())
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                movieDetailsView.hideProgress();
                                                movie.setBackdropDrawable(resource);

                                                movieDetailsView.onSuccess(movie);
                                            }
                                        });
                            }
                            else{
                                movieDetailsView.hideProgress();
                                movieDetailsView.onSuccess(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            movieDetailsView.onError(context.getString(R.string.generic_error));
                        }
                    }
                    else{
                        movieDetailsView.onError(context.getString(R.string.error_load_movie));
                    }
                }
                else{
                    Log.d(TAG, "Error: " + response.errorBody()+"");
                    movieDetailsView.onError(context.getString(R.string.generic_error));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                movieDetailsView.hideProgress();
                Log.d(TAG, t.getMessage());
                movieDetailsView.onError(context.getString(R.string.generic_error));
            }
        });
    }
}