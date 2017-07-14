package com.example.hackmobile.speedfirst;

/**
 * Created by dougsigelbaum on 7/16/16.
 */
public interface Workout {
    public static final String FARTLEK = "Fartlek";

    public String getWorkoutType();
    public void setTotalDuration(Duration duration);
    public Duration getTotalDuration();
}