package mocha.yusuf.film5.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListMovieModel implements Parcelable {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private ArrayList<MovieModel> results;

    public ListMovieModel(int page, int total_results, int total_pages, ArrayList<MovieModel> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieModel> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.total_results);
        dest.writeInt(this.total_pages);
        dest.writeTypedList(this.results);
    }

    protected ListMovieModel(Parcel in) {
        this.page = in.readInt();
        this.total_results = in.readInt();
        this.total_pages = in.readInt();
        this.results = in.createTypedArrayList(MovieModel.CREATOR);
    }

    public static final Parcelable.Creator<ListMovieModel> CREATOR = new Parcelable.Creator<ListMovieModel>() {
        @Override
        public ListMovieModel createFromParcel(Parcel source) {
            return new ListMovieModel(source);
        }

        @Override
        public ListMovieModel[] newArray(int size) {
            return new ListMovieModel[size];
        }
    };
}
