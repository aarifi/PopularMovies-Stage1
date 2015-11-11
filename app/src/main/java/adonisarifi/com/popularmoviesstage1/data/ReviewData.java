package adonisarifi.com.popularmoviesstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsReview;
import adonisarifi.com.popularmoviesstage1.model.ReviewModel;

/**
 * Created by AdonisArifi on 5.11.2015 - 2015 . PopularMovies,Stage1
 */
public class ReviewData {

    static RuntimeExceptionDao<ReviewModel, Integer> runtimeExceptionDao;
    DatabaseHelper databaseHelper;

    public ReviewData(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getReviewModelsDao();
    }


    public static String registerOrUpdate(ReviewModel reviewModel) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(reviewModel);
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

    public static ListResponsReview getAll(int moviesId) {
        List<ReviewModel> reviewModelList = new ArrayList<>();
        ListResponsReview listResponsReview = new ListResponsReview();
        try {
            List<ReviewModel> list = runtimeExceptionDao.queryForAll();

            for (ReviewModel reviewModel : list) {
                if (reviewModel.getMovieId().equals(String.valueOf(moviesId))) {
                    reviewModelList.add(reviewModel);

                }

            }

            listResponsReview.setResults(reviewModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listResponsReview;
    }

    public static ReviewModel getbyId(int id) {
        return runtimeExceptionDao.queryForId(id);
    }


    public void deleteReview(int movieId) {
        runtimeExceptionDao.deleteById(movieId);
    }
}
