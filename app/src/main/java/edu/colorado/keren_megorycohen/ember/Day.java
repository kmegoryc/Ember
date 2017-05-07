package edu.colorado.keren_megorycohen.ember;

import java.util.ArrayList;

/**
 * Created by kerenmegory-cohen on 4/24/17.
 */

public class Day {

    //user-specific data
    private int limit;
    private int smoked;
    private int remaining;

    //date
    private int day_of_year;
    private int day_of_month;
    private int month;
    private int year;

    //constructor
    public Day(int new_limit, int new_smoked, int new_day_of_year, int new_day_of_month, int new_month, int new_year){
        this.limit = new_limit;
        this.smoked = new_smoked;
        this.remaining = new_limit - new_smoked; //initialize to limit remaining (limit - smoked = remaining)

        this.day_of_year = new_day_of_year;
        this.day_of_month = new_day_of_month;
        this.month = new_month;
        this.year = new_year;
    }

    //create a public array of Day type elements (with test data)
    public static final ArrayList<Day> alldata = new ArrayList<Day>()
    {
        {
            add(new Day(20, 16, 109, 19, 4, 2017));
            add(new Day(20, 19, 110, 20, 4, 2017));
            add(new Day(20, 18, 111, 21, 4, 2017));
            add(new Day(20, 20, 112, 22, 4, 2017));
            add(new Day(20, 22, 113, 23, 4, 2017));
            add(new Day(20, 10, 114, 24, 4, 2017));
        }
    };

    public int getLimit() {
        return limit;
    }

    public int getSmoked() {
        return smoked;
    }

    public void updateSmoked() {
        this.smoked += 1;
    }

    public int getRemaining() {
        //update remaining
        this.remaining = this.limit - this.smoked;
        return remaining;
    }

    public int getDay_of_year() {
        return day_of_year;
    }

    public int getDay_of_month() {
        return day_of_month;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthString(){
        switch(month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Error";

        }

    }

    public int getYear() {
        return year;
    }
}
