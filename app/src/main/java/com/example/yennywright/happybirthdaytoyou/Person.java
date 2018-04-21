package com.example.yennywright.happybirthdaytoyou;

public class Person {
    private String userName;
    private DateSet userBirthday;

    public String getUserName(){
        return userName;
    }

    public DateSet getUserBirthday() {
        return userBirthday;
    }

    //constructor
    public Person (String inName, DateSet inDate){
        userName = inName;
        userBirthday = inDate;
    }

}
