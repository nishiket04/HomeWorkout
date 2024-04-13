package com.nishiket.homeworkout.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentAccountInformationBinding;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserBirthModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.model.UserNameModel;
import com.nishiket.homeworkout.model.UserWeightModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;
import com.nishiket.homeworkout.viewmodel.UserDetailViewModel;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountInformationFragment extends Fragment {
    private FragmentAccountInformationBinding accountInformationBinding;
    MultipartBody.Part body;
    private Calendar calendar;
    boolean image = false;
    boolean name = false;
    String dateB;
    boolean weight = false;
    boolean date = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        accountInformationBinding = FragmentAccountInformationBinding.inflate(inflater,container,false);
        return accountInformationBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendar = Calendar.getInstance();
        AuthViewModel authViewModel = new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        UserDetailViewModel viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(UserDetailViewModel.class);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

        viewModel.getData(123,authViewModel.getCurrentUser().getEmail());
        viewModel.getImage(123,authViewModel.getCurrentUser().getEmail());

        viewModel.getImageModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ImageModel>() {
            @Override
            public void onChanged(ImageModel imageModel) {
                Glide.with(getContext()).load(imageModel.getURL()).into(accountInformationBinding.profileImage);
            }
        });
        viewModel.getUserDetailModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<UserDetailModel>() {
            @Override
            public void onChanged(UserDetailModel userDetailModel) {
                accountInformationBinding.userName.setText(userDetailModel.getName());
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                Date date;
                try {
                    date = inputDateFormat.parse(userDetailModel.getBirth());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                accountInformationBinding.birthDate.setText(outputDateFormat.format(date));
                accountInformationBinding.email.setText(userDetailModel.getEmail());
                accountInformationBinding.weight.setText(""+userDetailModel.getWeightKg()+" kg");
            }
        });
        accountInformationBinding.backToProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new ProfileFragment()).commit();
            }
        });



        accountInformationBinding.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountInformationBinding.weight.setEnabled(true);
                accountInformationBinding.weight.requestFocus();

                if (accountInformationBinding.weight.getText().toString().endsWith("kg")) {
                    accountInformationBinding.weight.setText(accountInformationBinding.weight.getText().toString().substring(0, accountInformationBinding.weight.length() - 2));
                }
                weight = true;
            }
        });
        accountInformationBinding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountInformationBinding.userName.setEnabled(true);
                accountInformationBinding.userName.requestFocus();
                name = true;
            }
        });

        accountInformationBinding.birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                date = true;
            }
        });

        accountInformationBinding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i,101);
                image = true;
            }
        });

        accountInformationBinding.saveAccountInformationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image){
                   if(body!=null) {
                        Call<ResponseBody> call = retrofit.setImage(123, authViewModel.getCurrentUser().getEmail(), body);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }
                } else if (weight) {
                    if(!accountInformationBinding.weight.getText().toString().isEmpty()){
                        UserWeightModel userWeightModel = new UserWeightModel();
                        userWeightModel.setEmail(authViewModel.getCurrentUser().getEmail());
                        userWeightModel.setWeight(Double.parseDouble(accountInformationBinding.weight.getText().toString()));
                        Call<ResponseBody> call = retrofit.setWeight(123,userWeightModel);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }
                } else if (name) {
                    if(!accountInformationBinding.userName.getText().toString().isEmpty()){
                        UserNameModel userNameModel = new UserNameModel();
                        userNameModel.setEmail(authViewModel.getCurrentUser().getEmail());
                        userNameModel.setName(accountInformationBinding.userName.getText().toString());
                        Call<ResponseBody> call = retrofit.setName(123,userNameModel);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }
                } else if (date) {
                    UserBirthModel userBirthModel = new UserBirthModel();
                    userBirthModel.setEmail(authViewModel.getCurrentUser().getEmail());
                    userBirthModel.setBirth(dateB);
                    Call<ResponseBody> call = retrofit.setBirthDate(123,userBirthModel);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
                accountInformationBinding.weight.setEnabled(false);
                accountInformationBinding.userName.setEnabled(false);
            }
        });

    }
    public File uriToFile(Uri uri) {
        String filePath = getRealPathFromURI(uri);
        return new File(filePath);
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = requireActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == getActivity().RESULT_OK && data != null) {
            Uri image = data.getData();
            if(image!=null){
                Glide.with(getContext()).load(Uri.parse(String.valueOf(image))).into(accountInformationBinding.profileImage);
                File imageFile = uriToFile(image);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
                body = MultipartBody.Part.createFormData("sendimage", imageFile.getName(), requestFile);
            }
        }
        Log.d("ActivityResult", "requestCode: " + requestCode + ", resultCode: " + resultCode + ", data: " + data.getData());
    }

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
//        String dateFormat = "dd/MM/yyyy";
//        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
//        String formattedDate = sdf.format(calendar.getTime());
//
//        // Set the formatted date to the EditText
//        birthDateBinding.birthDateEditText.setText(formattedDate);
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        Date date;
        try {
            String dateString = inputDateFormat.format(calendar.getTime());
            dateB = dateString;
            date = inputDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        accountInformationBinding.birthDate.setText(outputDateFormat.format(date));
    }
}