package com.example.hackmobile.speedfirst.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import com.example.hackmobile.speedfirst.Duration;
import com.example.hackmobile.speedfirst.DurationListener;
import com.example.hackmobile.speedfirst.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dougsigelbaum on 7/7/16.
 */
public class DurationDialogFragment extends DialogFragment {

    @BindView(R.id.hourPicker) protected NumberPicker hourPicker;
    @BindView(R.id.minutePicker) protected NumberPicker minutePicker;
    @BindView(R.id.secondPicker) protected NumberPicker secondPicker;
    @BindView(R.id.cancelButton) protected FloatingActionButton cancelButton;
    @BindView(R.id.continueButton) protected FloatingActionButton continueButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View ret = inflater.inflate(R.layout.enter_total_duration, null);
        ButterKnife.bind(this, ret);
        initNumberPickers();
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Duration duration = new Duration(hourPicker.getValue(), minutePicker.getValue(), secondPicker.getValue());
                ((DurationListener)getTargetFragment()).onDurationSelected(duration, getTargetRequestCode());
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return ret;
    }

    public void initNumberPickers() {
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
