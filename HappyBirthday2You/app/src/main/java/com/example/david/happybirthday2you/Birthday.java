package com.example.david.happybirthday2you;

public class Birthday
{

    String name;
    int month;
    int day;

    public Birthday(String name, int month, int day)
    {
        this.name = name;
        this.month = month;
        this.day = day;
    }

    public Birthday(String savedData)
    {
        String[] data = savedData.split(" ");
        if(data.length == 3){
            name = data[0];
            month = new Integer(data[1]);
            day = new Integer(data[2]);
        } else {
            throw new IllegalArgumentException("data corrupted");
        }
    }

    public String getName() {  return name;  }
    public int getMonth() {  return month;  }
    public int getDay() {  return day;  }
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(name);
        s.append(" " + month);
        s.append(" " + day);

        return s.toString();
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof  Birthday)) return false;

        Birthday testBirthday = (Birthday)o;

        return testBirthday.getName() == this.getName();
    }

    public int hashcode()
    {
        return name.hashCode();
    }
}
