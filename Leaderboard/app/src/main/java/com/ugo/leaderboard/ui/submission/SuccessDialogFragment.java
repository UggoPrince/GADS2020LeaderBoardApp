package com.ugo.leaderboard.ui.submission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ugo.leaderboard.R;

public class SuccessDialogFragment extends DialogFragment {
    private static final String TAG = "SuccessDialogFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.success_submission_dialog, container, false);
    }
}
