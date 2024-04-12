package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentHeightBinding;
import com.nishiket.homeworkout.databinding.FragmentHeightInFeetBinding;
import com.nishiket.homeworkout.model.UserHeightModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeightInFeetFragment extends Fragment {

    private FragmentHeightInFeetBinding heightInFeetBinding;


    static double height_feet;
    public HeightInFeetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        heightInFeetBinding = FragmentHeightInFeetBinding.inflate(inflater, container, false);
        return heightInFeetBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        heightInFeetBinding.heightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!heightInFeetBinding.feetEditText.getText().toString().isEmpty()) {
                    height_feet=Double.parseDouble(heightInFeetBinding.feetEditText.getText().toString());
                    UserHeightModel userHeightModel = new UserHeightModel();
                    userHeightModel.setEmail(authViewModel.getCurrentUser().getEmail());
                    userHeightModel.setHeight(height_feet);
                    Log.d("height", "onClick: "+height_feet);
                    Call<ResponseBody> call = retrofit.setHeight(123,userHeightModel);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("height", "onResponse: "+response.code());
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("height", "onFailure: "+t.getMessage());
                        }
                    });
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.frame,new WeightFragment()).commit();
                }
            }
        });

    }
}
