package adonisarifi.com.popularmoviesstage1.model.rest;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
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
}
