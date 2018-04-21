package com.example.happybirthday2you;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.*;

public class Birthday extends AppCompatActivity {
    private EditText name;
    private EditText month;
    private EditText day;
    private TextView display;
    private TextView count;
    private boolean correctName = false;
    private boolean correctMonth = false;
    private boolean correctDay = false;
    List<People> listPeople = new ArrayList<People>();

    private boolean ValidMonth(String month) {
        boolean test = false;
        String[] goodMonths = new String[]
                {"January", "Jan", "Jan.", "january", "jan", "jan.", "1", "01",
                        "February", "Feb", "Feb.", "february", "feb", "feb.", "2", "02",
                        "March", "Mar", "Mar.", "march", "mar", "mar.", "3", "03",
                        "April", "Apr", "Apr.", "april", "apr", "apr.", "4", "04",
                        "May", "may", "5", "05",
                        "June", "Jun", "Jun.", "june", "jun", "jun.", "6", "06",
                        "July", "Jul", "Jul.", "july", "jul", "jul.", "7", "07",
                        "August", "Aug", "Aug.", "august", "aug", "aug.", "8", "08",
                        "September", "Sep", "Sep.", "september", "sep", "sep.", "9", "09",
                        "October", "Oct", "Oct.", "october", "oct", "oct.", "10",
                        "November", "Nov", "Nov.", "november", "nov", "nov.", "11",
                        "December", "Dec", "Dec.", "december", "dec", "dec.", "12"};
        for (String months : goodMonths) {
            if (month.equals(months)) {
                test = true;
            }
        }
        return test;
    }

    private boolean ValidDay(String day) {
        boolean test = true;
        Integer numDay = Integer.parseInt(day);
        if (numDay <= 0 || numDay >= 32)
            test = false;
        return test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        name = findViewById(R.id.Name);
        month = findViewById(R.id.Month);
        day = findViewById(R.id.Day);
        display = findViewById(R.id.Match);
        count = findViewById(R.id.Count);

        name.addTextChangedListener(new TextValidator(name) {
            @Override
            public void validate(TextView name, String stringName) {
                correctName = (stringName.trim().length() != 0);
                if(!correctName)
                {
                    name.setError("Invalid Name");
                }
            }
        });

        month.addTextChangedListener(new TextValidator(month) {
            @Override
            public void validate(TextView month, String stringMonth) {
                correctMonth = ValidMonth(stringMonth);
                if(!correctMonth)
                {
                    month.setError("Invalid Month");
                }
            }
        });

        day.addTextChangedListener(new TextValidator(day) {
            @Override
            public void validate(TextView day, String stringDay) {
                Integer dayLength = stringDay.trim().length();
                if (dayLength > 0)
                {
                    correctDay = ValidDay(stringDay);
                    if (!correctDay) {
                        day.setError("Invalid Day");
                    }
                }
                if(dayLength < 1)
                {
                    day.setError("Invalid Day");
                }
            }
        });

        name.setError(null);
        month.setError(null);
        day.setError(null);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = name.getText().toString();
                String stringMonth = month.getText().toString();
                String stringDay = day.getText().toString();

                name.setError(null);
                month.setError(null);
                day.setError(null);

                if (stringMonth.trim().length() == 0 || !ValidMonth(stringMonth)) {
                    month.setError("Invalid Month");
                }
                if (stringDay.trim().length() == 0 || !ValidDay(stringDay)) {
                    day.setError("Invalid Day");
                }
                if (isValidDate(stringMonth, stringDay) && stringName.trim().length() != 0) {
                    Integer numMonth = monthToNum(stringMonth);
                    People newPeople = new People(stringName, numMonth, Integer.parseInt(stringDay));
                    listPeople.add(newPeople);
                    if (matchFound(newPeople)) {
                        People match = getMatch(newPeople);
                        String stringDisplay = match._name + " and " + newPeople._name + " have the same birthday! \nIt took " + String.valueOf(listPeople.size()) + " time(s) to find a match!";
                        display.setText(stringDisplay);
                        listPeople.clear();
                    }
                    name.setError(null);
                    month.setError(null);
                    day.setError(null);
                }
                else
                {
                    month.setError("Invalid Date");
                    day.setError("Invalid Date");
                }

                name.setText(null);
                month.setText(null);
                day.setText(null);

                name.requestFocus();
                count.setText(String.valueOf(listPeople.size()));
            }

            private People getMatch(People person) {
                for (People people : listPeople) {
                    if ((people != person) && people._month.equals(person._month) && people._day.equals(person._day)) {
                        return people;
                    }
                }
                return null;
            }

            private boolean isValidDate(String month, String day) {
                boolean pass = true;
                if(day.trim().length() == 0)
                {
                    pass = false;
                    return pass;
                }
                Integer intDay = Integer.parseInt(day);
                if(!ValidDay(day)){
                    pass = false;
                    return pass;
                }
                
                if (month.equals("Feb") || month.equals("February") || month.equals("february") ||
                        month.equals("Feb.") || month.equals("feb") || month.equals("feb.") ||
                        month.equals("2") || month.equals("02")) {
                    if (intDay > 28)
                        pass = false;
                    }
                    else if (month.equals("April") || month.equals("april")|| month.equals("Apr.")|| month.equals("Apr")|| month.equals("apr")|| month.equals("apr.")||
                        month.equals("June") || month.equals("june") || month.equals("Jun.") || month.equals("Jun") || month.equals("jun") || month.equals("jun.") ||
                        month.equals("September") || month.equals("september") || month.equals("Sept") || month.equals("Sept.") || month.equals("sept") || month.equals("sept.") ||
                        month.equals("November") || month.equals("november") || month.equals("Nov.") || month.equals("Nov") || month.equals("nov.") ||month.equals("nov") ||
                        month.equals("4") || month.equals("04") || month.equals("6") ||
                        month.equals("06") || month.equals("9") || month.equals("09") || month.equals("11")) {
                    if (intDay > 30)
                        pass = false;
                    }
                    else if (month.equals("Jan") || month.equals("Jan.") || month.equals("jan") || month.equals("jan.") || month.equals("January") || month.equals("january") || month.equals("1") || month.equals("01") ||
                        month.equals("Mar") || month.equals("Mar.") || month.equals("mar") || month.equals("mar.") || month.equals("March") || month.equals("march") || month.equals("3") || month.equals("03") ||
                        month.equals("May") || month.equals("may") || month.equals("5") || month.equals("05") ||
                        month.equals("Jul") || month.equals("Jul.") || month.equals("jul") || month.equals("jul.") || month.equals("July") || month.equals("july") || month.equals("7") || month.equals("07") ||
                        month.equals("Aug") || month.equals("Aug.") || month.equals("aug") || month.equals("aug.") || month.equals("August") || month.equals("august") || month.equals("8") || month.equals("08") ||
                        month.equals("Oct") || month.equals("Oct.") || month.equals("oct") || month.equals("oct.") || month.equals("October") || month.equals("october") || month.equals("10") ||
                        month.equals("Dec") || month.equals("Dec.") || month.equals("dec") || month.equals("dec.") || month.equals("December") || month.equals("december") || month.equals("12")){
                    return pass;
                }
                else{
                    pass = false;
                }
                return pass;
            }

            private boolean matchFound(People person) {
                for (People people : listPeople) {
                    if ((people != person) && people._month.equals(person._month) && people._day.equals(person._day)) {
                        return true;
                    }
                }
                return false;
            }

            private Integer monthToNum(String month) {
                if (month.equals("Jan") || month.equals("Jan.") || month.equals("jan") || month.equals("jan.") || month.equals("January") || month.equals("january") || month.equals("1") || month.equals("01"))
                    return 1;
                if (month.equals("Feb") || month.equals("February") || month.equals("february") || month.equals("Feb.") || month.equals("feb") || month.equals("feb.") || month.equals("2") || month.equals("02"))
                    return 2;
                if (month.equals("Mar") || month.equals("Mar.") || month.equals("mar") || month.equals("mar.") || month.equals("March") || month.equals("march") || month.equals("3") || month.equals("03"))
                    return 3;
                if (month.equals("April") || month.equals("april")|| month.equals("Apr.")|| month.equals("Apr")|| month.equals("apr")|| month.equals("apr.") || month.equals("4") || month.equals("04"))
                    return 4;
                if (month.equals("May") || month.equals("may") || month.equals("5") || month.equals("05"))
                    return 5;
                if (month.equals("June") || month.equals("june") || month.equals("Jun.") || month.equals("Jun") || month.equals("jun") || month.equals("jun.") || month.equals("6") || month.equals("06"))
                    return 6;
                if (month.equals("Jul") || month.equals("Jul.") || month.equals("jul") || month.equals("jul.") || month.equals("July") || month.equals("july") || month.equals("7") || month.equals("07"))
                    return 7;
                if (month.equals("Aug") || month.equals("Aug.") || month.equals("aug") || month.equals("aug.") || month.equals("August") || month.equals("august") || month.equals("8") || month.equals("08"))
                    return 8;
                if (month.equals("September") || month.equals("september") || month.equals("Sept") || month.equals("Sept.") || month.equals("sept") || month.equals("sept.") || month.equals("9") || month.equals("09"))
                    return 9;
                if (month.equals("Oct") || month.equals("Oct.") || month.equals("oct") || month.equals("oct.") || month.equals("October") || month.equals("october") || month.equals("10"))
                    return 10;
                if (month.equals("November") || month.equals("november") || month.equals("Nov.") || month.equals("Nov") || month.equals("nov.") ||month.equals("nov") || month.equals("11"))
                    return 11;
                if (month.equals("Dec") || month.equals("Dec.") || month.equals("dec") || month.equals("dec.") || month.equals("December") || month.equals("december") || month.equals("12"))
                    return 12;
                return 0;
                }
        });
    }
}
