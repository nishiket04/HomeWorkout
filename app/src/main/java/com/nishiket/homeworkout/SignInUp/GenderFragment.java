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

import com.google.firebase.auth.FirebaseAuth;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.CardListRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentGenderBinding;
import com.nishiket.homeworkout.model.CardList;
import com.nishiket.homeworkout.model.UserGenderModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenderFragment extends Fragment {
    private List<CardList> cardListList = new ArrayList<>();
    private FragmentGenderBinding genderBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        genderBinding = FragmentGenderBinding.inflate(inflater,container,false);
        return genderBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);


        CardList c1 = new CardList();
        c1.setImage(R.drawable.man);
        c1.setGender("Man");
        CardList c2 = new CardList();
        c2.setImage(R.drawable.woman);
        c2.setGender("Woman");
        CardList c3 = new CardList();
        c3.setImage(R.drawable.other);
        c3.setGender("Other");
        cardListList.add(c1);
        cardListList.add(c2);
        cardListList.add(c3);

        CardListRecyclerViewAdapter cardListRecyclerViewAdapter = new CardListRecyclerViewAdapter(getActivity());
        genderBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        genderBinding.recyclerView.setAdapter(cardListRecyclerViewAdapter);
        cardListRecyclerViewAdapter.setActiveComplainList(cardListList);

        genderBinding.genderContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = cardListRecyclerViewAdapter.getSelectedGender();
                UserGenderModel userGenderModel = new UserGenderModel();
                userGenderModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userGenderModel.setGender(cardListList.get(selected).getGender());
                Call<ResponseBody> call = retrofit.setGender(123,userGenderModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("call", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("call", "onFailure: "+t.getMessage());
                    }
                });
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new GoalFragment()).commit();
            }
        });

    }
}