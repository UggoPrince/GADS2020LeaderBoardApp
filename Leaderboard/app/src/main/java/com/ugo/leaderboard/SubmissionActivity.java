package com.ugo.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.ugo.leaderboard.model.Project;
import com.ugo.leaderboard.ui.submission.FailureDialogFragment;
import com.ugo.leaderboard.ui.submission.SubmitDialogFragment;
import com.ugo.leaderboard.ui.submission.SuccessDialogFragment;
import com.ugo.leaderboard.util.GoogleFormService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity {
    TextView mSubmitButton;
    EditText mFirstnameView;
    EditText mLastnameView;
    EditText mEmailView;
    EditText mGithubLinkView;
    Project mProject = Project.getInstance();
    private SubmitDialogFragment mDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_submission);
        ImageView backArrow = findViewById(R.id.submit_activity_back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSubmitButton = findViewById(R.id.submit_button);
        mFirstnameView = findViewById(R.id.first_name_text);
        mLastnameView = findViewById(R.id.last_name_text);
        mEmailView = findViewById(R.id.email_text);
        mGithubLinkView = findViewById(R.id.github_link_text);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditTextValues();
                mDialogFragment = SubmitDialogFragment.newInstance(1);
                mDialogFragment.setCancelable(true);
                // AppCompatActivity activity = (AppCompatActivity) getActivity();
                mDialogFragment.show(getSupportFragmentManager(), "SubmitDialogFragment");
            }
        });
    }

    public void getEditTextValues() {
        mProject.setFirstname(mFirstnameView.getText().toString());
        mProject.setLastname(mLastnameView.getText().toString());
        mProject.setEmail(mEmailView.getText().toString());
        mProject.setGithubUrl(mGithubLinkView.getText().toString());
    }

    public void postValues() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Log.d("RETRO", retrofit.toString());
        GoogleFormService service = retrofit.create(GoogleFormService.class);
        Call<Void> call = service.saveForm(mProject.getEmail(), mProject.getFirstname(), mProject.getLastname(), mProject.getGithubUrl());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.d("Not Success Responce", String.valueOf(response.code()));
                    showFailureDialog();
                } else {
                    Log.d("Success Responce", response.toString());
                    showSuccessDialog();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Failure Response", t.getMessage());
            }
        });
    }

    public void showSuccessDialog() {
        try {
            mDialogFragment.dismiss();
            SuccessDialogFragment successDialogFragment = new SuccessDialogFragment();
            successDialogFragment.show(getSupportFragmentManager(), "SuccessDialogFragment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFailureDialog() {
        try {
            mDialogFragment.dismiss();
            FailureDialogFragment failureDialogFragment = new FailureDialogFragment();
            failureDialogFragment.show(getSupportFragmentManager(), "FailureDialogFragment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}