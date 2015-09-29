package adonisarifi.com.popularmoviesstage1.ui.fragment;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.items.MoviesMyAdapter;
import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.model.rest.ApiClient;
import adonisarifi.com.popularmoviesstage1.utils.PopularMovieSharedPref;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<MoviesModel>> {

    private static final String STATE_SORT = "STATE_SORT";
    String LOG_DEBUG = MainFragment.class.getSimpleName();
    RecyclerView recyclerView;
    public Callbacks callback;
    public String myCurrentOrder;
    public View rootView;
    PopularMovieSharedPref popularMovieSharedPref;
    List<MoviesModel> moviesModelList;
    MoviesModel[] dataForInstanceState;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(STATE_SORT, dataForInstanceState);

    }

    public MainFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Inflate the my custom layout
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recView_movies);
        popularMovieSharedPref = new PopularMovieSharedPref(getActivity().getApplicationContext());
        String order = popularMovieSharedPref.getStatusOrderMovies();
        if (savedInstanceState != null) {
            dataForInstanceState = (MoviesModel[]) savedInstanceState.getParcelableArray(STATE_SORT);
            moviesModelList = Arrays.asList(dataForInstanceState);
            reloadData();
            myCurrentOrder = order;
        } else {

            if (myCurrentOrder == null) {
                getLoaderManager().initLoader(0, null, MainFragment.this).forceLoad();
                myCurrentOrder = order;
            }


            Log.d(LOG_DEBUG, "onStart");

        }
        return rootView;

    }

    public void reloadData() {
        // Create adapter passing in the sample user data
        MoviesMyAdapter adapter = new MoviesMyAdapter(moviesModelList, getActivity());

        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);

        // Set layout manager to position the items
        //        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public Loader<List<MoviesModel>> onCreateLoader(int id, Bundle args) {
        Loader<List<MoviesModel>> loader = new MovieLoader(getActivity());
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<MoviesModel>> loader, final List<MoviesModel> data) {
        moviesModelList = data;
        this.dataForInstanceState = new MoviesModel[data.size()];
        dataForInstanceState = data.toArray(dataForInstanceState);
        reloadData();
    }

    @Override
    public void onLoaderReset(Loader<List<MoviesModel>> loader) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface Callbacks {
        void onItemSelected(MoviesModel movie);
    }

    public static class MovieLoader extends AsyncTaskLoader<List<MoviesModel>> {

        public MovieLoader(Context context) {
            super(context);
        }

        @Override
        public List<MoviesModel> loadInBackground() {
            try {

                PopularMovieSharedPref popularMovieSharedPref = new PopularMovieSharedPref(getContext());

                String order = popularMovieSharedPref.getStatusOrderMovies();
                if (order.equals(this.getContext().getString(R.string.pref_rated))) {
                    return ApiClient.getInstance().getTopRated();
                } else {
                    return ApiClient.getInstance().getPopular();
                }
            } catch (Exception ex) {

                return new ArrayList<>();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_DEBUG, "onREsume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_DEBUG, "onPause");

    }

    @Override
    public void onStart() {
        super.onStart();
        popularMovieSharedPref = new PopularMovieSharedPref(getActivity().getApplicationContext());
        String order = popularMovieSharedPref.getStatusOrderMovies();
        // reload list if settings are changed
        if (!myCurrentOrder.equals(order)) {
            myCurrentOrder = order;
            loadData();

        }

    }

    public void loadData() {
        getLoaderManager().initLoader(0, null, MainFragment.this).forceLoad();

    }

    public void setCallback(Callbacks callback) {
        this.callback = callback;
    }
}
