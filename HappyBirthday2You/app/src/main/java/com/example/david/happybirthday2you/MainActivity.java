package com.example.david.happybirthday2you;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;











/**
        int maxDay = 31;
        switch( new Integer(month.getText().toString()) )
        {
            case(2):maxDay = 28; break;
            case(4):
            case(6):
            case(9):
            case(11):maxDay = 30; break;
        }
        return new Integer(day.getText().toString()) <= maxDay;
    }

    public boolean validateForm()
    {
        return validateDay() &&
                validateMonth() &&
                validateMonthDay() &&
                !nameExists() &&
                validateName();
    }


    private void saveForm()
    {
        List<Birthday> matches = birthdayMatches();

        if( !matches.isEmpty() )
        {
            // display birthday match message (birthday + names with that birthday)
        }

        birthdays.add(new Birthday(
                name.getText().toString(),
                new Integer(month.getText().toString()),
                new Integer(day.getText().toString()))
        );
    }

    private void clearForm()
    {

        name.setText("");
        month.setText("");
        day.setText("");
    }

    private List<Birthday> birthdayMatches()
    {
        List<Birthday> matches = new ArrayList<>();
        for(Birthday birthday: birthdays)
        {
            if(birthday.day == new Integer(this.day.getText().toString()) &&
                    birthday.month == new Integer(this.month.getText().toString()) )
            {
                matches.add(birthday);
            }
        }
        return matches;
    }

    private void saveToDisk()
    {
        String filename = "savedBirthdays";
        String fileContents = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromDisk()
    {

    }

    private void setWarrning(EditText field, boolean failedValidation)
    {
        if(failedValidation)
        {

        }
    }
}

**/