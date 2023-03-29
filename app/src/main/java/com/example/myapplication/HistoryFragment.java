package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.DatabaseSingleton;
import com.example.myapplication.databinding.HistoryFragmentBinding;
import com.example.myapplication.models.AppDao;
import com.example.myapplication.models.AppDao_Impl;
import com.example.myapplication.models.AppEntity;

import java.util.List;

public class HistoryFragment extends Fragment {

    @NonNull HistoryFragmentBinding binding;
    AppDatabase db;

    public HistoryFragment() {
        super(R.layout.history_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  HistoryFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // get database object
        DatabaseSingleton dbObject = DatabaseSingleton.getInstance(this.getContext());
        db = dbObject.db;

        // switch screen back
        View buttonBack = binding.buttonBack;
        buttonBack.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_history_fragment_to_blankFragment, null));

        // get all operations
        AppDao dao = (AppDao) db;
        List<AppEntity> allOperations = dao.getAllOperations();


    }
}
