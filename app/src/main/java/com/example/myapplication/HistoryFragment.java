package com.example.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
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
        linearLayout.setMinimumWidth(View.MEASURED_SIZE_MASK);
        linearLayout.setGravity(Gravity.RIGHT);
        return linearLayout;
    }

    private TextView setStyles(TextView textView) {

        textView.setTextSize(32);
        textView.setTextColor(Color.WHITE);
        textView.setWidth(500);
        textView.setLayoutParams(serviceDescParams);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        return textView;
    }

    private Button createGenericButton(Context context, int id, String text) {
        Button button = new Button(context);
        button.setId(id);
        Drawable d = getResources().getDrawable(R.drawable.roundedbuttonac);
        button.setBackground(d);
        button.setWidth(30);
        button.setHeight(30);
        button.setText(text);

        // create button's layout params
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ((LinearLayout.LayoutParams) params).setMargins(30, 0, 0, 0);

        button.setLayoutParams(params);

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

           // set text (operation)
           textView.setText(operation.operation);
           // set styles
           textView = setStyles(textView);

           // create delete button
           Button deleteButton = createGenericButton(getContext(), id, "X");



           // ADD NEW TEXT VIEW INTO LINEAR LAYOUT
           LinearLayout layout = binding.layout;

           // get new horizontal linear layout
           LinearLayout horizontalLinearLayout = createNewHorizontalLinearLayout();

//            add the "history text view to horizontal layout"
           horizontalLinearLayout.addView(textView);

           // add button to horizontal layout
           horizontalLinearLayout.addView(deleteButton);

           // add horizontal layout into main linear layout
           layout.addView(horizontalLinearLayout);

           // add action to delete operation
           deleteButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   // "Delete button" has the same id of it operation
                   dao.deleteOperation(id);
                   // delete from horizontal line
                   layout.removeView(horizontalLinearLayout);
               }
           });

       }

    }
}
