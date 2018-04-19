package com.example.birthday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextWatcher{
    List<Person> people = new ArrayList<>();
    EditText name;
    EditText month;
    EditText day;
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name);
        month = findViewById(R.id.Month);
        day = findViewById(R.id.Day);
        year = findViewById(R.id.Year);
        name.addTextChangedListener(this);
        month.addTextChangedListener(this);
        year.addTextChangedListener(this);
        day.addTextChangedListener(this);
    }

    public void Validate(){
        if(!month.getText().toString().equals("")) {
            if (Integer.parseInt(month.getText().toString()) < 1 || (Integer.parseInt(month.getText().toString()) > 12)) {
                month.setError("Invalid month");
            }
        }
        if(!month.getText().toString().equals("") && !day.getText().toString().equals("")) {
            if ((Integer.parseInt(month.getText().toString()) == 1) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day");
            } else if ((Integer.parseInt(month.getText().toString()) == 2) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    (Integer.parseInt(day.getText().toString()) > 29)))
                day.setError("Invalid day.");

            else if (!year.getText().toString().equals("")) {
                if ((Integer.parseInt(month.getText().toString()) == 2) && (Integer.parseInt(day.getText().toString()) == 29 &&
                        Integer.parseInt(year.getText().toString()) % 4 != 0)) {
                    day.setError("Invalid day.");
                }
            } else if ((Integer.parseInt(month.getText().toString()) == 3) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 4) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 30)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 5) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 6) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 30)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 7) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 8) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 9) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 30)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 10) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 11) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 30)) {
                day.setError("Invalid day.");
            } else if ((Integer.parseInt(month.getText().toString()) == 12) && (Integer.parseInt(day.getText().toString()) < 1 ||
                    Integer.parseInt(day.getText().toString()) > 31)) {
                day.setError("Invalid day.");
            } else day.setError(null);

            if (!year.getText().toString().equals("")) {
                if ((Integer.parseInt(month.getText().toString()) == 2) && (Integer.parseInt(day.getText().toString()) == 29) &&
                        (Integer.parseInt(year.getText().toString()) % 4 == 0))
                    day.setError(null);
            }
        }
    }

    public Boolean isValid(){
        boolean emptyField = false;
        if( TextUtils.isEmpty(name.getText())){
            name.setError( "Name is required" );
            emptyField = true;
        }

        if( TextUtils.isEmpty(month.getText()))
        {
            month.setError( "Month is required" );
            emptyField = true;
        }
        if( TextUtils.isEmpty(day.getText()))
        {
            day.setError( "Day is required" );
            emptyField = true;
        }
        if( TextUtils.isEmpty(year.getText()))
        {
            year.setError( "Year is required" );
            emptyField = true;
        }

        if(emptyField)
            return false;

        if(Integer.parseInt(month.getText().toString()) < 1 || (Integer.parseInt(month.getText().toString()) > 12)) {
            return false;
        }


        if((Integer.parseInt(month.getText().toString()) == 1) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31)){
            return false;
        }

        else if((Integer.parseInt(month.getText().toString()) == 2) && (Integer.parseInt(day.getText().toString()) < 1 ||
                (Integer.parseInt(day.getText().toString()) > 29 || (Integer.parseInt(day.getText().toString()) > 28 &&
                        Integer.parseInt(year.getText().toString()) % 4 != 0)))){
            return false;
        }

        else if((Integer.parseInt(month.getText().toString()) == 3) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31)){
            return false;
        }

        else if((Integer.parseInt(month.getText().toString()) == 4) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 30)) {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 5) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 6) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 30))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 7) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 8) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 9) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 30))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 10) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 11) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 30))
        {
            return false;
        }
        else if((Integer.parseInt(month.getText().toString()) == 12) && (Integer.parseInt(day.getText().toString()) < 1 ||
                Integer.parseInt(day.getText().toString()) > 31))
        {
            return false;
        }
        else return true;
    }

    public void clear(View view){
        people.clear();
        TextView birthdays = findViewById(R.id.Birthdays);
        birthdays.setText("");
    }

    @Override
    public void afterTextChanged(Editable s){
        Validate();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void enter(View view) {
        TextView birthdays = findViewById(R.id.Birthdays);

            if(isValid()) {
            Person currentPerson = new Person(name.getText().toString(), Integer.parseInt(month.getText().toString()),
                    Integer.parseInt(day.getText().toString()), Integer.parseInt(year.getText().toString()));
            String birthday = "";
            Integer total = 0;
            for (Person person : people) {
                if (person.getDay().equals(currentPerson.getDay()) && person.getMonth().equals(currentPerson.getMonth())) {
                    birthday += person.getName() + ": " + person.getMonth() + "/" + person.getDay() + "/" + person.getYear() + "\n";
                    total += 1;
                }
            }
            String sharedBirthdays;
            if(total != 0){
                total += 1;
                sharedBirthdays = total.toString() + " people were born on " + currentPerson.getMonth() + "/" + currentPerson.getDay() + "\n";
            }
            else sharedBirthdays = "1 person was born on " + currentPerson.getMonth() + "/" + currentPerson.getDay() + "\n";
            people.add(currentPerson);
            birthday += currentPerson.getName() + ": " + currentPerson.getMonth() + "/" + currentPerson.getDay() + "/" + currentPerson.getYear();
            String birthdayText = sharedBirthdays + birthday;
            birthdays.setText(birthdayText);
        }
    }
}
