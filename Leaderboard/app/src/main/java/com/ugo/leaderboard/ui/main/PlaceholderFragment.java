package com.ugo.leaderboard.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ugo.leaderboard.LearnerLeadersAdapter;
import com.ugo.leaderboard.R;
import com.ugo.leaderboard.SkillIQLeadersAdapter;
import com.ugo.leaderboard.model.LearnerLeader;
import com.ugo.leaderboard.model.SkillIQLeader;
import com.ugo.leaderboard.util.Api;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    // private PageViewModel pageViewModel;
    private RecyclerView mRecyclerView;
    private int mIndex;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = 1;
        if (getArguments() != null) {
            mIndex = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = root.findViewById(R.id.list_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mIndex == 1) {
            ArrayList<LearnerLeader> leaders = Api.getLeanerLeaderFromJson(Api.learnerResult);
            LearnerLeadersAdapter adapter = new LearnerLeadersAdapter(leaders);
            mRecyclerView.setAdapter(adapter);
        } else {
            ArrayList<SkillIQLeader> leaders = Api.getSkillIQLeaderFromJson(Api.skillIQResult);
            SkillIQLeadersAdapter adapter = new SkillIQLeadersAdapter(leaders);
            mRecyclerView.setAdapter(adapter);
        }
    }
}