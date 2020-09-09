package com.ugo.leaderboard.ui.submission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ugo.leaderboard.R;

public class FailureDialogFragment extends DialogFragment {
    private static final String TAG = "FailureDialogFragment";

    static FailureDialogFragment newInstance(int num) {
        FailureDialogFragment f = new FailureDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.failed_submission_dialog, container, false);
    }
}
