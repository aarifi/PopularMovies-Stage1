package adonisarifi.com.popularmoviesstage1.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import adonisarifi.com.popularmoviesstage1.R;

/**
 * Created by AdonisArifi on 27.9.2015 - 2015 .
 */
public class PopularMovieSharedPref {
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesDefault;
    private Context myContext;

    public PopularMovieSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SupportMethod.getPackageNameOfAplication(), Context.MODE_PRIVATE);
        sharedPreferencesDefault = PreferenceManager.getDefaultSharedPreferences(context);
        myContext = context;
    }

    public void saveStatusOrderMovies(String s) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.STRING_ORDER_MOVIES, s);
        editor.commit();
    }

    public String getStatusOrderMovies() {
        return sharedPreferencesDefault.getString(myContext.getString(R.string.pref_sort_key), "top_rated");
    }


}
