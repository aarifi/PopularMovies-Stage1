package adonisarifi.com.popularmoviesstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsVideo;
import adonisarifi.com.popularmoviesstage1.model.VideoModel;

/**
 * Created by AdonisArifi on 5.11.2015 - 2015 . PopularMovies,Stage1
 */
public class VideoData {
    static RuntimeExceptionDao<VideoModel, Integer> runtimeExceptionDao;
    DatabaseHelper databaseHelper;

    public VideoData(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        runtimeExceptionDao = databaseHelper.getVideoModelsDao();
    }


    public String registerOrUpdate(VideoModel videoModel) {
        try {
            Dao.CreateOrUpdateStatus status = runtimeExceptionDao.createOrUpdate(videoModel);
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

    public static ListResponsVideo getAll(int moviesId) {
        List<VideoModel> videoModelList = new ArrayList<>();
        ListResponsVideo listResponsVideo = new ListResponsVideo();
        try {
            List<VideoModel> list = runtimeExceptionDao.queryForAll();

            for (VideoModel videoModel : list) {
                if (videoModel.getMovieId().equals(String.valueOf(moviesId))) {
                    videoModelList.add(videoModel);
                }

            }
            listResponsVideo.setResults(videoModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listResponsVideo;
    }

    public static VideoModel getbyId(int id) {
        return runtimeExceptionDao.queryForId(id);
    }

    public boolean ifMoviesAddFavorites(int moviesId) {

        boolean statusMovies = false;
        List<VideoModel> list = runtimeExceptionDao.queryForAll();

        for (VideoModel videoModel : list) {
            if (videoModel.getId().equals(moviesId)) {
                statusMovies = true;
            }
        }

        return statusMovies;
    }

    public void deletMovies(int movieId) {
        runtimeExceptionDao.deleteById(movieId);
    }
}

