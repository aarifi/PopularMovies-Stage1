package adonisarifi.com.popularmoviesstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;

/**
 * Created by AdonisArifi on 3.11.2015 - 2015 . PopularMovies,Stage1
 */
public class MovieData {
    static RuntimeExceptionDao<MoviesModel, Integer> runtimeExceptionDao;

    DatabaseHelper dbHelper;

    public MovieData(Context context) {
        dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = dbHelper.getMoviesModels();
    }

    public String registerOrUpdate(MoviesModel moviesModel) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(moviesModel);
            if (status.isCreated()) {
                return "isCreated";
            }
            if (status.isUpdated()) {
                return "isUpdated";
            }
            return "";

        } catch (SQLiteException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static List<MoviesModel> getAll() {
        List<MoviesModel> list = null;
        QueryBuilder<MoviesModel, Integer> qb = runtimeExceptionDao.queryBuilder();
        // qb.orderBy("OrderNumber", true);
        try {
            list = qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static MoviesModel getbyId(int id) {
        return runtimeExceptionDao.queryForId(id);
    }

    public boolean ifMoviesAddFavorites(int moviesId) {

        boolean statusMovies = false;
        List<MoviesModel> list = runtimeExceptionDao.queryForAll();

        for (MoviesModel moviesModel : list) {
            if (moviesModel.getId() == moviesId) {
                statusMovies = true;
            }
        }

        return statusMovies;
    }

    public void deletMovies(int movieId) {
        runtimeExceptionDao.deleteById(movieId);
    }
}
