package adonisarifi.com.popularmoviesstage1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by AdonisArifi on 3.9.2015 - 2015 .
 */
public class SupportMethod {

    private Context myContext;


    public SupportMethod(Context context) {
        myContext = context;
    }

    public boolean isNetworkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static String getPackageNameOfAplication() {
        return "adonisarifi.com.popularmoviesstage1";
    }
}
