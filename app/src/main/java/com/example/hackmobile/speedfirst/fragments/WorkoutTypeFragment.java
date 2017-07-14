package com.example.hackmobile.speedfirst.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.hackmobile.speedfirst.FartlekWorkout;
import com.example.hackmobile.speedfirst.CreateWorkoutActivity;
import com.example.hackmobile.speedfirst.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dougsigelbaum on 7/2/16.
 */
public class WorkoutTypeFragment extends Fragment {
    @BindView(R.id.fartlek_button) protected Button fartlekButton;
    @BindView(R.id.distance_button) protected Button distanceButton;
    @BindView(R.id.tempo_button) protected Button tempoButton;
    @BindView(R.id.interval_button) protected Button intervalButton;
    @BindView(R.id.workoutTypeLayout) protected LinearLayout workoutTypeLayout;
    private CreateWorkoutActivity hostActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hostActivity = (CreateWorkoutActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View ret = inflater.inflate(R.layout.workout_type_choice, null);
        ButterKnife.bind(this, ret);
        fartlekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFartlekSetup();
            }
        });
        distanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDistanceSetup();
            }
        });
        tempoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTempoSetup();
            }
        });
        intervalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntervalSetup();
            }
        });
        return ret;
    }

    public void startFartlekSetup() {
        FartlekTypePickerFragment fartlekTypePickerFragment = new FartlekTypePickerFragment();
        hostActivity.setCreatingWorkout(new FartlekWorkout());
        hostActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainRootView, fartlekTypePickerFragment)
                .addToBackStack("WorkoutTypeFragment")
                .commit();
    }

    public void startDistanceSetup() {

    }

    public void startTempoSetup() {

    }

    public void startIntervalSetup() {

    }
}
