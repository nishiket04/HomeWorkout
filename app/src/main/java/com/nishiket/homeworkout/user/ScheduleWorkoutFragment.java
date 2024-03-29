package com.nishiket.homeworkout.user;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentScheduleWorkoutBinding;

import java.util.Calendar;
import java.util.Locale;

public class ScheduleWorkoutFragment extends Fragment {
    private FragmentScheduleWorkoutBinding scheduleWorkoutBinding;
    private TimePicker timePicker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        scheduleWorkoutBinding = FragmentScheduleWorkoutBinding.inflate(inflater,container,false);
        return scheduleWorkoutBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timePicker = new TimePicker(getContext());
        scheduleWorkoutBinding.workoutTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        timeSetListener, // Your OnTimeSetListener
                        hourOfDay,
                        minute,
                        true // 24-hour format
                );
                timePickerDialog.show();
            }
        });

        scheduleWorkoutBinding.workoutTimeEditText.setFocusable(false);
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // This method will be called when the user sets the time
            // You can handle the selected time here
            String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

            // Update the EditText with the selected time
            scheduleWorkoutBinding.workoutTimeEditText.setText(selectedTime);
        }
    };
}