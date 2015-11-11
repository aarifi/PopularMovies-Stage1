package adonisarifi.com.popularmoviesstage1.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.adapters.VideoAdapter;
import adonisarifi.com.popularmoviesstage1.api.ApiClient;
import adonisarifi.com.popularmoviesstage1.data.MovieData;
import adonisarifi.com.popularmoviesstage1.data.ReviewData;
import adonisarifi.com.popularmoviesstage1.data.VideoData;
import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsReview;
import adonisarifi.com.popularmoviesstage1.model.ResponsModel.ListResponsVideo;
import adonisarifi.com.popularmoviesstage1.model.ReviewModel;
import adonisarifi.com.popularmoviesstage1.model.VideoModel;
import adonisarifi.com.popularmoviesstage1.utils.Constants;
import adonisarifi.com.popularmoviesstage1.utils.PopularMovieSharedPref;
import adonisarifi.com.popularmoviesstage1.utils.SupportMethod;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsFragment extends Fragment {
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
    @Bind(R.id.image_backdrop_path)
    ImageView image_backdrop_path;

    /*   @Bind(R.id.recyclerView_review)
       RecyclerView recyclerView_review;*/
    @Bind(R.id.recyclerView_video)
    RecyclerView recyclerView_video;
    public static String movieId;

    MoviesModel moviesModel;
    private static List<ReviewModel> reviewModelList;
    private static List<VideoModel> videoModelList;
    @Bind(R.id.btn_favorite)
    Button btn_favorite;

    public Context _conContext;
    @Bind(R.id.linearLayout_videos)
    LinearLayout linearLayout_videos;

    @Bind(R.id.linearLayout_review)
    LinearLayout linearLayout_review;

    @Bind(R.id.linearLayout_reviews_item)
    LinearLayout linearLayout_reviews_item;

    private MenuItem menuShareItem;

  /*  @Bind(R.id.linearLayout_trailers_item)
    LinearLayout linearLayout_trailers_item;*/

    //Data class
    MovieData movieData;
    ReviewData reviewData;
    VideoData videoData;


    public DetailsFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View rootView = null;
        if (SupportMethod.isTablet(getActivity())) {
            if (bundle != null) {
                rootView = inflater.inflate(R.layout.activity_item_detail, container, false);
                _conContext = getActivity().getApplicationContext();
                ButterKnife.bind(this, rootView);
                movieData = new MovieData(_conContext);
                reviewData = new ReviewData(_conContext);
                videoData = new VideoData(_conContext);


            }
        } else {
            rootView = inflater.inflate(R.layout.activity_item_detail, container, false);
            _conContext = getActivity().getApplicationContext();
            ButterKnife.bind(this, rootView);
            movieData = new MovieData(_conContext);
            reviewData = new ReviewData(_conContext);
            videoData = new VideoData(_conContext);


        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        moviesModel = (MoviesModel) getActivity().getIntent().getParcelableExtra(Constants.MOVIEMODEL_INTENTEXTRA);
        if (bundle != null) {
            moviesModel = getArguments().getParcelable(Constants.MOVIEMODEL_INTENTEXTRA);

        }
        if (moviesModel != null) {
            movieId = String.valueOf(moviesModel.getId());

            detail_title.setText(moviesModel.getTitle());
            overview.setText(moviesModel.getOverview());
            txtrelasedate.setText(moviesModel.getRelease_date());
            voteAvarage.setText(String.valueOf(moviesModel.getVote_average()) + "/10");

            Glide.with(this).load(moviesModel.getPoster_pathLocal())
                    .into(item_movie_cover);
            Glide.with(this).load(moviesModel.getBackdrop_pathLocal()).into(image_backdrop_path);
            getData();
            Log.d("DetailsScreen", moviesModel.getPoster_pathLocal());
            Log.d("DetailsScreen", moviesModel.getBackdrop_pathLocal());
        }

    }

    public void getData() {
        //check status of button
        checkIfFavorites();
        //get review
        getLoaderManager().initLoader(123, null, loaderCallbacksReview).forceLoad();
        //get video's
        getLoaderManager().initLoader(124, null, loaderCallbacksVideo).forceLoad();

    }

    @OnClick(R.id.btn_favorite)
    public void setOnClick() {

        if (videoModelList != null) {

            if (movieData.ifMoviesAddFavorites(Integer.parseInt(movieId))) {
                btn_favorite.setText(getString(R.string.marksAsFavorite));
                movieData.deletMovies(Integer.parseInt(movieId));
                Snackbar.make(getView(), getString(R.string.favorite_remove_message) + " " + moviesModel.getTitle(),
                        Snackbar.LENGTH_LONG).show();

            } else {
                btn_favorite.setText(getString(R.string.removeFromFavorite));
                movieData.registerOrUpdate(moviesModel);
                if (reviewModelList != null) {
                    int reviewModelIndx = 0;

                    for (ReviewModel reviewModel : reviewModelList) {
                        reviewModel.setMovieId(movieId);
                        reviewData.registerOrUpdate(reviewModel);
                        reviewModelIndx++;

                    }
                }
                if (videoModelList != null) {
                    int videoModelIndx = 0;
                    for (VideoModel videoModel : videoModelList) {
                        videoModel.setMovieId(movieId);
                        videoData.registerOrUpdate(videoModel);
                        videoModelIndx++;
                    }
                }

                Snackbar.make(getView(), getString(R.string.favorite_added_message) + " " + moviesModel.getTitle(),
                        Snackbar.LENGTH_LONG).show();
            }

        }

    }

    public void checkIfFavorites() {

        if (movieData.ifMoviesAddFavorites(Integer.parseInt(movieId))) {
            btn_favorite.setText(getString(R.string.removeFromFavorite));
        } else {
            btn_favorite.setText(getString(R.string.marksAsFavorite));
        }
    }

    //region REVIEW
    public static class AsyncTaskReview extends AsyncTaskLoader<ListResponsReview> {
        public AsyncTaskReview(Context context) {
            super(context);
        }

        @Override
        public ListResponsReview loadInBackground() {
            ListResponsReview listResponsReview = null;
            PopularMovieSharedPref popularMovieSharedPref = new PopularMovieSharedPref(getContext());
            String order = popularMovieSharedPref.getStatusOrderMovies();
            if (order.equals(this.getContext().getString(R.string.favorites))) {
                listResponsReview = ReviewData.getAll(Integer.parseInt(movieId));
            } else {
                listResponsReview = ApiClient.getInstance(getContext()).getReview(String.valueOf(movieId));
            }

            return listResponsReview;
        }
    }

    private LoaderManager.LoaderCallbacks<ListResponsReview> loaderCallbacksReview = new LoaderManager.LoaderCallbacks<ListResponsReview>() {


        @Override
        public android.support.v4.content.Loader<ListResponsReview> onCreateLoader(int id, Bundle args) {
            Loader<ListResponsReview> listLoader = new AsyncTaskReview(getActivity());
            return listLoader;
        }

        @Override
        public void onLoadFinished(android.support.v4.content.Loader<ListResponsReview> loader, ListResponsReview data) {

            if (data != null) {
                reviewModelList = data.getResults();
                List<ReviewModel> reviewModel = data.getResults();
                for (ReviewModel rModel : reviewModel) {
                    LayoutInflater layoutInflater;
                    layoutInflater = (LayoutInflater) getActivity().getApplicationContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View view = layoutInflater.inflate(R.layout.review_item, null);

                    TextView textView_video_name = (TextView) view.findViewById(R.id.txt_item_review_author_title);

                    TextView txt_item_review_desc = (TextView) view.findViewById(R.id.txt_item_review_desc);

                    ImageView imageView = (ImageView) view.findViewById(R.id.item_review_photo);

                    textView_video_name.setText(rModel.getAuthor());
                    txt_item_review_desc.setText(rModel.getContent());
                    Glide.with(getActivity()).load(R.mipmap.ic_account_circle_black_24dp).into(imageView);

                    linearLayout_reviews_item.addView(view);
                }
              /*//  addReviewInAdapter();
                if (reviewModelList.size() < 4 & reviewModelList.size() > 0) {
                    int height = (int) pxFromDp(getActivity(), 100) * 4;
                    linearLayout_review.setMinimumHeight(height);
                } else if (reviewModelList.size() > 4) {
                    int height = (int) pxFromDp(getActivity(), 70) * reviewModelList.size();
                    linearLayout_review.setMinimumHeight(height);
                }*/

            }

        }

        @Override
        public void onLoaderReset(android.support.v4.content.Loader<ListResponsReview> loader) {

        }
    };

    public void addReviewInAdapter() {
       /* ReviewAdapter reviewAdapter = new ReviewAdapter(reviewModelList, getActivity());
        recyclerView_review.setAdapter(reviewAdapter);

        recyclerView_review.setLayoutManager(new LinearLayoutManager(getActivity()));

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        recyclerView_review.setLayoutManager(gridLayoutManager);

        reviewAdapter.notifyDataSetChanged();*/

    }

    //endregion

    //region VIDEO
    public static class AsyncTaskVideo extends AsyncTaskLoader<ListResponsVideo> {
        public AsyncTaskVideo(Context context) {
            super(context);
        }

        @Override
        public ListResponsVideo loadInBackground() {

            ListResponsVideo listResponsVideo = null;
            PopularMovieSharedPref popularMovieSharedPref = new PopularMovieSharedPref(getContext());
            String order = popularMovieSharedPref.getStatusOrderMovies();
            if (order.equals(this.getContext().getString(R.string.favorites))) {
                listResponsVideo = VideoData.getAll(Integer.parseInt(movieId));
            } else {
                listResponsVideo = ApiClient.getInstance(getContext()).getVideoModel(movieId);

            }
            return listResponsVideo;
        }
    }

    private LoaderManager.LoaderCallbacks<ListResponsVideo> loaderCallbacksVideo = new LoaderManager.LoaderCallbacks<ListResponsVideo>() {


        @Override
        public android.support.v4.content.Loader<ListResponsVideo> onCreateLoader(int id, Bundle args) {
            Loader<ListResponsVideo> listLoader = new AsyncTaskVideo(getActivity());
            return listLoader;
        }

        @Override
        public void onLoadFinished(android.support.v4.content.Loader<ListResponsVideo> loader, ListResponsVideo data) {
            if (data != null) {
                videoModelList = data.getResults();
                addVideoListInAdapter();
                if (shareActionProvider != null) {
                    shareActionProvider.setShareIntent(getShareIntent());
                }
                if (videoModelList.size() < 3) {
                    int height = (int) pxFromDp(getActivity(), 90) * videoModelList.size();
                    linearLayout_videos.setMinimumHeight(height);
                } else {
                    int height = (int) pxFromDp(getActivity(), 70) * videoModelList.size();
                    linearLayout_videos.setMinimumHeight(height);
                }

            }

        }

        @Override
        public void onLoaderReset(android.support.v4.content.Loader<ListResponsVideo> loader) {

        }
    };

    public void addVideoListInAdapter() {
        VideoAdapter videoAdapter = new VideoAdapter(videoModelList, getActivity());
        recyclerView_video.setAdapter(videoAdapter);

        recyclerView_video.setLayoutManager(new LinearLayoutManager(getActivity()));

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        recyclerView_video.setLayoutManager(gridLayoutManager);
        // recyclerView_video.setNestedScrollingEnabled(false);
        videoAdapter.notifyDataSetChanged();
    }

    //endregion

    @Override
    public void onResume() {
        super.onResume();
    }

    public float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    private ShareActionProvider shareActionProvider;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_details_screen, menu);
        menuShareItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuShareItem);
    }


    private Intent getShareIntent() {
        String trailer_url = null;
        Intent intent = null;
        if (videoModelList.size() > 0) {
            trailer_url = Constants.STATIC_YOUTUBE_URL + videoModelList.get(0).getKey();

         intent = new Intent(Intent.ACTION_SEND,
                Uri.parse(Constants.STATIC_YOUTUBE_URL + videoModelList.get(0).getKey()));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Great Movies I Recommend: " + "\n" + moviesModel.getTitle());

        intent.putExtra(Intent.EXTRA_TEXT, trailer_url);
    }
        return intent;
    }
}

