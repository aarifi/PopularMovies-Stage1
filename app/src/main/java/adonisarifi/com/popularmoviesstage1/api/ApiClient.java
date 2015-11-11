package adonisarifi.com.popularmoviesstage1.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsReview;
import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsVideo;
import adonisarifi.com.popularmoviesstage1.utils.Constants;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by AdonisArifi on 8.9.2015 - 2015 .
 */
public class ApiClient implements RequestInterceptor, Client {

    private static final String LOG = ApiClient.class.getSimpleName();
    private static MoviesAPI mInterfaceMethod;
    private static ApiClient instance;
    Client wrappedClient;
    ConnectivityManager cm;

    public static ApiClient getInstance(Context coteContext) {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public MoviesAPI getmInterfaceMethod() {
        try {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
            if (mInterfaceMethod == null) {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setRequestInterceptor(this)
                        .setEndpoint(Constants.ENDPOINT)
                        .setErrorHandler(ErrorHandler.DEFAULT)
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setClient(new OkClient(okHttpClient))
                        .build();

                mInterfaceMethod = restAdapter.create(MoviesAPI.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mInterfaceMethod;
    }


    @Override
    public Response execute(Request request) throws IOException {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            Log.d(LOG, request.toString());
        }
        return wrappedClient.execute(request);
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

    public ListResponsReview getReview(String id) {
        ListResponsReview listResponses = null;
        try {
            listResponses = getmInterfaceMethod().getReview(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listResponses;
    }

    public ListResponsVideo getVideoModel(String moviesId) {
        ListResponsVideo listResponsVideo = getmInterfaceMethod().getVideo(moviesId);
        return listResponsVideo;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(Constants.KEY_PARAM, Constants.THEMOVIEDB_API_KEY);
    }


}
