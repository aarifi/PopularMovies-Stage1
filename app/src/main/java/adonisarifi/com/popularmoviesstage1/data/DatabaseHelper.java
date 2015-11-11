package adonisarifi.com.popularmoviesstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.model.ReviewModel;
import adonisarifi.com.popularmoviesstage1.model.VideoModel;
import adonisarifi.com.popularmoviesstage1.utils.Constants;

/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = Constants.DATABASE_NAME_ORMLITE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MoviesModel.class);
            TableUtils.createTable(connectionSource, ReviewModel.class);
            TableUtils.createTable(connectionSource, VideoModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, MoviesModel.class, true);
            TableUtils.dropTable(connectionSource, ReviewModel.class, true);
            TableUtils.dropTable(connectionSource, VideoModel.class, true);


            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }


    private RuntimeExceptionDao<MoviesModel, Integer> moviesModelsDao = null;

    public RuntimeExceptionDao<MoviesModel, Integer> getMoviesModels() {
        if (moviesModelsDao == null) {
            moviesModelsDao = getRuntimeExceptionDao(MoviesModel.class);
        }
        return moviesModelsDao;
    }

    private RuntimeExceptionDao<ReviewModel, Integer> reviewModelsDao = null;

    public RuntimeExceptionDao<ReviewModel, Integer> getReviewModelsDao() {
        if (reviewModelsDao == null) {
            reviewModelsDao = getRuntimeExceptionDao(ReviewModel.class);
        }
        return reviewModelsDao;
    }


    private RuntimeExceptionDao<VideoModel, Integer> videoModels = null;

    public RuntimeExceptionDao<VideoModel, Integer> getVideoModelsDao() {
        if (videoModels == null) {
            videoModels = getRuntimeExceptionDao(VideoModel.class);
        }
        return videoModels;
    }

}
