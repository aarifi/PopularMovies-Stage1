package adonisarifi.com.popularmoviesstage1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.model.ReviewModel;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AdonisArifi on 5.11.2015 - 2015 . PopularMovies,Stage1
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private Context context;
    private List<ReviewModel> reviewModelListItem;

    public ReviewAdapter(List<ReviewModel> reviewModelList, Context context) {
        this.context = context;
        this.reviewModelListItem = reviewModelList;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewContent = inflater.inflate(R.layout.review_item, parent, false);
        ReviewHolder reviewHolder = new ReviewHolder(viewContent);
        return reviewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {

        ReviewModel reviewModel = reviewModelListItem.get(position);
        final ImageView imageViewAuthor = holder.item_review_photo;
        TextView textViewTitle = holder.txt_item_review_title;
        TextView textViewDesc = holder.txt_item_review_desc;

       // Glide.with(context).load(R.mipmap.ic_account_circle_black_24dp).into(imageViewAuthor);
        Glide.with(context).load(R.mipmap.ic_account_circle_black_24dp).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageViewAuthor) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageViewAuthor.setImageDrawable(circularBitmapDrawable);
            }
        });
        textViewTitle.setText(reviewModel.getAuthor());
     textViewDesc.setText(reviewModel.getContent());

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return reviewModelListItem == null ? 0 : reviewModelListItem.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_review_photo)
        ImageView item_review_photo;
        @Bind(R.id.txt_item_review_author_title)
        TextView txt_item_review_title;
        @Bind(R.id.txt_item_review_desc)
        TextView txt_item_review_desc;

        public ReviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
