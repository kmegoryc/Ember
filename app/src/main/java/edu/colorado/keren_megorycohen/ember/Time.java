package edu.colorado.keren_megorycohen.ember;

/**
 * Created by kerenmegory-cohen on 4/25/17.
 */

public class Time {
    private int timeSinceLast;

    public int getTimeSinceLast() {
        return timeSinceLast;
    }
    public void setTimeSinceLast(int new_time) {
        this.timeSinceLast = new_time;
    }
    public static final Time time = new Time();
}
