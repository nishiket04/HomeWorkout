package com.nishiket.homeworkout.user;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentAccountInformationBinding;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;
import com.nishiket.homeworkout.viewmodel.UserDetailViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AccountInformationFragment extends Fragment {
    private FragmentAccountInformationBinding accountInformationBinding;
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
        AuthViewModel authViewModel = new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        UserDetailViewModel viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(UserDetailViewModel.class);

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

    }

}