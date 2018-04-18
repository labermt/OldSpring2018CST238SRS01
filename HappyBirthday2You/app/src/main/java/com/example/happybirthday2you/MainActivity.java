package com.example.happybirthday2you;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class MainActivity extends AppCompatActivity
{
    public static final String FINISH_MESSAGE = "com.example.happybirthday2you.MESSAGE";

    private Vector<String> names = new Vector<>();
    private Vector<String> months = new Vector<>();
    private Vector<String> days = new Vector<>();

    private TextView edit_name;
    private TextView edit_month;
    private TextView edit_day;
    private TextView edit_count;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = findViewById(R.id.editName);
        edit_month = findViewById(R.id.editMonth);
        edit_day = findViewById(R.id.editDay);
        edit_count = findViewById(R.id.editCount);
    }

    public void saveBirthday(View view)
    {
        if (edit_name.getText().toString().equalsIgnoreCase("") ||
                edit_month.getText().toString().equalsIgnoreCase("") ||
                edit_day.getText().toString().equalsIgnoreCase(""))
        {
            edit_name.setText("");
            edit_name.setHint("Make sure you enter your name");
            edit_month.setText("");
            edit_month.setHint("Make sure the name of month is valid");
            edit_day.setText("");
            edit_day.setHint("Make sure the day is valid");
        }
        else if (checkBirthday())
        {
            edit_name.setHint("Enter your name");
            edit_month.setHint("Enter name of month");
            edit_day.setHint("Enter day");

            String temp_month = edit_month.getText().toString().toLowerCase();
            String temp_day = edit_day.getText().toString();
            if (months == null || months.isEmpty())
            {
                months.add(temp_month);
                days.add(temp_day);
                names.add(edit_name.getText().toString());
                edit_name.setText("");
                edit_month.setText("");
                edit_day.setText("");
                edit_count.setText("Count: " + names.size());
            }
            else if (matchBirthday(temp_month, temp_day))
            {
                edit_count.setText("Count: " + names.size());
                String finish_str = (names.elementAt(months.indexOf(temp_month)) +
                        " and you(" +
                        edit_name.getText().toString() +
                        ") share the same birthday on " + temp_month + " " + temp_day + "!\nTotal Birthdays: " +
                        names.size());
                Intent intent = new Intent(this, FinishedActivity.class);
                intent.putExtra(FINISH_MESSAGE, finish_str);
                startActivity(intent);
            }
            else
            {
                months.add(temp_month);
                days.add(temp_day);
                names.add(edit_name.getText().toString());
                edit_name.setText("");
                edit_month.setText("");
                edit_day.setText("");
                edit_count.setText("Count: " + names.size());
            }
        }
        else
        {
            edit_name.setText("");
            edit_name.setHint("Make sure you enter your name");
            edit_month.setText("");
            edit_month.setHint("Make sure the name of month is valid");
            edit_day.setText("");
            edit_day.setHint("Make sure the day is valid");
        }
    }

    public boolean matchBirthday(String month, String day)
    {
        for (int i = 0; i < months.size(); i++)
        {
            if (months.elementAt(i).equalsIgnoreCase(month))
            {
                if(days.elementAt(i).equalsIgnoreCase(day))
                    return true;
            }
        }
        return false;
    }

    public boolean checkBirthday()
    {
        String temp_month = edit_month.getText().toString();
        int temp_day = Integer.parseInt(edit_day.getText().toString());
        if (temp_month.equalsIgnoreCase("January") ||
                temp_month.equalsIgnoreCase("March") ||
                temp_month.equalsIgnoreCase("May") ||
                temp_month.equalsIgnoreCase("July") ||
                temp_month.equalsIgnoreCase("August") ||
                temp_month.equalsIgnoreCase("October") ||
                temp_month.equalsIgnoreCase("December"))
        {
            if (temp_day <= 31 && temp_day >= 1)
                return true;
            else
                return false;
        }

        else if (temp_month.equalsIgnoreCase("April") ||
                temp_month.equalsIgnoreCase("June") ||
                temp_month.equalsIgnoreCase("September") ||
                temp_month.equalsIgnoreCase("November"))
        {
            if (temp_day <= 30 && temp_day >= 1)
                return true;
            else
                return false;
        }

        else if (temp_month.equalsIgnoreCase("February"))
        {
            if (temp_day <= 29 && temp_day >= 1)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
