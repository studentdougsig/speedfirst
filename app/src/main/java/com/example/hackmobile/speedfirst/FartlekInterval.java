package com.example.hackmobile.speedfirst;

/**
 * Created by dougsigelbaum on 7/3/16.
 */
public class FartlekInterval {
    private Duration duration;
    private String speed;

    public static final String SLOW_SPEED = "Slow";
    public static final String FAST_SPEED = "Fast";
    public FartlekInterval(Duration duration, String speed) {
        this.duration = duration;
        this.speed = speed;
    }

    public Duration getDuration() {
        return duration;
    }
    public String getSpeed() {
        return speed;
    }
}
