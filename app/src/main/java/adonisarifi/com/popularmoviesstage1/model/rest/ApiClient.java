package adonisarifi.com.popularmoviesstage1.model.rest;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.utils.Constants;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by AdonisArifi on 8.9.2015 - 2015 .
 */
public class ApiClient implements RequestInterceptor {

    private static MoviesAPI mInterfaceMethod;
    private static ApiClient instance;

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public MoviesAPI getmInterfaceMethod() {
        if (mInterfaceMethod == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setRequestInterceptor(this)
                    .setEndpoint(Constants.ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            mInterfaceMethod = restAdapter.create(MoviesAPI.class);
        }
        return mInterfaceMethod;
    }

    public List<MoviesModel> getPopular() {
        ListResponse result = getmInterfaceMethod().getPopular();
        return result.getResults();
    }

    public List<MoviesModel> getTopRated() {
        ListResponse result = getmInterfaceMethod().getTopRated();
        return result.getResults();
    }

    public MoviesModel getMovieDetails(String id) {
        MoviesModel m = getmInterfaceMethod().getMovieDetails(id);
        return m;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(Constants.KEY_PARAM, Constants.THEMOVIEDB_API_KEY);
    }


}
