package com.example.hackmobile.speedfirst;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by dougsigelbaum on 7/3/16.
 */
public class FartlekWorkout implements Workout {

    public static final String CUSTOM_SETUP = "Custom Setup";
    public static final String LADDER_SETUP = "Ladder Setup";
    public static final String CONSISTENT_SETUP = "Consistent Setup";

    private List<FartlekInterval> intervals;
    private Duration totalDuration;
    private Duration actualDuration;
    private String fartlekType;
    private String workoutType;

    public FartlekWorkout(Duration totalDuration) {
        this.workoutType = Workout.FARTLEK;
        intervals = new ArrayList();
        this.totalDuration = totalDuration;
        this.actualDuration = new Duration(0,0,0);
        this.fartlekType = CUSTOM_SETUP;
    }

    public FartlekWorkout() {
        this(null);
    }

    public String getFartlekType() {
        return fartlekType;
    }

    public void setFartlekType(String fartlekType) {
        this.fartlekType = fartlekType;
    }

    public boolean addInterval(FartlekInterval fartlekInterval) {
        intervals.add(fartlekInterval);
        actualDuration.add(fartlekInterval.getDuration());
        return actualDuration == totalDuration;
    }

    public List<FartlekInterval> getList() {
        return intervals;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Duration duration) {
        this.totalDuration = duration;
    }

    @Override
    public String getWorkoutType() {
        return workoutType;
    }

    public boolean validateDuration(Duration duration) {
        return true;//TODO
    }

    public void generateConsistent(Duration slowDuration, Duration fastDuration) {
        if(validateDuration(totalDuration) && validateDuration(slowDuration) && validateDuration(fastDuration)) {
            setFartlekType(CONSISTENT_SETUP);
            int totalDurationSeconds = totalDuration.toSeconds();
            int slowDurationSeconds = slowDuration.toSeconds();
            int fastDurationSeconds = fastDuration.toSeconds();
            ArrayList<FartlekInterval> firstRunIntervals = new ArrayList();
            int numSets = totalDurationSeconds / (slowDurationSeconds + fastDurationSeconds);
            int remainderSetSeconds = totalDurationSeconds % (slowDurationSeconds + fastDurationSeconds);
            int numSetsAdded = 0;
            if(remainderSetSeconds > 0) {
                addInterval(new FartlekInterval(Duration.fromSeconds(remainderSetSeconds), FartlekInterval.SLOW_SPEED));
                numSetsAdded += 1;
            }
            int totalSets = numSetsAdded + numSets;
            for(int i = numSetsAdded; i < totalSets; i+=2) {
                firstRunIntervals.add(new FartlekInterval(Duration.fromSeconds(slowDurationSeconds), FartlekInterval.SLOW_SPEED));
                firstRunIntervals.add(new FartlekInterval(Duration.fromSeconds(fastDurationSeconds), FartlekInterval.FAST_SPEED));
            }
            intervals = new ArrayList();
            int i = 0;
            String previousSpeed = null;
            for(FartlekInterval interval : firstRunIntervals) {
                if(previousSpeed != null) {
                    if(interval.getSpeed().equals(previousSpeed)) {
                        intervals.get(i).getDuration().add(interval.getDuration());
                    } else {
                        intervals.add(interval);
                        i++;
                    }
                }
                previousSpeed = interval.getSpeed();
            }
        }
    }

    public void generateLadder(Duration ladderIncrement, boolean roundTripLadderOption) {
        if(validateDuration(ladderIncrement)) {
            setFartlekType(LADDER_SETUP);
            int totalDurationSeconds = totalDuration.toSeconds();
            int ladderIncrementSeconds = ladderIncrement.toSeconds();
            ArrayList<FartlekInterval> firstRunIntervals = new ArrayList();
            int numSets = totalDurationSeconds / (slowDurationSeconds + fastDurationSeconds);
            int remainderSetSeconds = totalDurationSeconds % (slowDurationSeconds + fastDurationSeconds);
            int numSetsAdded = 0;
            if(remainderSetSeconds > 0) {
                addInterval(new FartlekInterval(Duration.fromSeconds(remainderSetSeconds), FartlekInterval.SLOW_SPEED));
                numSetsAdded += 1;
            }
            int totalSets = numSetsAdded + numSets;
            for(int i = numSetsAdded; i < totalSets; i+=2) {
                firstRunIntervals.add(new FartlekInterval(Duration.fromSeconds(slowDurationSeconds), FartlekInterval.SLOW_SPEED));
                firstRunIntervals.add(new FartlekInterval(Duration.fromSeconds(fastDurationSeconds), FartlekInterval.FAST_SPEED));
            }
            intervals = new ArrayList();
            int i = 0;
            String previousSpeed = null;
            for(FartlekInterval interval : firstRunIntervals) {
                if(previousSpeed != null) {
                    if(interval.getSpeed().equals(previousSpeed)) {
                        intervals.get(i).getDuration().add(interval.getDuration());
                    } else {
                        intervals.add(interval);
                        i++;
                    }
                }
                previousSpeed = interval.getSpeed();
            }
        }
    }
}
