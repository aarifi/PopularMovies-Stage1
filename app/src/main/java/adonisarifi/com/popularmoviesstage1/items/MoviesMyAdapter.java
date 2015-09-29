package adonisarifi.com.popularmoviesstage1.items;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.model.MoviesModel;
import adonisarifi.com.popularmoviesstage1.ui.activity.DetailsScreen;
import adonisarifi.com.popularmoviesstage1.utils.Constants;

/**
 * Created by AdonisArifi on 2.9.2015 - 2015 .
 */
public class MoviesMyAdapter extends RecyclerView.Adapter<MoviesMyAdapter.MovieViewHolder> {

    private List<MoviesModel> moviesModels;
    private Context context;

    public MoviesMyAdapter(List<MoviesModel> moviesModels, Context context) {
        this.moviesModels = moviesModels;
        this.context = context;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate the my custom layout
        View myContentView = inflater.inflate(R.layout.grid_item, parent, false);
        //return a new holder instance
        MovieViewHolder movieViewHolder = new MovieViewHolder(myContentView);

        return movieViewHolder;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        final MoviesModel moviesModel = moviesModels.get(position);

        TextView txtTitle = holder.title;
        txtTitle.setText(moviesModel.getTitle());
        TextView txtsupporttext = holder.txtOverview;
        txtsupporttext.setText(moviesModel.getOverview());
        final ImageView imageView = holder.imgMovie;
        if (moviesModel.getCachedPosterPath() == null) {
            moviesModel.setCachedPosterPath("http://image.tmdb.org/t/p/" + Constants.LIST_IMAGE_SIZE + "/" + moviesModel.getPosterPath());
        }
        Picasso.with(this.context).load(moviesModel.getCachedPosterPath()).into(imageView);
        holder.setClickListener(new ItemClickListener1() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailsScreen.class);
                intent.putExtra("Title", moviesModel.getTitle());
                intent.putExtra("ReleaseDate", moviesModel.getReleaseDate());
                intent.putExtra("Overview", moviesModel.getOverview());
                intent.putExtra("VoteAvarage", moviesModel.getVoteAvarage());
                intent.putExtra("CachedPosterPath", moviesModel.getCachedPosterPath());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesModels == null ? 0 : moviesModels.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ItemClickListener1 clickListener;
        public TextView title;
        public TextView txtOverview;
        public ImageView imgMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txttitle);
            txtOverview = (TextView) itemView.findViewById(R.id.txtOverview);
            imgMovie = (ImageView) itemView.findViewById(R.id.imgMovie);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        public void setClickListener(ItemClickListener1 itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }


}

interface ItemClickListener1 {
    void onClick(View view, int position, boolean isLongClick);
}