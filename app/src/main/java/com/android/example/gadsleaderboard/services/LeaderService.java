package com.android.example.gadsleaderboard.services;

import com.android.example.gadsleaderboard.models.Leader;
import com.android.example.gadsleaderboard.models.LearningLeader;
import com.android.example.gadsleaderboard.models.SkillLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeaderService {

    @GET("hours")
    Call<List<LearningLeader>> getLearningLeaders();

    @GET("skilliq")
    Call<List<SkillLeader>> getSkillIqLeaders();
}
