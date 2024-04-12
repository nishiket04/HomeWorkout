package com.nishiket.homeworkout.SignInUp;

import static androidx.lifecycle.ViewModelProvider.*;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentBirthDateBinding;
import com.nishiket.homeworkout.model.UserBirthModel;
import com.nishiket.homeworkout.model.UserGoalModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BirthDateFragment extends Fragment {
    private Calendar calendar;
    private FragmentBirthDateBinding birthDateBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        birthDateBinding = FragmentBirthDateBinding.inflate(inflater,container,false);
        return birthDateBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AuthViewModel authViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        calendar = Calendar.getInstance();
        birthDateBinding.birthDateContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBirthModel userBirthModel = new UserBirthModel();
                userBirthModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userBirthModel.setBirth(birthDateBinding.birthDateEditText.getText().toString());
                Call<ResponseBody> call = retrofit.setBirthDate(123,userBirthModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("birth", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("birth", "onFailure: "+t.getMessage());

                    }
                });
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new HeightFragment()).commit();
            }
        });

        birthDateBinding.birthDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // method to show date dialog
                showDatePickerDialog();
            }
        });
        // this will make it read only box
        birthDateBinding.birthDateEditText.setFocusable(false);
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
        birthDateBinding.birthDateEditText.setText(formattedDate);
    }
}