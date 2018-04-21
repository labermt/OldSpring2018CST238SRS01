package com.example.bbergst.cst238srs01;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText _editTextName;
    private EditText _editTextMonth;
    private EditText _editTextDay;
    private Button _buttonSubmit;
    private String arrMonth[];

    private int datesEntered;
    private TextView _textViewCount;
    private TextView _SameBD;
    private String[] Birth;
   // SharedPreferences sharedpreferences;
    private static ArrayList<BDate> EnteredBirthDates = new ArrayList();

    public class BDate{
        BDate(String N,String M,String D){
            Name_ = N;
            Month_ = M;
            Day_ = D;
        }

        public String Name_;
        public String Month_;
        public String Day_;
    }
    public abstract class RequiredTextValidator implements TextWatcher {
        private final TextView textView;

        RequiredTextValidator(TextView textView) {
            this.textView = textView;
        }

        public abstract void validate(TextView textView, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = textView.getText().toString();
            validate(textView, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Not Using */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Not Using */ }
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            arrMonth = getResources().getStringArray(R.array.Month_Array);
            datesEntered = 0;
            _textViewCount =  (TextView)findViewById(R.id.textViewCount);
            _SameBD =   (TextView)findViewById(R.id.SameDay);

            //put button stuff here!
            _editTextName = findViewById(R.id.editTextName);
            _editTextName.addTextChangedListener(new RequiredTextValidator(_editTextName) {
                @Override public void validate(TextView textView, String text) {
                    if(text.trim().isEmpty()){
                        textView.setError("Invalid Name");
                    }
                }
            });

            _editTextMonth = findViewById(R.id.editTextMonth);
            _editTextMonth.addTextChangedListener(new RequiredTextValidator(_editTextMonth) {
                @Override public void validate(TextView textView, String text){
                    if(text.trim().isEmpty()){
                        textView.setError("Invalid Month");
                    }
                    if(isValidMonth(text)==false){
                        textView.setError("Invalid Month");
                    }
                }
            });

            _editTextDay = findViewById(R.id.editTextDay);

            _editTextMonth.addTextChangedListener(new RequiredTextValidator(_editTextDay) {
                @Override public void validate(TextView textView, String text){
                    if(text.trim().isEmpty()){
                        textView.setError("Invalid Day");
                    }
                    if(_editTextMonth.getText().toString() == ""){
                        textView.setError("Invalid Month");
                    }
                    if (isValidDay(_editTextMonth.getText().toString(), text) == false) {
                            textView.setError("Invalid Day");
                        }
                    }

            });
            _buttonSubmit =(Button)findViewById(R.id.buttonSubmit);
            //sharedpreferences = getSharedPreferences("DataFile.txt", Context.MODE_PRIVATE);

            _buttonSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isValidMonth(_editTextMonth.getText().toString()) == true && isValidDay(_editTextMonth.getText().toString(), _editTextDay.getText().toString()) == true) {
                        String name = _editTextName.getText().toString();
                        String month = _editTextMonth.getText().toString();
                        String day = _editTextDay.getText().toString();

                        //SharedPreferences.Editor editor = sharedpreferences.edit();

                        EnteredBirthDates.add( new BDate(name,month,day));
                       // editor.putString("Name", name);
                       // editor.putString("Month", month);
                       // editor.putString("Day", day);


                       // editor.commit();
                        Toast.makeText(MainActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                        datesEntered++;
                       // editor.putInt("dates_entered",datesEntered);
                        //editor.commit();
                       _textViewCount.setText( Integer.toString(datesEntered));

                        for (int i = 0; i < EnteredBirthDates.size(); i++) {
                            for (int j = 1; j < EnteredBirthDates.size(); j++) {
                                if (EnteredBirthDates.get(i).Month_.compareTo(EnteredBirthDates.get(j).Month_)==0 && EnteredBirthDates.get(i).Day_.compareTo(EnteredBirthDates.get(j).Day_) == 0 && i != j)  {
                                    // duplicate element found
                                    Toast.makeText(MainActivity.this, "SAME BIRTHDAY!", Toast.LENGTH_LONG).show();
                                    _SameBD.setText(EnteredBirthDates.get(i).Name_.toString()+" and "+EnteredBirthDates.get(j).Name_.toString());
                                    datesEntered = 0;
                                    _textViewCount.setText( Integer.toString(datesEntered));
                                    EnteredBirthDates.clear();
                                }
                            }
                        }

                        //if two dates are the same
                       //SharedPreferences.Editor.clear()
                       // editor.commit();

                    } else {
                        Toast.makeText(MainActivity.this, "Form not complete", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }



    private boolean isValidMonth(String arg) {
        boolean valid = false;
        for(int i = 0; i < arrMonth.length;i++){
            if(arg.equals(arrMonth[i])){
                valid = true;
            }
        }
        return valid;
    }

    private boolean isValidDay(String month, String day) {
        boolean valid = true;
        int date = 0;
        switch(day){
            case "": break;
            case "1": date = 1; break;
            case "2": date = 2; break;
            case "3": date = 3; break;
            case "4": date = 4; break;
            case "5": date = 5; break;
            case "6": date = 6; break;
            case "7": date = 7; break;
            case "8": date = 8; break;
            case "9": date = 9; break;
            case "10": date = 10; break;
            case "11": date = 11; break;
            case "12": date = 12; break;
            case "13": date = 13; break;
            case "14": date = 14; break;
            case "15": date = 15; break;
            case "16": date = 16; break;
            case "17": date = 17; break;
            case "18": date = 18; break;
            case "19": date = 19; break;
            case "20": date = 20; break;
            case "21": date = 21; break;
            case "22": date = 22; break;
            case "23": date = 23; break;
            case "24": date = 24; break;
            case "25": date = 25; break;
            case "26": date = 26; break;
            case "27": date = 27; break;
            case "28": date = 28; break;
            case "29": date = 29; break;
            case "30": date = 30; break;
            case "31": date = 31; break;
            default: return false;
        }

        if(month == "Feb" || month == "February"){
            if(date > 29){
                valid = false;
            }
        } else if(month == "Apr"||month=="April"||month=="Jun"||month=="June"||month=="Sep"||month=="September"||month=="Nov"||month=="November"){
            if(date > 30) {
                valid = false;
            }
        } else {
            if(date>32){
                valid = false;

            }
        }

        return valid;
    }
}