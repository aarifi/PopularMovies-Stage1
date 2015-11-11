package adonisarifi.com.popularmoviesstage1.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.ui.fragment.DetailsFragment;
import adonisarifi.com.popularmoviesstage1.utils.Constants;

/**
 * Created by AdonisArifi on 6.11.2015 - 2015 . PopularMovies,Stage1
 */
public class DetailsActivity extends AppCompatActivity {
    DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("result", getIntent().getParcelableExtra(Constants.MOVIEMODEL_INTENTEXTRA));
            DetailsFragment detailsFragment = new DetailsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_details_container, detailsFragment)
                    .commit();
        }
    }


}
