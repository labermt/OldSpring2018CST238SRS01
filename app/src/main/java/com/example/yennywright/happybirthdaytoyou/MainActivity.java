package com.example.yennywright.happybirthdaytoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Person> personList = new ArrayList<Person>();
    List<String> months = Arrays.asList("january", "february", "march", "april", "may",
                    "june", "july", "august", "september", "october", "november", "december",
            "jan", "feb", "mar", "apr", "jun", "jul", "aug", "sept", "oct", "nov", "dec",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

    List<String> fullMonths = Arrays.asList("january", "february", "march", "april", "may",
            "june", "july", "august", "september", "october", "november", "december");

    TextView outFinalMessageBox;
    TextView countDisplayBox;
    EditText nameText;
    EditText monthText;
    EditText dayText;
    Button submitButton;
    Button resetButton;
    String finalName;
    String finalMonth;
    String finalDay;
    int EntryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        outFinalMessageBox = findViewById(R.id.textview_output_mssg);
        countDisplayBox = findViewById(R.id.textview_count_display);

        //NAME field
        nameText = findViewById(R.id.edittext_name_input);
        nameText.addTextChangedListener(new TextValidator(nameText) {
            @Override
            public void validate(TextView textView, String text) {
                String userName = nameText.getText().toString();
                if(nameValidator(userName)){
                    finalName = userName;
                }
            }
        });

        //MONTH field
        monthText = findViewById(R.id.edittext_month_input);
        monthText.addTextChangedListener(new TextValidator(monthText) {
            @Override
            public void validate(TextView textView, String text) {
                String userMonth = monthText.getText().toString();
                if(monthValidator(userMonth)){
                    finalMonth = userMonth;
                }
            }
        });

        //DAY field
        dayText = findViewById(R.id.edittext_day_input);
        dayText.addTextChangedListener(new TextValidator(dayText) {
            @Override
            public void validate(TextView textView, String text) {
                String finalDayStr = dayText.getText().toString();
                if(dayValidator(finalDayStr)){
                    finalDay = finalDayStr;
                }
            }
        });

        //initializing buttons
        //SUBMIT button
        submitButton = findViewById(R.id.button_submittion);
        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //final check before button clicked
                if(!nameValidator(nameText.getText().toString())){
                    nameText.requestFocus();
                    nameText.setError("Please enter your name.");
                }
                else if(!monthValidator(monthText.getText().toString())){
                    monthText.requestFocus();
                    monthText.setError("Please enter month.");
                }
                else if(!dayValidator(dayText.getText().toString())){
                    dayText.requestFocus();
                    dayText.setError("Please enter day.");
                }
                else {
                    if (dateIsValid(finalMonth, finalDay)) {

                        //unifying month-input
                        String trimmedFinalMonth;
                        if(fullMonths.contains(finalMonth.toLowerCase())) {
                            trimmedFinalMonth = finalMonth.toLowerCase().substring(0,3);
                        }
                        else{
                            trimmedFinalMonth = finalMonth.toLowerCase();
                        }

                        //creating new date and person objects
                        DateSet newDate = new DateSet(trimmedFinalMonth, Integer.parseInt(finalDay));
                        Person newPerson = new Person(finalName, newDate);
                        countDisplayBox.setText("" + EntryCount);

                        //find similar dates then display the names
                        StringBuilder builder = new StringBuilder();
                        for(Person peep : personList){
                            if(peep.getUserBirthday().equals(newDate)) {
                                builder.append("Happy Birthday to: \n" + newPerson.getUserName() +
                                                                    "\n" + peep.getUserName());
                                outFinalMessageBox.setText(builder.toString());
                            }
                            else {
                                outFinalMessageBox.setText("No match found yet.");
                            }
                        }
                        //add to persistent storage
                        personList.add(newPerson);

                        //display entry count
                        EntryCount = personList.size();
                        String countStr = new String ("" + EntryCount);
                        countDisplayBox.setText(countStr);

                        //clear the input fields
                        nameText.setText("");
                        nameText.setError(null);
                        monthText.setText("");
                        monthText.setError(null);
                        dayText.setText("");
                        dayText.setError(null);
                    }
                }
            }
        });

        //RESET button
        resetButton = findViewById(R.id.button_resetstorage);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                personList.clear();
                int EntryCount = 0;

                countDisplayBox.setText("" + EntryCount);
                outFinalMessageBox.setText("Storage is empty.");
            }
        });
    }

    public boolean isAlphanumeric(String str)
    {
        boolean status = true;
        char[] charArray = str.toCharArray();
        if(str.length() > 0){
            for (char c : charArray) {
                if (!Character.isLetterOrDigit(c))
                    status = false;
            }
        }
        else {
            status = false;
        }

        return status;
    }

    public boolean nameValidator(String inName)
    {
        boolean status = true;
        if (inName != null){
            if(!isAlphanumeric(inName)) {
                nameText.setError("Accept Alphanumeric only.");
                status = false;
            }
        }
        else {
            nameText.setError("Please enter your name.");
            status = false;
        }

        return status;
    }

    public boolean monthValidator(String inMonth)
    {
        boolean status = true;
        if (inMonth != null){
            if(months.contains(inMonth.toLowerCase()) == false) {
                monthText.setError("Input is invalid.");
                status = false;
            }
        }
        else {
            monthText.setError("Please enter month.");
            status = false;
        }

        return status;
    }

    public boolean dayValidator(String inDay)
    {
        boolean status = true;
        if (inDay != null){
            if(!isNumeric(inDay) || inDay == "0") {
                dayText.setError("Input is invalid.");
                status = false;
            }
        }
        else {
            dayText.setError("Please enter day.");
            status = false;
        }

        return status;
    }

    public static boolean isNumeric(String inDay)
    {
        return inDay.matches("-?\\d+(.\\d+)?");
    }


    public boolean dateIsValid(String inMonth, String dDay)
    {
        boolean valid = false;

        if((inMonth != null && inMonth != "") && (dDay != null && dDay != ""))
        {
            int inDay = Integer.parseInt(dDay);
            String inMonthLowCase = inMonth.toLowerCase();
            String trimmedLowCaseMonth;

            if(fullMonths.contains(inMonthLowCase)) {
                trimmedLowCaseMonth = inMonthLowCase.substring(0,3);
            }
            else{
                trimmedLowCaseMonth = inMonthLowCase;
            }

            if(trimmedLowCaseMonth.matches("jan")||inMonth == "1"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("feb")||inMonth == "2"){
                if(inDay >= 1 && inDay <= 29){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("mar")||inMonth == "3"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("apr")||inMonth == "4"){
                if(inDay >= 1 && inDay <= 30){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("may")||inMonth == "5"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("jun")||inMonth == "6"){
                if(inDay >= 1 && inDay <= 30){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("jul")||inMonth == "7"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("aug")||inMonth == "8"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("sep")||inMonth == "9"){
                if(inDay >= 1 && inDay <= 30){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("oct")||inMonth == "10"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("nov")||inMonth == "11"){
                if(inDay >= 1 && inDay <= 30){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }
            else if(trimmedLowCaseMonth.matches("dec")||inMonth == "12"){
                if(inDay >= 1 && inDay <= 31){
                    valid = true;
                }
                else{
                    dayText.setError("Date is invalid.");
                    valid = false;
                }
            }

        }
        return valid;
    }
}