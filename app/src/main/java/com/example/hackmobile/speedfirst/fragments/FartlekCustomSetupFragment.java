package com.example.hackmobile.speedfirst.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hackmobile.speedfirst.Duration;
import com.example.hackmobile.speedfirst.DurationListener;
import com.example.hackmobile.speedfirst.FartlekInterval;
import com.example.hackmobile.speedfirst.FartlekWorkout;
import com.example.hackmobile.speedfirst.CreateWorkoutActivity;
import com.example.hackmobile.speedfirst.R;
import com.example.hackmobile.speedfirst.TimeListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dougsigelbaum on 7/2/16.
 */
public class FartlekCustomSetupFragment extends Fragment implements DurationListener {

    @BindView(R.id.speedSpinner) protected AppCompatSpinner speedSpinner;
    @BindView(R.id.addButton) protected FloatingActionButton addButton;
    @BindView(R.id.saveButton) protected FloatingActionButton saveButton;
    @BindView(R.id.durationList) protected RecyclerView durationList;
    @BindView(R.id.intervalDurationField) protected EditText intervalDurationField;
    @BindView(R.id.totalDurationField) protected TextView totalDurationField;


    private TimeListAdapter timeListAdapter;
    private CreateWorkoutActivity hostActivity;


    private static final int TOTAL_DURATION_FIELD = 0;
    private static final int INTERVAL_DURATION_FIELD = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View ret = inflater.inflate(R.layout.fartlek_setup_layout, null);
        ButterKnife.bind(this, ret);
        totalDurationField.setText(getCreatingWorkout().getTotalDuration().toString());

        if(getCreatingWorkout().getTotalDuration() != null) {
            totalDurationField.setText(getCreatingWorkout().getTotalDuration().toString());
        }
        ArrayAdapter<CharSequence> speedSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.speed_array, android.R.layout.simple_spinner_item);
        speedSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedSpinner.setAdapter(speedSpinnerAdapter);
        durationList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        durationList.setLayoutManager(layoutManager);
        timeListAdapter = new TimeListAdapter(hostActivity, getCreatingWorkout());
        durationList.setAdapter(timeListAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddToTimeList();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSaveTimeList();
            }
        });
        intervalDurationField.setInputType(InputType.TYPE_NULL);
        intervalDurationField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGetDuration(INTERVAL_DURATION_FIELD);
            }
        });
        intervalDurationField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    launchGetDuration(INTERVAL_DURATION_FIELD);
                }
            }
        });
        return ret;
    }

    public void launchGetDuration(int field) {
        DurationDialogFragment durationDialogFragment = new DurationDialogFragment();
        durationDialogFragment.setTargetFragment(FartlekCustomSetupFragment.this, field);
        hostActivity.getSupportFragmentManager().beginTransaction()
                .addToBackStack("FartlekCustomSetupFragment")
                .add(durationDialogFragment, "DurationDialogFragment")
                .commit();
    }

    @Override
    public void onDurationSelected(Duration duration, int field) {
        switch(field) {
            case INTERVAL_DURATION_FIELD:
                intervalDurationField.setText(duration.toString());
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hostActivity = (CreateWorkoutActivity)context;
    }


    public void selectAddToTimeList() {
        CharSequence intervalDurationText = intervalDurationField.getText();
        if(intervalDurationText != null) {
            Duration duration = Duration.fromString(intervalDurationText.toString());
            if(duration != null) {
                String speedStr = speedSpinner.getSelectedItem().toString();
                timeListAdapter.getDataSource().addInterval(new FartlekInterval(duration, speedStr));
                timeListAdapter.notifyDataSetChanged();
            }
        }

    }

    public FartlekWorkout getCreatingWorkout() {
        return (FartlekWorkout)hostActivity.getCreatingWorkout();
    }

    public void selectSaveTimeList() {

    }
}
