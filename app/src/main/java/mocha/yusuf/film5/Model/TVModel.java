package mocha.yusuf.film5.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import mocha.yusuf.film5.Database.TVContract;

import static mocha.yusuf.film5.Database.TVContract.getColumnDouble;
import static mocha.yusuf.film5.Database.TVContract.getColumnInt;
import static mocha.yusuf.film5.Database.TVContract.getColumnString;

public class TVModel implements Parcelable {
    @SerializedName("original_name")
    private String original_name;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("id")
    private String id;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String poster_path;

    public TVModel(String original_name, String name, double popularity, int vote_count, String first_air_date, String backdrop_path, String original_language, String id, double vote_average, String overview, String poster_path) {
        this.original_name = original_name;
        this.name = name;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original_name);
        dest.writeString(this.name);
        dest.writeDouble(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeString(this.first_air_date);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.original_language);
        dest.writeString(this.id);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
    }

    protected TVModel(Parcel in) {
        this.original_name = in.readString();
        this.name = in.readString();
        this.popularity = in.readDouble();
        this.vote_count = in.readInt();
        this.first_air_date = in.readString();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.id = in.readString();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.poster_path = in.readString();
    }

    public static final Parcelable.Creator<TVModel> CREATOR = new Parcelable.Creator<TVModel>() {
        @Override
        public TVModel createFromParcel(Parcel source) {
            return new TVModel(source);
        }

        @Override
        public TVModel[] newArray(int size) {
            return new TVModel[size];
        }
    };

    public TVModel (Cursor cursor) {
        this.id = getColumnString(cursor, TVContract.TVColumns.TV_id);
        this.original_name = getColumnString(cursor, TVContract.TVColumns.TV_original_name);
        this.name = getColumnString(cursor, TVContract.TVColumns.TV_name);
        this.popularity = getColumnInt(cursor, TVContract.TVColumns.TV_popularity);
        this.vote_count = getColumnInt(cursor, TVContract.TVColumns.TV_vote_count);
        this.backdrop_path = getColumnString(cursor, TVContract.TVColumns.TV_first_air_date);
        this.backdrop_path = getColumnString(cursor, TVContract.TVColumns.TV_backdrop_path);
        this.original_language = getColumnString(cursor, TVContract.TVColumns.TV_original_language);
        this.vote_average = getColumnDouble(cursor, TVContract.TVColumns.TV_vote_average);
        this.overview = getColumnString(cursor, TVContract.TVColumns.TV_overview);
        this.poster_path = getColumnString(cursor, TVContract.TVColumns.TV_poster_path);

    }
}
