package com.example.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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


    private TextView setStyles(TextView textView) {
        ConstraintLayout.LayoutParams serviceDescParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        textView.setTextSize(32);
        textView.setTextColor(Color.WHITE);
        textView.setWidth(View.MEASURED_SIZE_MASK);
        textView.setLayoutParams(serviceDescParams);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        return textView;
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
        AppDao dao = db.appDao();
        List<AppEntity> allOperations = dao.getAllOperations();

       for(AppEntity operation : allOperations) {
           int id = operation.operationID;
           // CREATE A NEW TEXT VIEW COMPONENT
           TextView textView = new TextView(getContext());
           // set id
           textView.setId(id);
           // set text (operation)
           textView.setText(operation.operation);
           // set styles
           textView = setStyles(textView);

           // ADD NEW TEXT VIEW INTO LINEAR LAYOUT
           LinearLayout layout = binding.layout;
           layout.addView(textView);
       }

    }
}
