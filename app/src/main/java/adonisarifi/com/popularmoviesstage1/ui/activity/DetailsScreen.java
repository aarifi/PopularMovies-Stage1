package adonisarifi.com.popularmoviesstage1.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsScreen extends AppCompatActivity {
    Bundle params;
    private MoviesModel movie;

    @Bind(R.id.detail_title)
    TextView detail_title;
    @Bind(R.id.item_movie_cover)
    ImageView item_movie_cover;
    @Bind(R.id.overview)
    TextView overview;
    @Bind(R.id.txtrelasedate)
    TextView txtrelasedate;
    @Bind(R.id.voteAvarage)
    TextView voteAvarage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        params = getIntent().getExtras();
        ButterKnife.bind(this);
        detail_title.setText(params.getString("Title"));
        overview.setText(params.getString("Overview"));
        txtrelasedate.setText(params.getString("ReleaseDate"));
        voteAvarage.setText(params.getString("VoteAvarage"));
        Picasso.with(this).load(params.getString("CachedPosterPath")).into(item_movie_cover);
        Log.d("DetailsScreen", params.getString("CachedPosterPath"));

    }


}
