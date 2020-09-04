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

public class SkillLeadersAdapter extends RecyclerView.Adapter<SkillLeadersAdapter.LeadersViewHolder> {

    List<SkillLeader> mSkillLeaders;

    public SkillLeadersAdapter(List<SkillLeader> skillLeaders) {
        mSkillLeaders = skillLeaders;
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
        SkillLeader skillLeader = mSkillLeaders.get(position);
        holder.bindViews(skillLeader);
    }

    @Override
    public int getItemCount() {
        return mSkillLeaders.size();
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


        public void bindViews(SkillLeader leader) {
            mNameTextView.setText(leader.getName());
            Picasso.get().load(leader.getBadgeUrl()).into(mBadgeImageView);
            mScoreTextView.setText(leader.getDescription());
        }

    }
}
