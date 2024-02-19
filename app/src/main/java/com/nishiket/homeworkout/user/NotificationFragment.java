package com.nishiket.homeworkout.user;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.NotificationParentRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentNotificationBinding;
import com.nishiket.homeworkout.model.NotificationChildModel;
import com.nishiket.homeworkout.model.NotificationParentModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding notificationBinding;
    private List<NotificationParentModel> notificationParentModelList = new ArrayList<>();
    private List<NotificationChildModel> notificationChildModelList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notificationBinding = FragmentNotificationBinding.inflate(inflater,container,false);
        return notificationBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        NotificationChildModel n1 = new NotificationChildModel();
        NotificationChildModel n2 = new NotificationChildModel();

        n1.setImage(R.drawable.sumo_squat);
        n2.setImage(R.drawable.back);
        n1.setNotification("Full Body Yoga");
        n2.setNotification("Functional Workout");
        n1.setTime("17:30");
        n2.setTime("10:30");

        notificationChildModelList.add(n1);
        notificationChildModelList.add(n2);

        NotificationParentModel np1 = new NotificationParentModel();
        NotificationParentModel np2 = new NotificationParentModel();

        np1.setNotifiacationTime("November, 15 2021");
        np2.setNotifiacationTime("November, 18 2021");

        np1.setNotificationChildModelList(notificationChildModelList);
        np2.setNotificationChildModelList(notificationChildModelList);

        notificationParentModelList.add(np1);
        notificationParentModelList.add(np2);
        notificationParentModelList.add(np1);
        notificationParentModelList.add(np2);
        notificationParentModelList.add(np1);
        notificationParentModelList.add(np2);

        NotificationParentRecyclerViewAdapter notificationParentRecyclerViewAdapter = new NotificationParentRecyclerViewAdapter(getActivity());
        notificationBinding.notificationPerentRecycelrView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        notificationBinding.notificationPerentRecycelrView.setAdapter(notificationParentRecyclerViewAdapter);
        notificationParentRecyclerViewAdapter.setNotificationParentModelList(notificationParentModelList);
        notificationParentRecyclerViewAdapter.notifyDataSetChanged();

        notificationBinding.backToHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }
}