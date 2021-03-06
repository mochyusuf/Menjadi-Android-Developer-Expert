package mocha.yusuf.favorit.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import mocha.yusuf.favorit.Database.MovieContract;

import static mocha.yusuf.favorit.Database.MovieContract.getColumnDouble;
import static mocha.yusuf.favorit.Database.MovieContract.getColumnInt;
import static mocha.yusuf.favorit.Database.MovieContract.getColumnString;

public class MovieModel implements Parcelable {
    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    public MovieModel(double popularity, int vote_count, String poster_path, int id, String backdrop_path, String original_language, String original_title, String title, double vote_average, String overview, String release_date) {
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.poster_path = poster_path;
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeString(this.poster_path);
        dest.writeInt(this.id);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.title);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
    }

    public MovieModel(Parcel in) {
        this.popularity = in.readDouble();
        this.vote_count = in.readInt();
        this.poster_path = in.readString();
        this.id = in.readInt();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.title = in.readString();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public MovieModel (Cursor cursor) {
        this.id = Integer.valueOf(getColumnString(cursor, MovieContract.MovieColumns.MOVIE_id));
        this.title = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_title);
        this.popularity = getColumnDouble(cursor, MovieContract.MovieColumns.MOVIE_popularity);
        this.vote_count = getColumnInt(cursor, MovieContract.MovieColumns.MOVIE_vote_count);
        this.poster_path = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_poster_path);
        this.backdrop_path = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_backdrop_path);
        this.original_language = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_original_language);
        this.original_title = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_original_title);
        this.vote_average = getColumnDouble(cursor, MovieContract.MovieColumns.MOVIE_vote_average);
        this.overview = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_overview);
        this.release_date = getColumnString(cursor, MovieContract.MovieColumns.MOVIE_release_date);

    }
}