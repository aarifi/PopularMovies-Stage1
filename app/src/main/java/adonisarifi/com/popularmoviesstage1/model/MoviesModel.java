package adonisarifi.com.popularmoviesstage1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;


public class MoviesModel implements Parcelable {


    @DatabaseField
    private Boolean adult;
    @DatabaseField
    private String backdrop_path;
    @DatabaseField
    private String backdrop_pathLocal;
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String original_language;
    @DatabaseField
    private String original_title;
    @DatabaseField
    private String overview;
    @DatabaseField
    private String release_date;
    @DatabaseField
    private String poster_path;
    @DatabaseField
    private String poster_pathLocal;
    @DatabaseField
    private double popularity;
    @DatabaseField
    private String title;
    @DatabaseField
    private Boolean video;
    @DatabaseField
    private double vote_average;
    @DatabaseField
    private int vote_count;
    @DatabaseField
    private String review;
    @DatabaseField
    private String fullVideoUrl;

    public MoviesModel() {

    }

    protected MoviesModel(Parcel in) {
        backdrop_path = in.readString();
        backdrop_pathLocal = in.readString();
        id = in.readInt();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        poster_pathLocal = in.readString();
        popularity = in.readDouble();
        title = in.readString();
        vote_average = in.readDouble();
        vote_count = in.readInt();
        review = in.readString();
        fullVideoUrl = in.readString();
    }

    public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getBackdrop_pathLocal() {
        return backdrop_pathLocal;
    }

    public void setBackdrop_pathLocal(String backdrop_pathLocal) {
        this.backdrop_pathLocal = backdrop_pathLocal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getPoster_pathLocal() {
        return poster_pathLocal;
    }

    public void setPoster_pathLocal(String poster_pathLocal) {
        this.poster_pathLocal = poster_pathLocal;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFullVideoUrl() {
        return fullVideoUrl;
    }

    public void setFullVideoUrl(String fullVideoUrl) {
        this.fullVideoUrl = fullVideoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdrop_path);
        dest.writeString(backdrop_pathLocal);
        dest.writeInt(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(poster_pathLocal);
        dest.writeDouble(popularity);
        dest.writeString(title);
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
        dest.writeString(review);
        dest.writeString(fullVideoUrl);
    }
}
