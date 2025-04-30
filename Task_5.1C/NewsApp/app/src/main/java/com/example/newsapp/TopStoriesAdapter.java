package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoryViewHolder> {

    private Context context;
    private List<NewsItem> topStoriesList;
    private NewsAdapter.OnItemClickListener listener;

    public TopStoriesAdapter(Context context, List<NewsItem> topStoriesList, NewsAdapter.OnItemClickListener listener) {
        this.context = context;
        this.topStoriesList = topStoriesList;
        this.listener = listener;
    }

    public static class TopStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public TopStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewTopStory);
            titleTextView = itemView.findViewById(R.id.titleTextViewTopStory);
        }

        public void bind(final NewsItem item, final NewsAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_story, parent, false);
        return new TopStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoryViewHolder holder, int position) {
        NewsItem item = topStoriesList.get(position);
        holder.titleTextView.setText(item.getTitle());
        Glide.with(context)
                .load(item.getImageUrl())
                .centerCrop()
                .into(holder.imageView);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return topStoriesList.size();
    }
}
