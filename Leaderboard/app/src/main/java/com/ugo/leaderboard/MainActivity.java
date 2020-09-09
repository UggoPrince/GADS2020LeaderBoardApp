package com.ugo.leaderboard;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayoutMediator;
import com.ugo.leaderboard.ui.main.SectionsPagerAdapter;
import com.ugo.leaderboard.util.Api;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView mOpenSubmitActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenSubmitActivityButton = findViewById(R.id.button_open_submit_activity);
        mOpenSubmitActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubmissionActivity.class);
                startActivity(intent);
            }
        });
        URL[] apiUrls;
        try {
            apiUrls = new URL[]{Api.buildUrl("hours"), Api.buildUrl("skilliq")};
            new ApiCallsTask().execute(apiUrls);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    public void setupFragment() {
        RelativeLayout relativeLayout = findViewById(R.id.gads_layout);
        TextView titleView = findViewById(R.id.app_title);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), getLifecycle());
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        relativeLayout.setVisibility(View.INVISIBLE);
        titleView.setVisibility(View.VISIBLE);
        mOpenSubmitActivityButton.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        TabLayout tabs = findViewById(R.id.tabs);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabs, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(sectionsPagerAdapter.getPageTitle(position));
            }
        });
        tabLayoutMediator.attach();
    }

    public class ApiCallsTask extends AsyncTask<URL, Void, String []> {
        @Override
        protected String[] doInBackground(URL... urls) {
            URL hourUrl = urls[0];
            URL skillIQUrl = urls[1];
            String[] result = new String[2];
            try {
                String learnerLeaders = Api.getJson(hourUrl);
                String skillIQLeaders = Api.getJson(skillIQUrl);
                result[0] = learnerLeaders;
                result[1] = skillIQLeaders;
            } catch (Exception e) {
                Log.d("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String[] result) {
            Api.keepResults(result);
            setupFragment();
        }
    }
}