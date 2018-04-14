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
    private TextView finish_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = findViewById(R.id.editName);
        edit_month = findViewById(R.id.editMonth);
        edit_day = findViewById(R.id.editDay);
        finish_text = findViewById(R.id.finishText);
    }

    public void saveBirthday(View view)
    {
        if (checkBirthday())
        {
            edit_month.setHint("Enter month");
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
            }
            else if (matchBirthday(temp_month, temp_day))
            {
                String finish_str = (names.elementAt(months.indexOf(temp_month)) +
                        " and you(" +
                        edit_name.getText().toString() +
                        ") share the same birthday on " + temp_month + " " + temp_day + "!");
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
            }
        }
        else
        {
            edit_name.setText("");
            edit_month.setText("");
            edit_month.setHint("Make sure the month is valid");
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
        if (edit_month == null)
            return false;
        else if (edit_month.getText().toString().equalsIgnoreCase("January") ||
                edit_month.getText().toString().equalsIgnoreCase("March") ||
                edit_month.getText().toString().equalsIgnoreCase("May") ||
                edit_month.getText().toString().equalsIgnoreCase("July") ||
                edit_month.getText().toString().equalsIgnoreCase("August") ||
                edit_month.getText().toString().equalsIgnoreCase("October") ||
                edit_month.getText().toString().equalsIgnoreCase("December"))
        {
            if (Integer.parseInt(edit_day.getText().toString()) <= 31 &&  Integer.parseInt(edit_day.getText().toString()) >= 1)
                return true;
            else
                return false;
        }

        else if (edit_month.getText().toString().equalsIgnoreCase("April") ||
                edit_month.getText().toString().equalsIgnoreCase("June") ||
                edit_month.getText().toString().equalsIgnoreCase("September") ||
                edit_month.getText().toString().equalsIgnoreCase("November"))
        {
            if (Integer.parseInt(edit_day.getText().toString()) <= 30 &&  Integer.parseInt(edit_day.getText().toString()) >= 1)
                return true;
            else
                return false;
        }

        else if (edit_month.getText().toString().equalsIgnoreCase("February"))
        {
            if (Integer.parseInt(edit_day.getText().toString()) <= 29 &&  Integer.parseInt(edit_day.getText().toString()) >= 1)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
