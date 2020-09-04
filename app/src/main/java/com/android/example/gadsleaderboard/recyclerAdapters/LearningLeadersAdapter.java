package com.android.example.gadsleaderboard.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.gadsleaderboard.R;
import com.android.example.gadsleaderboard.models.LearningLeader;
import com.android.example.gadsleaderboard.models.SkillLeader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.LeadersViewHolder> {

    List<LearningLeader> mLearningLeaders;

    public LearningLeadersAdapter(List<LearningLeader> learningLeaders) {
        mLearningLeaders = learningLeaders;
    }

    @NonNull
    @Override
    public LeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new LeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersViewHolder holder, int position) {
        LearningLeader learningLeader = mLearningLeaders.get(position);
        holder.bindViews(learningLeader);
    }

    @Override
    public int getItemCount() {
        return mLearningLeaders.size();
    }

    class LeadersViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView, mScoreTextView;
        ImageView mBadgeImageView;

        public LeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.item_name_textView);
            mScoreTextView = (TextView) itemView.findViewById(R.id.item_score_textView);
            mBadgeImageView = (ImageView) itemView.findViewById(R.id.item_badge_imageView);
        }

        public void bindViews(LearningLeader leader) {
            mNameTextView.setText(leader.getName());
            Picasso.get().load(leader.getBadgeUrl()).into(mBadgeImageView);
            mScoreTextView.setText(leader.getDescription());
        }

    }
}
