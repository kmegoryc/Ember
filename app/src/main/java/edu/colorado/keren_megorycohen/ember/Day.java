package edu.colorado.keren_megorycohen.ember;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kerenmegory-cohen on 4/24/17.
 */

public class Day {

    //create a public ArrayList consisting of Day objects
    public static final ArrayList<Day> alldata = new ArrayList<Day>();

    //user-specific data
    private int limit;
    private int smoked;
    private int remaining;

    //date
    private int day_of_year;
    private int day_of_month;
    private int month;
    private int year;

    public Day () {
        //default constructor required for calls to DataSnapshot.getValue(Day.class)
    }

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

    //used when writing to the database
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("limit", limit);
        result.put("smoked", smoked);
        result.put("remaining", remaining);
        result.put("dayofyear", day_of_year);
        result.put("dayofmonth", day_of_month);
        result.put("month", month);
        result.put("year", year);
        return result;
    }

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
