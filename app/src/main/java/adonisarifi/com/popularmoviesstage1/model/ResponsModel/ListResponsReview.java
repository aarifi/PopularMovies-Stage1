package adonisarifi.com.popularmoviesstage1.model.ResponsModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.ReviewModel;

/**
 * Created by AdonisArifi on 5.11.2015 - 2015 . PopularMovies,Stage1
 */
public class ListResponsReview implements Parcelable {

    private List<ReviewModel> results;
    private int id;
    private int page;
    private int total_pages;

    public  ListResponsReview()
    {

    }

    protected ListResponsReview(Parcel in) {
        results = in.createTypedArrayList(ReviewModel.CREATOR);
        id = in.readInt();
        page = in.readInt();
        total_pages = in.readInt();
        total_results = in.readInt();
    }

    public static final Creator<ListResponsReview> CREATOR = new Creator<ListResponsReview>() {
        @Override
        public ListResponsReview createFromParcel(Parcel in) {
            return new ListResponsReview(in);
        }

        @Override
        public ListResponsReview[] newArray(int size) {
            return new ListResponsReview[size];
        }
    };

    public List<ReviewModel> getResults() {
        return results;
    }

    public void setResults(List<ReviewModel> results) {
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    private int total_results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
        dest.writeInt(id);
        dest.writeInt(page);
        dest.writeInt(total_pages);
        dest.writeInt(total_results);
    }
}
