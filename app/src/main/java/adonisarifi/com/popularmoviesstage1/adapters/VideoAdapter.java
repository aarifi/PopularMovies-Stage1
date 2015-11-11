package adonisarifi.com.popularmoviesstage1.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import adonisarifi.com.popularmoviesstage1.R;
import adonisarifi.com.popularmoviesstage1.model.VideoModel;
import adonisarifi.com.popularmoviesstage1.utils.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AdonisArifi on 5.11.2015 - 2015 . PopularMovies,Stage1
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private Context context;
    private List<VideoModel> videoModelsList;

    public VideoAdapter(List<VideoModel> videoModels, Context context) {
        this.context = context;
        this.videoModelsList = videoModels;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewContent = inflater.inflate(R.layout.video_item, parent, false);
        VideoHolder reviewHolder = new VideoHolder(viewContent);
        return reviewHolder;
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {

        final VideoModel videoModel = videoModelsList.get(position);
        TextView textViewTitle = holder.txt_item_video_title;
        textViewTitle.setText(videoModel.getName());
        holder.setClickListener(new ItemClickListener1() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoModel.getKey()));
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(Constants.STATIC_YOUTUBE_URL + videoModel.getKey()));
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return videoModelsList == null ? 0 : videoModelsList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener1 clickListener;
        @Bind(R.id.txt_item_video_title)
        TextView txt_item_video_title;


        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        public void setClickListener(ItemClickListener1 itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }
    }


    interface ItemClickListener1 {
        void onClick(View view, int position, boolean isLongClick);
    }
}
