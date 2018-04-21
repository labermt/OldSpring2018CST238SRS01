package com.example.birthday;

public class Person {
    public Person(String name, Integer month, Integer day, Integer year){
        this.month = month;
        this.day = day;
        this.year = year;
        this.name = name;
    }
    private Integer month;
    private Integer day;
    private Integer year;
    private String name;

    public Integer getMonth(){
        return month;
    }

    public Integer getDay(){
        return day;
    }

    public Integer getYear(){
        return year;
    }

    public String getName(){
        return name;
    }
}
