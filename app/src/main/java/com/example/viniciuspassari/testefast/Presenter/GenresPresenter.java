package com.example.viniciuspassari.testefast.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.viniciuspassari.testefast.Data.GenreConverter;
import com.example.viniciuspassari.testefast.Network.API;
import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.Utils.Constants;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresPresenter implements GenresContract.Presenter{

    public static final String TAG = GenresPresenter.class.getName();

    private Context context;
    private GenresContract genresListView;
    private API api;

    public GenresPresenter(Context context, GenresContract genresListView){
        this.context = context;
        this.genresListView = genresListView;
        this.api = API.Builder.build(Constants.BASE_URL);
    }

    @Override
    public void getGenresList() {
        genresListView.showProgress();

        Call<String> genresListCall = api.getGenres(Constants.THE_MOVIE_DB_KEY);
        genresListCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                genresListView.hideProgress();

                if(response.isSuccessful()){
                    if(response.code() == Constants.STATUS_CODE_SUCCESS){
                        Log.d(TAG, response.body());
                        GenreConverter converter = new GenreConverter();
                        try {
                            genresListView.onSuccess(converter.getGenres(response.body()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            genresListView.onError(context.getString(R.string.generic_error));
                        }
                    }
                    else{
                        genresListView.onError(context.getString(R.string.error_load_genres));
                    }
                }
                else{
                    Log.d(TAG, "Error: " + response.errorBody()+"");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                genresListView.hideProgress();
                Log.e(TAG, t.getMessage());
                genresListView.onError(context.getString(R.string.generic_error));
            }
        });
    }
}