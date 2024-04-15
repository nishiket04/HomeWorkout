package com.nishiket.homeworkout.user;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentMusicProviderBinding;

public class MusicProviderFragment extends Fragment {
    private FragmentMusicProviderBinding musicProviderBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        musicProviderBinding = FragmentMusicProviderBinding.inflate(inflater,container,false);
        return musicProviderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        musicProviderBinding.spotifyLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com"));
                i.setPackage("com.spotify.music"); // Specify the package name of Spotify app
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add flag to start the activity in a new task
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    // Handle case where Spotify app is not installed
                    // Fallback to opening Spotify website in a web browser
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com"));
                    startActivity(webIntent);
                }
            }
        });

        musicProviderBinding.backToHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }
}