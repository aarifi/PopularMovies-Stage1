package adonisarifi.com.popularmoviesstage1.api;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsReview;
import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsVideo;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by AdonisArifi on 7.9.2015 - 2015 .
 */
public interface MoviesAPI {

    @GET("/movie/popular")
    ListResponse getPopular();

    @GET("/movie/top_rated")
    ListResponse getTopRated();

    @GET("/movie/{id}")
    MoviesModel getMovieDetails(@Path("id") String id);

    @GET("/movie/{id}/reviews")
    public ListResponsReview getReview(
            @Path("id") String id
    );


    @GET("/movie/{id}/videos")
    public ListResponsVideo getVideo(
            @Path("id") String id
    );
}
