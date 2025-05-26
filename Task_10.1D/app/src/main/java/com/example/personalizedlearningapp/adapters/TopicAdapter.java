package com.example.personalizedlearningapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.activities.AssessmentActivity;
import com.example.personalizedlearningapp.models.Topic;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private final Context context;
    private final List<Topic> topicList;

    public TopicAdapter(Context context, List<Topic> topicList) {
        this.context = context;
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.textViewTopicTitle.setText(topic.getTitle());

        //  onClick listener to open AssessmentActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AssessmentActivity.class);
            intent.putExtra("topic_title", topic.getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTopicTitle;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTopicTitle = itemView.findViewById(R.id.textViewTopicTitle);
        }
    }
}
