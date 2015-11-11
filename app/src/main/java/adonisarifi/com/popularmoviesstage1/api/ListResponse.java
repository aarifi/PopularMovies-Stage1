package adonisarifi.com.popularmoviesstage1.api;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.model.MoviesModel;

/**

 * Created by AdonisArifi on 10.9.2015 - 2015  - 2015 .
 */
public class ListResponse {
    List<MoviesModel> results;
    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MoviesModel> getResults() {
        return results;
    }

    public void setResults(List<MoviesModel> results) {
        this.results = results;
    }
}
