package com.example.andrewdoser.cst238srs01;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText mName;
    EditText mMM;
    EditText mDD;
    Button mButton;
    //public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = findViewById(R.id.editTextName);
        mMM = findViewById(R.id.editTextMonth);
        mDD = findViewById(R.id.editTextDay);
        mButton = findViewById(R.id.buttonSubmit);
        final PersonDbHelper myDB = new PersonDbHelper(this);

        mName.addTextChangedListener(new TextValidator(mName) {
            @Override public void validate(TextView textView, String text) {
                String name = mName.getText().toString();
                String tname = name.trim();
                if(tname.isEmpty())
                {
                    mName.setError("Please Enter a Name");
                }

            }
        });

        mMM.addTextChangedListener(new TextValidator(mMM) {
            @Override public void validate(TextView textView, String text) {
                String name = mMM.getText().toString();
                String tname = name.trim();
                if(tname.isEmpty())
                {
                    mMM.setError("Please Enter a Month");
                }
                else if(!tname.matches("[0-9]+"))
                {
                    mMM.setError("Please enter only numbers");
                }
                if(tname.matches("[0-9]+"))
                {
                    int iname = Integer.parseInt(tname);
                    if(iname <= 0 || iname > 12)
                    {
                        mMM.setError("Please Enter a Valid Month");
                    }
                }// iname>=1 && iname<13

            }
        });

        mDD.addTextChangedListener(new TextValidator(mDD){
            @Override public void validate(TextView textView, String Text) {
                String name = mDD.getText().toString();
                String tname = name.trim();

                if(tname.isEmpty())
                {
                    mDD.setError("Please Enter a Day");
                }
                else if(!tname.matches("[0-9]+"))
                {
                    mDD.setError("Please enter only numbers");
                }
                if(tname.matches("[0-9]+"))
                {
                    int iname = Integer.parseInt(tname);
                    if(iname <= 0 || iname >= 32)
                    {
                        mDD.setError("Please Enter a Valid Day");
                    }
                }
            }
        });

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(validationSuccess())
                        {
                            int month = Integer.parseInt(mMM.getText().toString().trim());
                            int day = Integer.parseInt(mDD.getText().toString().trim());
                            boolean isInserted = myDB.insertData(mName.getText().toString().trim(), mMM.getText().toString().trim(), mDD.getText().toString().trim());
                            if(isInserted == true)
                            {
                                CharSequence Sub = "Successfully Submitted information";
                                int duration = Toast.LENGTH_LONG;
                                Toast Tsub = Toast.makeText(getApplicationContext(), Sub, duration);
                                TextView TsubMessage = (TextView) Tsub.getView().findViewById(android.R.id.message);
                                Tsub.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                                TsubMessage.setTextColor(Color.rgb(0, 142, 0));
                                Tsub.show();
                            }
                            //if(WriteToFile())
                           // {
                                //count++;
                              /*  CharSequence Sub = "Successfully Submitted information";
                                int duration = Toast.LENGTH_LONG;
                                Toast Tsub = Toast.makeText(getApplicationContext(), Sub, duration);
                                TextView TsubMessage = (TextView) Tsub.getView().findViewById(android.R.id.message);
                                Tsub.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                                TsubMessage.setTextColor(Color.rgb(0, 142, 0));
                                Tsub.show();*/
                            //}
                            //if(ReadFromFile())
                            //{
                                /*CharSequence Test = Bday[0];
                                int dur = Toast.LENGTH_LONG;
                                Toast Ttest = Toast.makeText(getApplicationContext(), Test, dur);
                                TextView TtestMessage = (TextView) Ttest.getView().findViewById(android.R.id.message);
                                Ttest.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                                TtestMessage.setTextColor(Color.rgb(0, 142, 0));
                                Ttest.show();*/
                            //}
                        }


                    }
                }
        );


}

    private Boolean validationSuccess()
    {
        Context context = getApplicationContext();
       /* if(mName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Please enter name",0).show();
            return false;
        }*/
        String month = mMM.getText().toString().trim();
        String day = mDD.getText().toString().trim();
        CharSequence Feb = "February only has, at most, 29 days. Please change the Day field.";
        CharSequence Apr = "April only has, at most, 30 days. Please change the Day field.";
        CharSequence Jun = "June only has, at most, 30 days. Please change the Day field.";
        CharSequence Nov = "November only has, at most, 30 days. Please change the Day field.";
        CharSequence Sep = "September only has, at most, 30 days. Please change the Day field.";
        int duration = Toast.LENGTH_LONG;
        Toast Tfeb = Toast.makeText(context, Feb, duration);
        Toast Tapr = Toast.makeText(context, Apr, duration);
        Toast Tjun = Toast.makeText(context, Jun, duration);
        Toast Tnov = Toast.makeText(context, Nov, duration);
        Toast Tsep = Toast.makeText(context, Sep, duration);

        TextView TfebMessage = (TextView) Tfeb.getView().findViewById(android.R.id.message);
        TfebMessage.setTextColor(Color.RED);
        TextView TaprMessage = (TextView) Tapr.getView().findViewById(android.R.id.message);
        TaprMessage.setTextColor(Color.RED);
        TextView TjunMessage = (TextView) Tjun.getView().findViewById(android.R.id.message);
        TjunMessage.setTextColor(Color.RED);
        TextView TnovMessage = (TextView) Tnov.getView().findViewById(android.R.id.message);
        TnovMessage.setTextColor(Color.RED);
        TextView TsepMessage = (TextView) Tsep.getView().findViewById(android.R.id.message);
        TsepMessage.setTextColor(Color.RED);
        Tfeb.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        Tapr.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        Tjun.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        Tnov.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        Tsep.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            int imonth = Integer.parseInt(month);
            int iday = Integer.parseInt(day);

            switch(imonth)
            {
                case 2:
                    if(iday > 29)
                    {
                        Tfeb.show();
                        return false;
                    }
                    break;
                case 4:
                    if(iday > 30)
                    {
                        Tapr.show();
                        return false;
                    }
                    break;
                case 6:
                    if(iday > 30)
                    {
                        Tjun.show();
                        return false;
                    }
                    break;
                case 9:
                    if(iday > 30)
                    {
                        Tsep.show();
                        return false;
                    }
                    break;
                case 11:
                    if(iday > 30)
                    {
                        Tnov.show();
                        return false;
                    }
                default:
                    return true;
            }


        return true;
    }

}

