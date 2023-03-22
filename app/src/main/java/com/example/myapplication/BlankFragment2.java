package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentBlank2Binding;
import com.example.myapplication.databinding.FragmentBlankBinding;

public class BlankFragment2 extends Fragment {

    @NonNull FragmentBlank2Binding binding;

    public BlankFragment2(){
        super(R.layout.fragment_blank2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentBlank2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View buttonSwitchScreen = binding.btn;
        buttonSwitchScreen.setOnClickListener(Navigation. createNavigateOnClickListener(R.id.blankFragment, null));

    }
}