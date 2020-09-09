package com.ugo.leaderboard.util;

import android.net.Uri;
import android.util.Log;

import com.ugo.leaderboard.model.LearnerLeader;
import com.ugo.leaderboard.model.SkillIQLeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Api {
    private Api() {}
    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/";
    public static String learnerResult;
    public static String skillIQResult;

    public static URL buildUrl(String path) {
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon().appendEncodedPath(path)
                .build();
        try {
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        } finally {
            connection.disconnect();
        }
    }

    public static ArrayList<LearnerLeader> getLeanerLeaderFromJson(String json) {
        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        ArrayList<LearnerLeader> leaders = new ArrayList<LearnerLeader>();

        try {
            JSONArray arrayObject = new JSONArray(json); // new JSONObject(json);
            // JSONArray arrayObject = jsonObject.getJSONArray("");
            int numberOfLeaders = arrayObject.length();
            for (int i = 0; i < numberOfLeaders; i++) {
                JSONObject leaderJSON = arrayObject.getJSONObject(i);
                LearnerLeader learnerLeader = new LearnerLeader(
                        leaderJSON.getString(NAME),
                        leaderJSON.getInt(HOURS),
                        leaderJSON.getString(COUNTRY)
                );
                leaders.add(learnerLeader);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return leaders;
    }

    public static ArrayList<SkillIQLeader> getSkillIQLeaderFromJson(String json) {
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        ArrayList<SkillIQLeader> leaders = new ArrayList<SkillIQLeader>();

        try {
            JSONArray arrayObject = new JSONArray(json);
            // JSONArray arrayObject = jsonObject.getJSONArray("");
            int numberOfLeaders = arrayObject.length();
            for (int i = 0; i < numberOfLeaders; i++) {
                JSONObject leaderJSON = arrayObject.getJSONObject(i);
                SkillIQLeader iqLeader = new SkillIQLeader(
                        leaderJSON.getString(NAME),
                        leaderJSON.getInt(SCORE),
                        leaderJSON.getString(COUNTRY)
                );
                leaders.add(iqLeader);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return leaders;
    }

    public static void keepResults(String[] result) {
        learnerResult = result[0];
        skillIQResult = result[1];
    }
}
