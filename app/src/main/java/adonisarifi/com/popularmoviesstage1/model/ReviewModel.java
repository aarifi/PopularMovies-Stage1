package adonisarifi.com.popularmoviesstage1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by AdonisArifi on 4.11.2015 - 2015 . PopularMovies,Stage1
 */
public class ReviewModel implements Parcelable {

    @DatabaseField(id=true)
    private String id;
    @DatabaseField
    private String movieId;
    @DatabaseField
    private String author;
    @DatabaseField
    private String content;
    @DatabaseField
    private String url;

    public ReviewModel() {

    }

    protected ReviewModel(Parcel in) {
        id = in.readString();
        movieId = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    public static final Creator<ReviewModel> CREATOR = new Creator<ReviewModel>() {
        @Override
        public ReviewModel createFromParcel(Parcel in) {
            return new ReviewModel(in);
        }

        @Override
        public ReviewModel[] newArray(int size) {
            return new ReviewModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(movieId);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
    }
}
