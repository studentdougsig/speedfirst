package com.example.hackmobile.speedfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hackmobile.speedfirst.fragments.WorkoutTypeFragment;

public class CreateWorkoutActivity extends AppCompatActivity {


    private Workout creatingWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout_activity);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainRootView, new WorkoutTypeFragment())
                .commit();
    }

    public Workout getCreatingWorkout() {
        return creatingWorkout;
    }

    public void setCreatingWorkout(Workout creatingWorkout) {
        this.creatingWorkout = creatingWorkout;
    }
}
