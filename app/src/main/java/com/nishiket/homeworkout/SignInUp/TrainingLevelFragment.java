package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.TraingListRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentTrainingLevelBinding;
import com.nishiket.homeworkout.model.TraingListModel;
import com.nishiket.homeworkout.model.UserLevelModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingLevelFragment extends Fragment {
    private FragmentTrainingLevelBinding trainingLevelBinding;
    private List<TraingListModel> traingListModelList =  new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        trainingLevelBinding = FragmentTrainingLevelBinding.inflate(inflater,container,false);
        return trainingLevelBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AuthViewModel authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        TraingListModel t1 = new TraingListModel();
        TraingListModel t2 = new TraingListModel();
        TraingListModel t3 = new TraingListModel();
        TraingListModel t4 = new TraingListModel();

        t1.setMainTxt("Medium");
        t2.setMainTxt("Irregular training");
        t3.setMainTxt("Medium");
        t4.setMainTxt("Advanced");

        t1.setSubTxt("I train 1-2 times a week");
        t2.setSubTxt("I train 2-3 times a week");
        t3.setSubTxt("I train 3-5 times a week");
        t4.setSubTxt("I train more than 5 times a week");

        traingListModelList.add(t1);
        traingListModelList.add(t2);
        traingListModelList.add(t3);
        traingListModelList.add(t4);


        TraingListRecyclerViewAdapter traingListRecyclerViewAdapter = new TraingListRecyclerViewAdapter(getActivity());
        trainingLevelBinding.traingLevelRecylerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        trainingLevelBinding.traingLevelRecylerView.setAdapter(traingListRecyclerViewAdapter);
        traingListRecyclerViewAdapter.setTraingListModelList(traingListModelList);
        traingListRecyclerViewAdapter.notifyDataSetChanged();

        trainingLevelBinding.trainingLevelContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLevelModel userLevelModel = new UserLevelModel();
                userLevelModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userLevelModel.setLevel(traingListRecyclerViewAdapter.getLevel()+1);
                Call<ResponseBody> call = retrofit.setLevel(123, userLevelModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("level", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("level", "onFailure: "+t.getMessage());

                    }
                });
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new ActivitiesInterestFragment()).commit();
            }
        });

    }
}