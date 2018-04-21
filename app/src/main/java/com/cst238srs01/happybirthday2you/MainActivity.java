package com.cst238srs01.happybirthday2you;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<String> NameList;
    public static List<String> DateList;
    public static int Entries = 0;
    public static String CurrentMonth = null;
    public static String Day = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        final Button myButton = findViewById(R.id.button);
        final EditText myTextBox = findViewById(R.id.TextFieldFName);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        final Spinner daySpinner = findViewById(R.id.spinnerDay);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Day = daySpinner.getSelectedItem().toString();
                myButton.callOnClick();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        if (imm != null) {
            // only will trigger it if no physical keyboard is open
            imm.showSoftInput(myTextBox, 0);
        }
        myTextBox.addTextChangedListener(new TextWatcher() {
        //see TextValidator class for location of TextValidator source
            public void afterTextChanged(Editable s) {
                myTextBox.addTextChangedListener(new TextValidator(myTextBox) {
                    @Override public void validate(TextView textView, String text) {
                        if(text == null)
                        {   textView.setError("Empty");   }
                        else
                        {
                            text.trim();
                            if(!text.isEmpty())
                            {
                                textView.setError(null);
                            }
                            else
                            {
                                textView.setError("Empty Spaces Detected");
                            }
                        }

                    }
                });
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }
    /** Called when the user touches the button */
    public void StoreAndCompare(View view) {
       if(CurrentMonth != null) {
            final Context context = getApplicationContext();
            CharSequence text = "Submit Clicked";
            final int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            EditText FirstNameText = findViewById(R.id.TextFieldFName);
            String FirstNameString = FirstNameText.getText().toString();
            EditText LastNameText = findViewById(R.id.TextFieldLName);
            String LastName = LastNameText.getText().toString();
            RadioGroup myRadioGroup = findViewById(R.id.radioGroup);
            Spinner mySpinner = findViewById(R.id.spinnerDay);
            String CompleteName = FirstNameString + " " + LastName;
            String Date = CurrentMonth + " " + Day;

           if(DateList != null)
            {if ( DateList.contains(Date)) {
                text = CompleteName+ " has the same birthday as " + NameList.get(DateList.indexOf(Date)).toString();
                toast = Toast.makeText(this, text , duration);
                toast.setGravity(Gravity.TOP| Gravity.LEFT, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);  //lets me change the location of toast
                toast.show();
                ClearData();
                Entries = 0;
            } else {
                toast = Toast.makeText(context,"Added To List " + Date,duration);
                toast.show();
                NameList.add(CompleteName);
                DateList.add(Date);
                writeToFile("Names", CompleteName);
                writeToFile("Dates", Date);
                Entries++;
                ResetForm();
            }}
            else
            {
                // Lists are empty can't check against empty lists
                NameList = new ArrayList<>();
                DateList = new ArrayList<>();
                NameList.add(CompleteName);
                DateList.add(Date);
                writeToFile("Names", CompleteName);
                writeToFile("Dates", Date);
                Entries++;

                ResetForm();
            }
        }
        else
       {
           Context context = getApplicationContext();
           CharSequence text = "Choose a Month";
           int duration = Toast.LENGTH_SHORT;
           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
           ResetForm();
       }
    }

public void writeToFile(String filename, String fileContents)
{
    FileOutputStream outputStream;
    try {
        outputStream = openFileOutput(filename, Context.MODE_APPEND);
        outputStream.write(fileContents.getBytes());
        outputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void ClearData()
{
    NameList.clear();
    DateList.clear();
    ResetForm();
}
public void ResetForm()
{

    Spinner mySpinner = findViewById(R.id.spinnerDay);
    mySpinner.setSelection(0, true);
    TextView FirstNameText = findViewById(R.id.textViewFName);
    TextView LastNameText = findViewById(R.id.textViewLName);
    RadioGroup myRadioGroup = findViewById(R.id.radioGroup);
    FirstNameText.setText("");
    LastNameText.setText("");
    myRadioGroup.clearCheck();
    FirstNameText.requestFocus();
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
