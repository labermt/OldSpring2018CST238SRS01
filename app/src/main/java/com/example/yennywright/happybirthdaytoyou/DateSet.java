package com.example.yennywright.happybirthdaytoyou;

public class DateSet {
    private String birthMonth;
    private int birthDay;

    //constructor
    public DateSet (String inMonth, int inDay){
        birthMonth = inMonth;
        birthDay = inDay;
    }

    public boolean equals(DateSet inDateSet)
    {
        boolean result = false;
        if((inDateSet.birthDay == birthDay) &&
                (inDateSet.birthMonth.toLowerCase().equals(birthMonth.toLowerCase())))
        {
            result = true;
        }

        return result;
    }
}
