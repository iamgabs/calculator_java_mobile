package com.example.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.myapplication.models.AppEntity;

import java.sql.SQLException;
import java.util.List;

public class HistoryFragment extends Fragment {

    @NonNull HistoryFragmentBinding binding;
    AppDatabase db;
    ConstraintLayout.LayoutParams serviceDescParams = new ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

    public HistoryFragment() {
        super(R.layout.history_fragment);
    }

    private LinearLayout createNewHorizontalLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(serviceDescParams);
        linearLayout.setLeft(0);
        linearLayout.setMinimumHeight(35);
        return linearLayout;
    }

    private TextView setStyles(TextView textView) {

        textView.setTextSize(32);
        textView.setTextColor(Color.WHITE);
        textView.setWidth(View.MEASURED_SIZE_MASK);
        textView.setLayoutParams(serviceDescParams);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        return textView;
    }

    private Button createGenericButton(int id, String text) {
        Button button = new Button(getContext());
        button.setId(id);
        button.setText(text);
        return button;
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

           // create delete button
           Button deleteButton = createGenericButton(id, "X");
           // add action to delete operation
           deleteButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   // "Delete button" has the same id of it operation
                   dao.deleteOperation(id);
                   // TODO DELETE OPERATION OF LINEAR LAYOUT
               }
           });

           // ADD NEW TEXT VIEW INTO LINEAR LAYOUT
           LinearLayout layout = binding.layout;

           // get new horizontal linear layout
           LinearLayout horizontalLinearLayout = createNewHorizontalLinearLayout();

           // add the "history text view to horizontal layout"
           horizontalLinearLayout.addView(textView);

           // add button to horizontal layout
           horizontalLinearLayout.addView(deleteButton);

           // add horizontal layout into main linear layout
           layout.addView(horizontalLinearLayout);


       }

    }
}
