package com.ugo.leaderboard.ui.submission;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ugo.leaderboard.R;
import com.ugo.leaderboard.SubmissionActivity;
import com.ugo.leaderboard.model.Project;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitDialogFragment extends DialogFragment {
    private static final String TAG = "SubmitDialogFragment";
    private TextView mActionYes;
    private ImageView mActionCancel;
    public View mRoot;

    public static SubmitDialogFragment newInstance(int num) {
        SubmitDialogFragment f = new SubmitDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.submit_dialog, container, false);
        mActionYes = mRoot.findViewById(R.id.dialog_submit_button);
        mActionCancel = mRoot.findViewById(R.id.close_submit_dialog);
        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        mActionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((SubmissionActivity)getContext()).postValues();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return mRoot;
    }
}
