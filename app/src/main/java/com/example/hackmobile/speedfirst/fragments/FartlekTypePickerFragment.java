package com.example.hackmobile.speedfirst.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.hackmobile.speedfirst.Duration;
import com.example.hackmobile.speedfirst.DurationListener;
import com.example.hackmobile.speedfirst.FartlekWorkout;
import com.example.hackmobile.speedfirst.CreateWorkoutActivity;
import com.example.hackmobile.speedfirst.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dougsigelbaum on 7/10/16.
 */
public class FartlekTypePickerFragment extends Fragment implements DurationListener {

    private static final int TOTAL_DURATION_FIELD = 0;
    private static final int LADDER_INCREMENT_FIELD = 1;
    private static final int SLOW_DURATION_FIELD = 2;
    private static final int FAST_DURATION_FIELD = 3;

    @BindView(R.id.roundtripOptionSpinner) protected AppCompatSpinner roundtripOptionSpinner;
    @BindView(R.id.totalDurationField) protected EditText totalDurationField;
    @BindView(R.id.ladderIncrementField) protected EditText ladderIncrementField;
    @BindView(R.id.slowDurationField) protected EditText slowDurationField;
    @BindView(R.id.fastDurationField) protected EditText fastDurationField;
    @BindView(R.id.ladderRoundtripOptionRow) protected TableRow ladderRoundtripOptionRow;
    @BindView(R.id.ladderIncrementRow) protected TableRow ladderIncrementRow;
    @BindView(R.id.consistentFastDurationRow) protected TableRow consistentFastDurationRow;
    @BindView(R.id.consistentSlowDurationRow) protected TableRow consistentSlowDurationRow;
    @BindView(R.id.customButton) protected Button customButton;
    @BindView(R.id.ladderButton) protected Button ladderButton;
    @BindView(R.id.consistentButton) protected Button consistentButton;
    @BindView(R.id.continueButton) protected FloatingActionButton continueButton;

    private CreateWorkoutActivity hostActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.select_fartlek_type_layout, null);

        ButterKnife.bind(this, view);
        ArrayAdapter<CharSequence> roundtripOptionSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.yes_no_array, android.R.layout.simple_spinner_item);
        roundtripOptionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roundtripOptionSpinner.setAdapter(roundtripOptionSpinnerAdapter);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCustomSetup();
            }
        });
        ladderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLadderSetup();
            }
        });
        consistentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectConsistentSetup();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullFieldsAndGoToCustomSetup();
            }
        });
        selectCustomSetup();
        fastDurationField.setInputType(InputType.TYPE_NULL);
        fastDurationField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGetDuration(FAST_DURATION_FIELD);
            }
        });
        fastDurationField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    launchGetDuration(FAST_DURATION_FIELD);
                }
            }
        });
        slowDurationField.setInputType(InputType.TYPE_NULL);
        slowDurationField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGetDuration(SLOW_DURATION_FIELD);
            }
        });
        slowDurationField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    launchGetDuration(SLOW_DURATION_FIELD);
                }
            }
        });
        totalDurationField.setInputType(InputType.TYPE_NULL);
        totalDurationField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGetDuration(TOTAL_DURATION_FIELD);
            }
        });
        totalDurationField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    launchGetDuration(TOTAL_DURATION_FIELD);
                }
            }
        });
        ladderIncrementField.setInputType(InputType.TYPE_NULL);
        ladderIncrementField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGetDuration(LADDER_INCREMENT_FIELD);
            }
        });
        ladderIncrementField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    launchGetDuration(LADDER_INCREMENT_FIELD);
                }
            }
        });
        return view;
    }

    public void pullFieldsAndGoToCustomSetup() {
        CharSequence totalDurationText = totalDurationField.getText();
        if(totalDurationText != null) {
            Duration totalDuration = Duration.fromString(totalDurationText.toString());
            if(totalDuration != null) {
                getCreatingWorkout().setTotalDuration(totalDuration);
                if(getCreatingWorkout().getFartlekType().equals(FartlekWorkout.LADDER_SETUP)) {
                    pullLadderFieldsAndGoToCustomSetup();
                } else if(getCreatingWorkout().getFartlekType().equals(FartlekWorkout.CONSISTENT_SETUP)) {
                    pullConsistentFieldsAndGoToCustomSetup();
                } else if (getCreatingWorkout().getFartlekType().equals(FartlekWorkout.CUSTOM_SETUP)) {
                    pullCustomFieldsAndGoToCustomSetup();
                }
            }
        }
    }

    private void pullLadderFieldsAndGoToCustomSetup() {
        CharSequence ladderIncrementText = ladderIncrementField.getText();
        if(ladderIncrementText != null) {
            Duration ladderIncrementDuration = Duration.fromString(ladderIncrementText.toString());
            if(ladderIncrementDuration != null) {
                String roundTripLadderOptionString = roundtripOptionSpinner.getSelectedItem().toString();
                if (roundTripLadderOptionString != null) {
                    boolean roundTripLadderOption = roundTripLadderOptionString.equals("Yes");
                    getCreatingWorkout().generateLadder(ladderIncrementDuration, roundTripLadderOption);
                    launchCustomFartlekSetupFragment();
                }
            }
        }
    }

    public FartlekWorkout getCreatingWorkout() {
        return (FartlekWorkout)hostActivity.getCreatingWorkout();
    }

    private void pullConsistentFieldsAndGoToCustomSetup() {
        CharSequence slowDurationText = slowDurationField.getText();
        if(slowDurationText != null) {
            Duration slowDuration = Duration.fromString(slowDurationText.toString());
            if(slowDuration != null) {
                CharSequence fastDurationText = fastDurationField.getText();
                if(fastDurationText != null) {
                    Duration fastDuration = Duration.fromString(fastDurationText.toString());
                    if(fastDuration != null) {
                        getCreatingWorkout().generateConsistent(slowDuration, fastDuration);
                        launchCustomFartlekSetupFragment();
                    }
                }
            }
        }

    }

    private void pullCustomFieldsAndGoToCustomSetup() {
        launchCustomFartlekSetupFragment();
    }

    private void launchCustomFartlekSetupFragment() {
        FartlekCustomSetupFragment fartlekCustomSetupFragment = new FartlekCustomSetupFragment();
        hostActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainRootView, fartlekCustomSetupFragment)
                .addToBackStack("FartlekTypePickerFragment")
                .commit();
    }

    public void launchGetDuration(int field) {
        DurationDialogFragment durationDialogFragment = new DurationDialogFragment();
        durationDialogFragment.setTargetFragment(FartlekTypePickerFragment.this, field);
        durationDialogFragment.show(hostActivity.getSupportFragmentManager(), "DurationDialogFragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hostActivity = (CreateWorkoutActivity)context;
    }

    @Override
    public void onDurationSelected(Duration duration, int field) {
        switch(field) {
            case TOTAL_DURATION_FIELD:
                totalDurationField.setText(duration.toString());
                break;
            case SLOW_DURATION_FIELD:
                slowDurationField.setText(duration.toString());
                break;
            case FAST_DURATION_FIELD:
                fastDurationField.setText(duration.toString());
                break;
            case LADDER_INCREMENT_FIELD:
                ladderIncrementField.setText(duration.toString());
                break;
        }
    }

    public void selectCustomSetup() {
        customButton.setBackgroundResource(R.drawable.button_pressed);
        ladderButton.setBackgroundResource(R.drawable.button_normal);
        consistentButton.setBackgroundResource(R.drawable.button_normal);
        consistentFastDurationRow.setVisibility(View.GONE);
        consistentSlowDurationRow.setVisibility(View.GONE);
        ladderIncrementRow.setVisibility(View.GONE);
        ladderRoundtripOptionRow.setVisibility(View.GONE);
        getCreatingWorkout().setFartlekType(FartlekWorkout.CUSTOM_SETUP);
    }

    public void selectLadderSetup() {
        customButton.setBackgroundResource(R.drawable.button_normal);
        ladderButton.setBackgroundResource(R.drawable.button_pressed);
        consistentButton.setBackgroundResource(R.drawable.button_normal);

        consistentFastDurationRow.setVisibility(View.GONE);
        consistentSlowDurationRow.setVisibility(View.GONE);
        ladderIncrementRow.setVisibility(View.VISIBLE);
        ladderRoundtripOptionRow.setVisibility(View.VISIBLE);
        getCreatingWorkout().setFartlekType(FartlekWorkout.LADDER_SETUP);
    }

    public void selectConsistentSetup() {
        customButton.setBackgroundResource(R.drawable.button_normal);
        ladderButton.setBackgroundResource(R.drawable.button_normal);
        consistentButton.setBackgroundResource(R.drawable.button_pressed);
        consistentFastDurationRow.setVisibility(View.VISIBLE);
        consistentSlowDurationRow.setVisibility(View.VISIBLE);
        ladderIncrementRow.setVisibility(View.GONE);
        ladderRoundtripOptionRow.setVisibility(View.GONE);
        getCreatingWorkout().setFartlekType(FartlekWorkout.CONSISTENT_SETUP);
    }
}
