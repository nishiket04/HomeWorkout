package com.nishiket.homeworkout.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.SignInUp.SignInUpActivity;

public class ProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private TextView logout;
    private LinearLayout accountInformationProfileLn;
    private CardView premium;
    private AppCompatButton goPremiumBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignId(view);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("514357553984-6jdqglnfnb99dmg2jmkhc4pjl8rojm35.apps.googleusercontent.com")
                        .requestEmail()
                        .build();
                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
                googleSignInClient.revokeAccess();
                Intent i = new Intent(getActivity(), SignInUpActivity.class);
                startActivity(i);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.finish();
            }
        });
        goPremiumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                premium.setVisibility(View.VISIBLE);
                goPremiumBtn.setVisibility(View.GONE);
            }
        });

        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                premium.setVisibility(View.GONE);
                goPremiumBtn.setVisibility(View.VISIBLE);
            }
        });

        accountInformationProfileLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new AccountInformationFragment()).addToBackStack("accountInformation").commit();
            }
        });
    }

    private void assignId(View view) {
        logout = view.findViewById(R.id.logout);
        goPremiumBtn = view.findViewById(R.id.goPrimiumBtn);
        premium = view.findViewById(R.id.premium);
        accountInformationProfileLn = view.findViewById(R.id.accountInformationProfileLn);
    }
}