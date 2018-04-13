package com.cst238srs01.happybirthday2you;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<String> NameList;
    public List<String> DateList;

    public String CurrentMonth = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText myTextBox = findViewById(R.id.TextFieldFName);

        myTextBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Context context = getApplicationContext();
                CharSequence text = "Hello! First Name Changed";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
    }
    /** Called when the user touches the button */
    public void StoreAndCompare(View view) {
       if(CurrentMonth != null) {
            Context context = getApplicationContext();
            CharSequence text = "Submit Clicked";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            EditText FirstNameText = findViewById(R.id.TextFieldFName);
            String FirstNameString = FirstNameText.getText().toString();
            String LastName = findViewById(R.id.TextFieldLName).toString();
            Spinner daySpinner = findViewById(R.id.spinnerDay);
            String CompleteName = FirstNameString + " " + LastName;
            String Date = CurrentMonth + " " + daySpinner.toString();
            if (DateList.contains(Date)) {
                text = CompleteName + " has the same birthday as " + NameList.get(DateList.indexOf(Date));
                Toast.makeText(context, text, duration);
                toast.show();
            } else {
                NameList.add(CompleteName);
                DateList.add(Date);
            }
        }
        else
       {
           Context context = getApplicationContext();
           CharSequence text = "Choose a Month";
           int duration = Toast.LENGTH_SHORT;
           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
       }
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonJan:
                if (checked) {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                        // Create an ArrayAdapter using the string array and a default spinner layout
                     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.JanDays, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
                }
                CurrentMonth = "Jan";
                    break;
            case R.id.radioButtonFeb:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.FebDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Feb";
                    break;
            case R.id.radioButtonMar:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.MarDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Mar";
                break;
            case R.id.radioButtonApr:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.AprDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Apr";
                break;
            case R.id.radioButtonMay:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.MayDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "May";
                break;
            case R.id.radioButtonJun:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.JunDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Jun";
                break;
            case R.id.radioButtonJul:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.JulDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Jul";
                break;
            case R.id.radioButtonAug:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.AugDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Aug";
                break;
            case R.id.radioButtonSep:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.SepDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Sep";
                break;
            case R.id.radioButtonOct:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.OctDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Oct";
                break;
            case R.id.radioButtonNov:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.NovDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Nov";
                break;
            case R.id.radioButton:
                if (checked)
                {
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                            R.array.DecDays, android.R.layout.simple_spinner_item);
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                }
                CurrentMonth = "Dec";
                break;

        }
    }
}
