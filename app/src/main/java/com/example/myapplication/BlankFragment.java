package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.DatabaseSingleton;
import com.example.myapplication.databinding.FragmentBlankBinding;
import com.example.myapplication.models.AppDao;
import com.example.myapplication.models.AppEntity;

import org.w3c.dom.Entity;

public class BlankFragment extends Fragment {
    FragmentBlankBinding binding;
    AppDatabase db;

    public BlankFragment(){
        super(R.layout.fragment_blank);

    }

    public static boolean isNumeric(String s)
    {
        if (s == null || s.equals("")) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentBlankBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseSingleton dbObject = DatabaseSingleton.getInstance(this.getContext());
        db = dbObject.db;

        // get TextView elements
        TextView smallTextView = binding.textViewSmall;
        TextView bigTextView = binding.bigTextView;
        final String[] operation = {""};
        final int[] id = {0};

        // Get All button clicks and insert it into text view

        // Get Button AC
        View buttonAC = binding.buttonAC;
        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ALL CLEAR FUNCTION clears the text view
                smallTextView.setText("");
                bigTextView.setText("");
            }
        });

        // Get Button Clear
        View buttonC = binding.buttonC;
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CLEAR FUNCTION clears the last char
                if(bigTextView.getText() != ""){
                    String text = (String) bigTextView.getText();
                    // remove last char and set new text
                    bigTextView.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        // Get Numbers

        // SEVEN
        View buttonSeven = binding.button7;
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"7");
            }
        });

        // EIGHT
        View buttonEight = binding.button8;
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"8");
            }
        });

        // NINE
        View buttonNine = binding.button9;
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"9");
            }
        });

        // FOUR
        View buttonFour = binding.button4;
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"4");
            }
        });

        // FIVE
        View buttonFive = binding.button5;
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"5");
            }
        });

        // SIX
        View buttonSix = binding.button6;
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"6");
            }
        });

        // ONE
        View buttonOne = binding.button1;
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"1");
            }
        });

        // TWO
        View buttonTwo = binding.button2;
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"2");
            }
        });

        // THREE
        View buttonThree = binding.button3;
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"3");
            }
        });

        // ZERO
        View buttonZero = binding.button0;
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigTextView.setText(bigTextView.getText()+"0");
            }
        });

        // POINT
        View buttonPoint = binding.buttonPoint;
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(smallTextView.getText() != "") {
                    bigTextView.setText(bigTextView.getText()+".");
                }
            }
        });

        // OPERATORS
        View buttonDivide = binding.buttonDivide;
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric((String) bigTextView.getText())) {
                    // get text of bigTextView and transfer to smallTextView
                    smallTextView.setText(bigTextView.getText());
                    // clear text of bigTextView and add the operator
                    bigTextView.setText(""+"/");
                    // set the operaration
                    if(operation[0] == ""){
                        operation[0] = "/";
                    }
                }
            }
        });
        View buttonMultiply = binding.buttonMultiply;
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric((String) bigTextView.getText())) {
                    // get text of bigTextView and transfer to smallTextView
                    smallTextView.setText(bigTextView.getText());
                    // clear text of bigTextView and add the operator
                    bigTextView.setText(""+"*");
                    // set the operaration
                    if(operation[0] == ""){
                        operation[0] = "*";
                    }
                }
            }
        });

        View buttonPlus = binding.buttonPlus;
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric((String) bigTextView.getText())) {
                    // get text of bigTextView and transfer to smallTextView
                    smallTextView.setText(bigTextView.getText());
                    // clear text of bigTextView and add the operator
                    bigTextView.setText(""+"+");
                    // set the operaration
                    if(operation[0] == ""){
                        operation[0] = "+";
                    }
                }
            }
        });

        View buttonMinus = binding.buttonMinus;
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric((String) bigTextView.getText())) {
                    // get text of bigTextView and transfer to smallTextView
                    smallTextView.setText(bigTextView.getText());
                    // clear text of bigTextView and add the operator
                    bigTextView.setText(""+"-");
                    // set the operaration
                    if(operation[0] == ""){
                        operation[0] = "-";
                    }
                }
            }
        });

        View buttonMod = binding.buttonMod;
        buttonMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric((String) bigTextView.getText())) {
                    // get text of bigTextView and transfer to smallTextView
                    smallTextView.setText(bigTextView.getText());
                    // clear text of bigTextView and add the operator
                    bigTextView.setText(""+"%");
                    // set the operaration
                    if(operation[0] == ""){
                        operation[0] = "%";
                    }
                }
            }
        });


        View buttonEquals = binding.buttonEquals;
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the first number
                double result = 0;
                Double number1 = Double.valueOf((String) smallTextView.getText());
                Double number2 = Double.valueOf(((String) bigTextView.getText()).substring(1, bigTextView.getText().length()));
                if(!operation[0].equals("") && ((number1 != null) || (number2 != null))){
                    if (operation[0].equals("+")) {
                        result = number1 + number2;
                    } else if (operation[0].equals("-")) {
                        result = number1 - number2;
                    } else if (operation[0].equals("*")) {
                        result = number1 * number2;
                    } else if (operation[0].equals("/")) {
                        if(number2 == 0.0) {
                            // clear operator
                            operation[0] = "";
                            // clear smallTextView
                            smallTextView.setText("");
                            // set undefined value
                            bigTextView.setText("Undefined");
                            return;
                        }
                        result = number1 / number2;
                    } else if (operation[0].equals("%")) {
                        result = (number1/100) * number2;
                    }

                    AppDao dao = db.appDao();
                    AppEntity entity = new AppEntity();

                    String op = String.valueOf(number1) + " " + operation[0] + " " +String.valueOf(number2);

                    // create an entity object
                    entity.operationID = id[0]+=1;
                    entity.first_number = number1;
                    entity.second_number = number2;
                    entity.operator = operation[0];
                    entity.operation = op;

                    // save operation
                    dao.newOperation(entity);

                    // clear numbers
                    number1 = null; number2 = null;
                    // clear operator
                    operation[0] = "";
                    // clear smallTextView
                    smallTextView.setText("");
                    // set the result to bigTextView
                    bigTextView.setText(String.valueOf(result));
                } else {
                    smallTextView.setText("");
                    bigTextView.setText("");
                }
            }
        });





        // go to history screen
        View buttonHistory = binding.historyButton;
        buttonHistory.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_blankFragment_to_history_fragment, null));
    }
}