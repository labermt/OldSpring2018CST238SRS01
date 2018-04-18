package com.example.andrewdoser.cst238srs01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText mName;
    EditText mMM;
    EditText mDD;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = findViewById(R.id.editTextName);
        mMM = findViewById(R.id.editTextMonth);
        mDD = findViewById(R.id.editTextDay);
        mButton = findViewById(R.id.buttonSubmit);

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
                    if(iname <= 0 || iname >= 31)
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

                        String month = mMM.getText().toString();
                        String day = mDD.getText().toString();
                    }
                }
        );


}
/*   Random random = new Random();
   int RanNum = random.nextInt(MaxNumber-MinNumber)+MinNumber;
   String StringEditNum= editTextNum.getText().toString();
    if(!StringEditNum.equals("")){
        int EditNum = Integer.parseInt(StringEditNum);
        if (EditNum >RanNum){
            textViewStatus.setText("Enter Higher Number");
        }else if(EditNum <RanNum){
            textViewStatus.setText("Enter Lower Numer!");
        }else {
            textViewStatus.setText("You won! You Nutz!");
        }
    } else{
            Toast.makeText(MainActivity.this,"Try again!",Toast.LENGTH_SHORT).show();
        }*/



}
