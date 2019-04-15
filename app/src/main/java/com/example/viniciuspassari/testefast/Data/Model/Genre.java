package com.example.viniciuspassari.testefast.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Genre implements Parcelable {

    public static final String BUNDLE_ID = "genre_id";

    private int id;
    private String name;

    public Genre(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Genre(){}

    protected Genre(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}