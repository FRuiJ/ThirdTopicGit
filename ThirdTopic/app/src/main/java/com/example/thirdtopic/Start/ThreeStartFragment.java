package com.example.thirdtopic.Start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thirdtopic.MainActivity;
import com.example.thirdtopic.R;
import com.example.thirdtopic.StartActivity;

public class ThreeStartFragment extends Fragment {

    public Button button;
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_three_start, container, false);
        button = view.findViewById(R.id.start_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.actionStart(getActivity());
                getActivity().finish();
            }
        });
        return view;
    }
}