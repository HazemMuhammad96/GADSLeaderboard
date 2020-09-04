package com.android.example.gadsleaderboard.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.example.gadsleaderboard.R;

public class ConfirmDialog extends DialogFragment {

    private Button mConfirmButton, mCancelButton;
    ConfirmDialogListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_confirm, null);
        builder.setView(rootView);

        setRetainInstance(true);

        mConfirmButton = (Button) rootView.findViewById(R.id.dialog_button_confirm);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.confirmSubmission(true);
                dismiss();
            }
        });

        mCancelButton = (Button) rootView.findViewById(R.id.confirm_dialog_close);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (ConfirmDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }

    public interface ConfirmDialogListener {
        void confirmSubmission(boolean isConfirmed);
    }

}
