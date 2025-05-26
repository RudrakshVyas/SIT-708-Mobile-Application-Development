package com.example.personalizedlearningapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.activities.RecommendationActivity;
import com.example.personalizedlearningapp.models.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private final Context context;
    private final List<Subject> subjectList;

    public SubjectAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.textViewTitle.setText(subject.getTitle());
        holder.imageViewIcon.setImageResource(subject.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecommendationActivity.class);
            intent.putExtra("subject_name", subject.getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIcon;
        TextView textViewTitle;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
