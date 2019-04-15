package com.example.viniciuspassari.testefast.Network;

import com.example.viniciuspassari.testefast.Utils.Constants;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET(Constants.URL_GET_GENRES)
    Call<String> getGenres(@Query("api_key") String apiKey);

    @GET(Constants.URL_GET_MOVIES_BY_GENRE)
    Call<String> getMoviesByGenre(@Path("id") int genreId, @Query("api_key") String apiKey);

    @GET(Constants.URL_GET_MOVIE_BY_ID)
    Call<String> getMovieById(@Path("id") int movieId, @Query("api_key") String apiKey);

    class Builder {
        private static HttpLoggingInterceptor getLoggingInterceptor() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return interceptor;
        }

        private static OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
            return new OkHttpClient.Builder()
                    .dispatcher(new Dispatcher(Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS)))
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }

        public static API build(String baseUrl) {
            OkHttpClient client = getOkHttpClient(getLoggingInterceptor());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();

            return retrofit.create(API.class);
        }
    }

}