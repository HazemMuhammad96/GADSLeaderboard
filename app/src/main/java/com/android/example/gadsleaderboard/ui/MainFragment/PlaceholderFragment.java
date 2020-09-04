package com.android.example.gadsleaderboard.ui.MainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.gadsleaderboard.recyclerAdapters.LearningLeadersAdapter;
import com.android.example.gadsleaderboard.R;
import com.android.example.gadsleaderboard.models.LearningLeader;
import com.android.example.gadsleaderboard.models.SkillLeader;
import com.android.example.gadsleaderboard.recyclerAdapters.SkillLeadersAdapter;
import com.android.example.gadsleaderboard.services.LeaderService;
import com.android.example.gadsleaderboard.services.ServiceBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlaceholderFragment extends Fragment {

    private int mFragmentPosition;
    private ProgressBar mProgressBar;
    private ImageView mErrorImageView;
    private TextView mErrorTextView;

    public PlaceholderFragment(int position) {
        mFragmentPosition = position;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setRetainInstance(true);

        //Views Declaration
        final TextView textView = (TextView) rootView.findViewById(R.id.textView);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.loading_progressBar);
        mErrorImageView = (ImageView) rootView.findViewById(R.id.loadingError_imageView);
        mErrorTextView = (TextView) rootView.findViewById(R.id.loadingError_textView);
        mProgressBar.setVisibility(View.VISIBLE);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.leaders_recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);

        if (mFragmentPosition == 0) {
            Call<List<LearningLeader>> leaderCall = leaderService.getLearningLeaders();
            leaderCall.enqueue(new Callback<List<LearningLeader>>() {
                @Override
                public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                    if (response.isSuccessful()) {
                        LearningLeadersAdapter adapter = new LearningLeadersAdapter(response.body());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        mProgressBar.setVisibility(View.GONE);
                    } else
                        failureResponse(rootView, "Error Connecting to the Server");
                }

                @Override
                public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                    failureResponse(rootView, "Network Error");
                }
            });
        } else if (mFragmentPosition == 1) {
            Call<List<SkillLeader>> leaderCall = leaderService.getSkillIqLeaders();
            leaderCall.enqueue(new Callback<List<SkillLeader>>() {
                @Override
                public void onResponse(Call<List<SkillLeader>> call, Response<List<SkillLeader>> response) {
                    if (response.isSuccessful()) {
                        SkillLeadersAdapter adapter = new SkillLeadersAdapter(response.body());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        mProgressBar.setVisibility(View.GONE);
                    } else
                        failureResponse(rootView, "Error Connecting to the Server");
                }

                @Override
                public void onFailure(Call<List<SkillLeader>> call, Throwable t) {
                    failureResponse(rootView, "Network Error");
                }
            });
        }

        return rootView;
    }

    private void failureResponse(View rootView, String errorMessage) {
        Snackbar.make(rootView, "Failed To Load Leaderboard.", Snackbar.LENGTH_SHORT).show();
        mErrorImageView.setImageResource(R.drawable.ic_wifi_error);
        mErrorImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setText(errorMessage);
    }

}