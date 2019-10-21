package mocha.yusuf.film5.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListTVModel implements Parcelable {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private ArrayList<TVModel> results;

    public ListTVModel(int page, int total_results, int total_pages, ArrayList<TVModel> results) {
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

    public ArrayList<TVModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<TVModel> results) {
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

    protected ListTVModel(Parcel in) {
        this.page = in.readInt();
        this.total_results = in.readInt();
        this.total_pages = in.readInt();
        this.results = in.createTypedArrayList(TVModel.CREATOR);
    }

    public static final Parcelable.Creator<ListTVModel> CREATOR = new Parcelable.Creator<ListTVModel>() {
        @Override
        public ListTVModel createFromParcel(Parcel source) {
            return new ListTVModel(source);
        }

        @Override
        public ListTVModel[] newArray(int size) {
            return new ListTVModel[size];
        }
    };
}
