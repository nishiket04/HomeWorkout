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
import com.nishiket.homeworkout.adapter.MainGoalRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentGoalBinding;
import com.nishiket.homeworkout.model.CardList;
import com.nishiket.homeworkout.model.UserGoalModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalFragment extends Fragment {
    private List<CardList> cardListList = new ArrayList<>();
    private FragmentGoalBinding goalBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        goalBinding = FragmentGoalBinding.inflate(inflater,container,false);
        return goalBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AuthViewModel authViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

            CardList c1 = new CardList();
            CardList c2 = new CardList();
            CardList c3 = new CardList();
            CardList c4 = new CardList();

            c1.setGender("Lose weight");
            c2.setGender("Keep fit");
            c3.setGender("Get stronger");
            c4.setGender("Gain muscle mass");

            c1.setImage(R.drawable.weight_scale);
            c2.setImage(R.drawable.clover);
            c3.setImage(R.drawable.bicep);
            c4.setImage(R.drawable.dumbbell);

            cardListList.add(c1);
            cardListList.add(c2);
            cardListList.add(c3);
            cardListList.add(c4);



        MainGoalRecyclerViewAdapter mainGoalRecyclerViewAdapter = new MainGoalRecyclerViewAdapter(getActivity());
        goalBinding.goalRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        goalBinding.goalRecycler.setAdapter(mainGoalRecyclerViewAdapter);
        mainGoalRecyclerViewAdapter.setCardListList(cardListList);
        mainGoalRecyclerViewAdapter.notifyDataSetChanged();


        goalBinding.mainGoalContinuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardList c = mainGoalRecyclerViewAdapter.getSelectedData();
                UserGoalModel userGoalModel = new UserGoalModel();
                userGoalModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userGoalModel.setGoale(c.getGender());
                Call<ResponseBody> call = retrofit.setGoale(123,userGoalModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("goal", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("goal", "onFailure: "+t.getMessage());

                    }
                });
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new BirthDateFragment()).commit();
            }
        });


    }
}