package com.example.shant.happybirthday2you;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class Person {
    public String Name;
    public Integer Month;
    public Integer Day;

    public Person(String name, Integer month, Integer day ) {
        this.Name = name;
        this.Month = month;
        this.Day = day;
    }
}

public class MainActivity extends AppCompatActivity {

    private EditText m_editTextName;
    private EditText m_editTextDay;
    private EditText m_editTextMonth;
    List<Person> People = new ArrayList<Person>();

    class RequiredTextValidator extends TextValidator {
        RequiredTextValidator(TextView textView) {
            super(textView);
        }


        @Override
        public void validate(TextView textView, String text) {
            boolean validstringname = false;
            if (text != null) {
                String trimmedString = text.trim();
                if (trimmedString != null && !trimmedString.isEmpty()) {
                    validstringname = true;
                }
            }

            if (!validstringname) {
                textView.setError("Required!");
            }
        }
    }

    class RequiredDayValidator extends RequiredTextValidator {
        private int lowerbound = 1;
        private int upperbound = 31;

        RequiredDayValidator(TextView textView, int lowerbound, int upperbound) {
            super(textView);
            this.lowerbound = lowerbound;
            this.upperbound = upperbound;
        }

        @Override
        public void validate(TextView textView, String text) {
            super.validate(textView, text);
            String month = m_editTextMonth.getText().toString();
            String day = m_editTextDay.getText().toString();
            boolean validstringday = false;
            boolean rangevalid = false;
            if (isValidString(text)) {
                validstringday = true;
                if (isRangeValid(text, lowerbound, upperbound) && isValidDate(month, day)) {
                    rangevalid = true;
                }
                if (!rangevalid) {
                    m_editTextDay.setError("Out of Range!");
                }
            }
            if (!validstringday) {
                m_editTextDay.setError("Required!");
            }
        }
    }

    class RequiredMonthValidator extends RequiredTextValidator {
        private int lowerbound = 1;
        private int upperbound = 12;

        RequiredMonthValidator(TextView textView, int lowerbound, int upperbound) {
            super(textView);
            this.lowerbound = lowerbound;
            this.upperbound = upperbound;
        }

        @Override
        public void validate(TextView textView, String text) {
            super.validate(textView, text);
            boolean validstringmonth = false;
            boolean rangevalid = false;
            if (isValidString(text)) {
                validstringmonth = true;
                if (isRangeValid(text, lowerbound, upperbound)) {
                    rangevalid = true;
                }
                if (!rangevalid) {
                    m_editTextMonth.setError("Out of Range!");
                }
            }
            if (!validstringmonth) {
                m_editTextMonth.setError("Required month!");
            }
        }
    }

    private boolean isValidString(String text) {
        if (text != null) {
            String trimmedString = text.trim();
            if (trimmedString != null && !trimmedString.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean isRangeValid(String text, int lowerbound, int upperbound) {
        int myNum = Integer.parseInt(text);
        if (myNum >= lowerbound && myNum <= upperbound) {
            return true;
        }
        return false;
    }

    private boolean isValidDate(String month, String day) {
        boolean pass = true;
        Integer intDay = Integer.parseInt(day);
        if (month.equals("2") || month.equals("02")) {
            if (intDay > 28)
                pass = false;
        } else if (month.equals("4") || month.equals("04") || month.equals("6") ||
                month.equals("06") || month.equals("9") || month.equals("09") || month.equals("11")) {
            if (intDay > 30)
                pass = false;
        }
        return pass;
    }

    private boolean matchFound(Person p) {
        for (Person person : People) {
            if ((person != p) && person.Month.equals(p.Month) && person.Day.equals(p.Day)) {
                return true;
            }
        }
        return false;
    }

    private Person getMatch(Person p) {
        for (Person person : People) {
            if ((person != p) && person.Month.equals(p.Month) && person.Day.equals(p.Day)) {
                return person;
            }
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_editTextName = findViewById(R.id.editTextName);
        m_editTextDay = findViewById(R.id.editTextDay);
        m_editTextMonth = findViewById(R.id.editTextMonth);

        m_editTextName.addTextChangedListener(new RequiredTextValidator(m_editTextName));
        m_editTextDay.addTextChangedListener(new RequiredDayValidator(m_editTextDay, 1, 31));
        m_editTextMonth.addTextChangedListener(new RequiredMonthValidator(m_editTextMonth, 1, 12));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View view) {
                                                             String name = m_editTextName.getText().toString();
                                                             String month = m_editTextMonth.getText().toString();
                                                             String day = m_editTextDay.getText().toString();
                                                             if (!isValidString(month) || !isValidString(day) || !isValidString(name)) {
                                                                 Context context = getApplicationContext();
                                                                 CharSequence text = "All fields must have an entry!";
                                                                 int duration = Toast.LENGTH_LONG;

                                                                 Toast toast = Toast.makeText(context, text, duration);
                                                                 toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                                                                 toast.show();
                                                             }
                                                             else if (!isRangeValid(month, 1, 12)) {
                                                                 Context context = getApplicationContext();
                                                                 CharSequence text = "Month must be between 1 and 12!";
                                                                 int duration = Toast.LENGTH_LONG;

                                                                 Toast toast = Toast.makeText(context, text, duration);
                                                                 toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                                                                 toast.show();
                                                             }
                                                             else if (!isRangeValid(day, 1, 31)) {
                                                                 Context context = getApplicationContext();
                                                                 CharSequence text = "Day must be between 1 and 31!";
                                                                 int duration = Toast.LENGTH_LONG;

                                                                 Toast toast = Toast.makeText(context, text, duration);
                                                                 toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                                                                 toast.show();
                                                             }
                                                             else if (!isValidDate(month, day)) {
                                                                 Context context = getApplicationContext();
                                                                 CharSequence text = "Date is not possible for given month!";
                                                                 int duration = Toast.LENGTH_LONG;

                                                                 Toast toast = Toast.makeText(context, text, duration);
                                                                 toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                                                                 toast.show();
                                                             }
                                                             else if (isValidDate(month, day) && isRangeValid(month, 1, 12) &&
                                                                     isRangeValid(day, 1, 31) && isValidString(name) && isValidString(month)
                                                                     && isValidString(day)) {
                                                                 Person newPerson = new Person(name, Integer.parseInt(month), Integer.parseInt(day));
                                                                 People.add(newPerson);
                                                                 m_editTextName.setText("");
                                                                 m_editTextMonth.setText("");
                                                                 m_editTextDay.setText("");
                                                                 Context context = getApplicationContext();
                                                                 if (matchFound(newPerson)) {
                                                                     Person match = getMatch(newPerson);
                                                                     CharSequence text = match.Name + " and " + newPerson.Name + " have the same birthday!\n"
                                                                             + "total birthdays: " + String.valueOf(People.size());
                                                                     int duration = Toast.LENGTH_LONG;
                                                                     Toast toast = Toast.makeText(context, text, duration);
                                                                     toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                                                                     toast.show();
                                                                     People.clear();
                                                                 } else {
                                                                     CharSequence text = "Birthday successfully entered!\n"
                                                                             + "total birthdays: " + String.valueOf(People.size());
                                                                     int duration = Toast.LENGTH_LONG;

                                                                     Toast toast = Toast.makeText(context, text, duration);
                                                                     toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                                                                     toast.show();
                                                                 }
                                                             }
                                                         }
                                                     }
        );
    }
}
