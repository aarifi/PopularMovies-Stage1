package adonisarifi.com.popularmoviesstage1.data;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.model.ReviewModel;
import adonisarifi.com.popularmoviesstage1.model.VideoModel;

/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DatabaseConfig extends OrmLiteConfigUtil {


    public static final Class<?>[] classes = new Class[]
            {
                    MoviesModel.class,
                    ReviewModel.class,
                    VideoModel.class
            };


    public static void main(String[] args) {
        try {
            writeConfigFile("ormlite_config.txt", classes);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
