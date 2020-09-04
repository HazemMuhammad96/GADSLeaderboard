package com.android.example.gadsleaderboard;

import android.os.Bundle;

import com.android.example.gadsleaderboard.dialogs.ConfirmDialog;
import com.android.example.gadsleaderboard.dialogs.ResultDialog;
import com.android.example.gadsleaderboard.services.SubmitBuilder;
import com.android.example.gadsleaderboard.services.SubmitService;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubmissionActivity extends AppCompatActivity implements ConfirmDialog.ConfirmDialogListener {

    private EditText mGithubEditText;
    private EditText mEmailEditText;
    private EditText mLastNameEditText;
    private EditText mFirstNameEditText;
    private Button mSubmitButton;

    private boolean mIsConfirmed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //View Declaration
        mFirstNameEditText = (EditText) findViewById(R.id.editText_firstName);
        mLastNameEditText = (EditText) findViewById(R.id.editText_lastName);
        mEmailEditText = (EditText) findViewById(R.id.editText_email);
        mGithubEditText = (EditText) findViewById(R.id.editText_github);
        mSubmitButton = (Button) findViewById(R.id.button_submit_project);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!areEmpty())
                    openConfirmDialog();
                else Toast.makeText(SubmissionActivity.this,
                        "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void openConfirmDialog() {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.show(getSupportFragmentManager(), "Confirm");
    }

    private boolean areEmpty() {
        return mFirstNameEditText.getText().toString().equals("") ||
                mLastNameEditText.getText().toString().equals("") ||
                mEmailEditText.getText().toString().equals("") ||
                mGithubEditText.getText().toString().equals("");
    }

    private void submitProject() {
        SubmitService submitService = SubmitBuilder.buildService(SubmitService.class);
        Call<Void> submission = submitService.submitProject(
                mEmailEditText.getText().toString(),
                mFirstNameEditText.getText().toString(), mLastNameEditText.getText().toString(),
                mGithubEditText.getText().toString()
        );
        submission.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    ResultDialog resultDialog =
                            new ResultDialog("Submission Successful", R.drawable.ic_succeed);
                    resultDialog.show(getSupportFragmentManager(), "Result");
                } else {
                    ResultDialog resultDialog =
                            new ResultDialog("Error Submitting Project", R.drawable.ic_error);
                    resultDialog.show(getSupportFragmentManager(), "Result");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                ResultDialog resultDialog =
                        new ResultDialog("Submission not Successful", R.drawable.ic_failed);
                resultDialog.show(getSupportFragmentManager(), "Result");
            }
        });
    }

    @Override
    public void confirmSubmission(boolean isConfirmed) {
        mIsConfirmed = isConfirmed;
        Log.d("Confirm", mIsConfirmed + "From Interface");
        if (mIsConfirmed)
            submitProject();
    }
}