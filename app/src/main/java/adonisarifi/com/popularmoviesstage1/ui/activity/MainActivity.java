package adonisarifi.com.popularmoviesstage1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.adapters.MoviesMyAdapter;
import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.ui.fragment.DetailsFragment;
import adonisarifi.com.popularmoviesstage1.ui.fragment.MainFragment;
import adonisarifi.com.popularmoviesstage1.utils.Constants;

public class MainActivity extends AppCompatActivity implements MoviesMyAdapter.Callbacks {

    MainFragment fragment;
    DetailsFragment detailsFragment;

    private boolean onLargScreen;
    private static final String DETAIL_FRAGMENT_TAG = "detail_fragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new MainFragment();
        if (findViewById(R.id.fragment_details_container) != null) {
            onLargScreen = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_details_container, new DetailsFragment()).commit();
            }
        } else {
            onLargScreen = false;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(MoviesModel movie) {

        if (onLargScreen) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.MOVIEMODEL_INTENTEXTRA, movie);

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details_container, detailsFragment, DETAIL_FRAGMENT_TAG)
                    .commit();

        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(Constants.MOVIEMODEL_INTENTEXTRA, movie);
            startActivity(intent);
        }

    }
}
