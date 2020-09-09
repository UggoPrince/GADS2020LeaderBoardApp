package com.ugo.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.leaderboard.model.SkillIQLeader;

import java.util.ArrayList;

public class SkillIQLeadersAdapter extends
        RecyclerView.Adapter<SkillIQLeadersAdapter.SkillIQLeadersViewHolder> {
    ArrayList<SkillIQLeader> mSkillIQLeaders;

    public SkillIQLeadersAdapter(ArrayList<SkillIQLeader> skillIQLeaders) {
        mSkillIQLeaders = skillIQLeaders;
    }

    @NonNull
    @Override
    public SkillIQLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_leader_list, parent, false);
        return new SkillIQLeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQLeadersViewHolder holder, int position) {
        SkillIQLeader iqLeader = mSkillIQLeaders.get(position);
        holder.bind(iqLeader);
    }

    @Override
    public int getItemCount() {
        return mSkillIQLeaders.size();
    }

    public class SkillIQLeadersViewHolder extends RecyclerView.ViewHolder {
        TextView mTextName;
        TextView mTextDetails;
        ImageView mImageView;
        final String SKILL_IQ_TEXT = " skill IQ Score, ";

        public SkillIQLeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextDetails = itemView.findViewById(R.id.text_details);
            mImageView = itemView.findViewById(R.id.imageView);
        }

        public void bind (SkillIQLeader leader) {
            mImageView.setImageResource(R.drawable.skill_iq_trimmed);
            mTextName.setText(leader.getName());
            String details = leader.getScore() + SKILL_IQ_TEXT + leader.getCountry();
            mTextDetails.setText(details);
        }
    }
}
