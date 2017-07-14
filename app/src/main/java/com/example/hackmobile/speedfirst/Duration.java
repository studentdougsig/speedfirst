package com.example.hackmobile.speedfirst;

/**
 * Created by dougsigelbaum on 7/16/16.
 */
public class Duration {
    private int hours;
    private int minutes;
    private int seconds;

    public Duration(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public static boolean validate(String str) {
        if(str != null) {
            String[] splitStr = str.split(":");
            if(splitStr.length == 3) {
                try {
                    int hours = Integer.parseInt(splitStr[0]);
                    int minutes = Integer.parseInt(splitStr[1]);
                    int seconds = Integer.parseInt(splitStr[2]);
                    return hours + minutes + seconds != 0;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public static Duration fromString(String str) {
        if(validate(str)) {
            String[] splitStr = str.split(":");
            int hours = Integer.parseInt(splitStr[0]);
            int minutes = Integer.parseInt(splitStr[1]);
            int seconds = Integer.parseInt(splitStr[2]);
            return new Duration(hours, minutes, seconds);
        }
        return null;
    }

    public void add(Duration duration) {
        this.hours += duration.getHours();
        this.minutes += duration.getMinutes();
        this.seconds += duration.getSeconds();
    }

    public String toString() {
        return "" + hours + ":" + minutes + ":" + seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int toSeconds() {
        return hours*3600+minutes*60+seconds;
    }

    public static Duration fromSeconds(int seconds) {
        int retHours = seconds / 3600;
        int remainderSeconds = seconds % 3600;
        int retMinutes = remainderSeconds / 60;
        int retSeconds = remainderSeconds % 60;
        return new Duration(retHours, retMinutes, retSeconds);
    }
}
