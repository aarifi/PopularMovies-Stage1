package adonisarifi.com.popularmoviesstage1.model.ResponsModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.VideoModel;

/**
 * Created by AdonisArifi on 5.11.2015 - 2015 . PopularMovies,Stage1
 */
public class ListResponsVideo implements Parcelable {

    public int id;
    public List<VideoModel> results;

    protected ListResponsVideo(Parcel in) {
        id = in.readInt();
        results = in.createTypedArrayList(VideoModel.CREATOR);
    }

    public ListResponsVideo() {

    }

    public static final Creator<ListResponsVideo> CREATOR = new Creator<ListResponsVideo>() {
        @Override
        public ListResponsVideo createFromParcel(Parcel in) {
            return new ListResponsVideo(in);
        }

        @Override
        public ListResponsVideo[] newArray(int size) {
            return new ListResponsVideo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoModel> getResults() {
        return results;
    }

    public void setResults(List<VideoModel> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeTypedList(results);
    }
}
