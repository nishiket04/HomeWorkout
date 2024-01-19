package com.nishiket.homeworkout.SignInUp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.nishiket.homeworkout.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BirthDateFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birth_date, container, false);
    }

    private AppCompatButton birthDateContinueBtn;
    private EditText birthDateEditText;
    private Calendar calendar;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        birthDateContinueBtn = view.findViewById(R.id.birthDateContinueBtn);
        birthDateEditText = view.findViewById(R.id.birthDateEditText);
        // get instance of calender to set piced date on this
        calendar = Calendar.getInstance();
        birthDateContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new HeightFragment()).commit();
            }
        });

        birthDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // method to show date dialog
                showDatePickerDialog();
            }
        });
        // this will make it read only box
        birthDateEditText.setFocusable(false);
    }

    // This method is to show dialogBox
    private void showDatePickerDialog() {
        // show calender
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // set Date on celender
                calendar.set(calendar.YEAR,i);
                calendar.set(calendar.MONTH,i1);
                calendar.set(calendar.DATE,i2);
                // update date on editText
                updateBirthDateEditText();
            }
        },
                // get that date
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DATE)
        );
        // this will set that user can't select grater than current date
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
        // this will show dialog box
        datePickerDialog.show();
    }

    // method to update data
    private void updateBirthDateEditText() {
        // changing date format
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        String formattedDate = sdf.format(calendar.getTime());

        // Set the formatted date to the EditText
        birthDateEditText.setText(formattedDate);
    }
}