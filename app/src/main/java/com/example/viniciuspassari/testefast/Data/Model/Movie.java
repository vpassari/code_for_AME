package com.example.viniciuspassari.testefast.Data.Model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {

    public static final String BUNDLE = "movie";
    public static final String BUNDLE_ID = "movie_id";

    private boolean adult;
    private String backdropPath;
    private ArrayList<Genre> genres;
    private List<Integer> genreIds;
    private String homepage;
    private long id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private double voteAverage;
    private int voteCount;

    private Drawable backdropDrawable;

    public Movie(){}

    public Movie(String title){
        this.title = title;
    }

    protected Movie(Parcel in) {
        adult = in.readByte() != 0;
        backdropPath = in.readString();
        genres = in.createTypedArrayList(Genre.CREATOR);
        genreIds = new ArrayList<>();
        homepage = in.readString();
        id = in.readLong();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        releaseDate = in.readString();
        title = in.readString();
        voteAverage = in.readDouble();
        voteCount = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Drawable getBackdropDrawable() {
        return backdropDrawable;
    }

    public void setBackdropDrawable(Drawable backdropDrawable) {
        this.backdropDrawable = backdropDrawable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(backdropPath);
        parcel.writeTypedList(genres);
        parcel.writeList(genreIds);
        parcel.writeString(homepage);
        parcel.writeLong(id);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeDouble(popularity);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeString(title);
        parcel.writeDouble(voteAverage);
        parcel.writeInt(voteCount);
    }
}