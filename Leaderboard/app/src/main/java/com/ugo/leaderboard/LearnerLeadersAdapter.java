package com.ugo.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.leaderboard.model.LearnerLeader;

import java.util.ArrayList;

public class LearnerLeadersAdapter extends
        RecyclerView.Adapter<LearnerLeadersAdapter.LearnerLeadersViewHolder>  {
    ArrayList<LearnerLeader> mLearnerLeaders;

    public LearnerLeadersAdapter(ArrayList<LearnerLeader> learnerLeaders) {
        mLearnerLeaders = learnerLeaders;
    }

    @NonNull
    @Override
    public LearnerLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_leader_list, parent, false);
        return new LearnerLeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerLeadersViewHolder holder, int position) {
        LearnerLeader learnerLeader = mLearnerLeaders.get(position);
        holder.bind(learnerLeader);
    }

    @Override
    public int getItemCount() {
        return mLearnerLeaders.size();
    }

    public class LearnerLeadersViewHolder extends RecyclerView.ViewHolder {
        TextView mTextName;
        TextView mTextDetails;
        ImageView mImageView;
        final String LEARNER_HOUR_TEXT = " learning hours, ";
        public LearnerLeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextDetails = itemView.findViewById(R.id.text_details);
            mImageView = itemView.findViewById(R.id.imageView);
        }

        public void bind (LearnerLeader leader) {
            mImageView.setImageResource(R.drawable.top_learner);
            mTextName.setText(leader.getName());
            String details = leader.getHours() + LEARNER_HOUR_TEXT + leader.getCountry();
            mTextDetails.setText(details);
        }
    }
}
