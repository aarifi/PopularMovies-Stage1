package adonisarifi.com.popularmoviesstage1.utils;

/**
 * Created by AdonisArifi on 1.9.2015 - 2015  - 2015 .
 */
public final class Constants {

    //To fetch popular movies, you will use the API from themoviedb.org, this is a API KEY .
    public static final String THEMOVIEDB_API_KEY = "ae49e8f4655ac62bbe09db8687109572";

    public static final String ENDPOINT = "http://api.themoviedb.org/3";

    public static final String KEY_PARAM = "api_key";

    public static final String STATIC_IMAGE_URL = "http://image.tmdb.org/t/p/";
    public static final String STATIC_YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    public static final String LIST_IMAGE_SIZE = "w185";
    public static final String DETAILS_IMAGE_SIZE = "w780";


    //Declare SHARED PREFERENCE Key
    public static final String STRING_ORDER_MOVIES = SupportMethod.getPackageNameOfAplication() + ".STRING_ORDER_MOVIES";

    public static final String MOVIEMODEL_INTENTEXTRA = SupportMethod.getPackageNameOfAplication() + ".MOVIEMODEL_INTENTEXTRA";
    public static final String DATABASE_NAME_ORMLITE = "popularmovie.db";
    ;
}
