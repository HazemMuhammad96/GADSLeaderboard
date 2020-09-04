package com.android.example.gadsleaderboard.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.example.gadsleaderboard.R;

public class ResultDialog extends DialogFragment {

    private ImageView mResultBadge;
    private TextView mResultText;
    private String mResultString;
    private int mResultDrawableId;

    public ResultDialog(String resultString, int resultDrawableId) {
        mResultString = resultString;
        mResultDrawableId = resultDrawableId;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_result, null);
        builder.setView(view);

        setRetainInstance(true);

        mResultBadge = (ImageView) view.findViewById(R.id.dialog_result_badge);
        mResultText = (TextView) view.findViewById(R.id.dialog_result_text);

        populateView();
        autoDismiss();

        return builder.create();
    }

    private void populateView() {
        mResultBadge.setImageResource(mResultDrawableId);
        mResultText.setText(mResultString);
    }

    private void autoDismiss() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 2000);
    }
}
