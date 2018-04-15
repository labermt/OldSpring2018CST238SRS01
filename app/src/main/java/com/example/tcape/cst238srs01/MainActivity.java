package com.example.tcape.cst238srs01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText monthText;
    private EditText dayText;
    private TextView displayText;
    private TextView countText;
    private List<Person> People = new ArrayList<>();
    private boolean validName = false;
    private boolean validMonth = false;
    private boolean validDay = false;
    private String[] validMonths = new String[]{
            "January", "Jan", "1", "01", "February", "Feb", "2", "02",
            "March", "Mar", "3", "03", "April", "Apr", "4", "04",
            "May", "5", "05", "June", "Jun", "6", "06",
            "July", "Jul", "7", "07", "August", "Aug", "8", "08",
            "September", "Sep", "9", "09", "October", "Oct", "10",
            "November", "Nov", "11", "December", "Dec", "12"};

    private boolean isValidMonth(String month) {
        boolean pass = false;
        for(String months : validMonths) {
            if (month.equals(months))
                pass = true;
        }
        return pass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.editText_Name);
        monthText = findViewById(R.id.editText_Month);
        dayText = findViewById(R.id.editText_Day);
        displayText = findViewById(R.id.textView_Message);
        countText = findViewById(R.id.textView2);
        nameText.setSelectAllOnFocus(true);
        monthText.setSelectAllOnFocus(true);
        dayText.setSelectAllOnFocus(true);

        nameText.addTextChangedListener(new TextValidator(nameText) {
            @Override
            public void validate(TextView nameText, String name) {
                validName = (name.trim().length() != 0);
                if (!validName)
                    nameText.setError("Invalid Name");
            }
        });

        monthText.addTextChangedListener(new TextValidator(monthText) {
            @Override
            public void validate(TextView monthText, String month) {
                validMonth = isValidMonth(month);
                if (!validMonth)
                    monthText.setError("Invalid Month");
            }
        });

        dayText.addTextChangedListener(new TextValidator(dayText) {
            @Override
            public void validate(TextView dayText, String day) {
                Integer intDay;
                if(day.trim().length() != 0)
                    intDay = parseInt(day);
                else
                    intDay = 0;
                validDay = ((day.trim().length() != 0) && (intDay > 0 && intDay <= 31));
                if(!validDay)
                    dayText.setError("Invalid Day");
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String month = monthText.getText().toString();
                String dayStr = dayText.getText().toString();
                monthText.setError(null);
                dayText.setError(null);
                nameText.setError(null);
                displayText.setText(R.string.message);
                if (name.equals("")) {
                    nameText.setError("Invalid Name");
                    return;
                }
                if (month.equals("")) {
                    monthText.setError("Invalid Month");
                    return;
                }
                if (dayStr.equals("")){
                    dayText.setError("Invalid Day");
                    return;
                }
                if(!isValidDate(month, dayStr)){
                    monthText.setError("Invalid Date");
                    dayText.setError("Invalid Date");
                    return;
                }
                if(isValidDate(month, dayStr)){
                    Integer monthInt = monthToInteger(month);
                    Person newPerson = new Person(name, monthInt, parseInt(dayStr));
                    People.add(newPerson);
                    if(matchFound(newPerson)) {
                        Person match = getMatch(newPerson);
                        String display = match.Name + " and " + newPerson.Name + " have the same birthday!\n" +
                                "Total people: " + String.valueOf(People.size());
                        displayText.setText(display);
                        People.clear();
                    }
                    nameText.setText(null);
                    monthText.setText(null);
                    dayText.setText(null);
                    monthText.setError(null);
                    dayText.setError(null);
                    nameText.setError(null);
                    nameText.requestFocus();
                    countText.setText(String.valueOf(People.size()));
                }
            }

            private Person getMatch(Person p){
                for(Person person : People){
                    if((person != p) && person.Month.equals(p.Month) && person.Day.equals(p.Day)) {
                        return person;
                    }
                }
                return null;
            }

            private boolean isValidDate(String month, String day){
                boolean pass = true;
                Integer intDay = parseInt(day);
                if (month.equals("Feb") || month.equals("February") || month.equals("2") || month.equals("02")){
                    if (intDay > 29)
                        pass = false;
                }
                else if(month.equals("April") || month.equals("June") || month.equals("September") ||
                        month.equals("November") || month.equals("Apr") || month.equals("Jun") || month.equals("Sep") ||
                        month.equals("Nov") || month.equals("4") || month.equals("04") || month.equals("6") ||
                        month.equals("06") || month.equals("9") || month.equals("09") || month.equals("11")){
                    if (intDay > 30)
                        pass = false;
                }
                return pass;
            }

            private boolean matchFound(Person p){
                for(Person person : People)
                    if ((person != p) && person.Month.equals(p.Month) && person.Day.equals(p.Day))
                        return true;
                return false;
            }

            private Integer monthToInteger(String month){
                if (month.equals("Jan") || month.equals("January") || month.equals("1") || month.equals("01"))
                    return 1;
                if (month.equals("Feb") || month.equals("February") || month.equals("2") || month.equals("02"))
                    return 2;
                if (month.equals("Mar") || month.equals("March") || month.equals("3") || month.equals("03"))
                    return 3;
                if (month.equals("Apr") || month.equals("April") || month.equals("4") || month.equals("04"))
                    return 4;
                if (month.equals("May") || month.equals("5") || month.equals("05"))
                    return 5;
                if (month.equals("Jun") || month.equals("June") || month.equals("6") || month.equals("06"))
                    return 6;
                if (month.equals("Jul") || month.equals("July") || month.equals("7") || month.equals("07"))
                    return 7;
                if (month.equals("Aug") || month.equals("August") || month.equals("8") || month.equals("08"))
                    return 8;
                if (month.equals("Sep") || month.equals("September") || month.equals("9") || month.equals("09"))
                    return 9;
                if (month.equals("Oct") || month.equals("October")  || month.equals("10"))
                    return 10;
                if (month.equals("Nov") || month.equals("November")  || month.equals("11"))
                    return 11;
                if (month.equals("Dec") || month.equals("December")  || month.equals("12"))
                    return 12;
                return 0;
            }
        });
    }
}
