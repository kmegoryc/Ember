package edu.colorado.keren_megorycohen.ember;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static edu.colorado.keren_megorycohen.ember.Day.alldata;
import static edu.colorado.keren_megorycohen.ember.Time.time;


public class Overview extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    //global calendar variable
    Calendar atLast = Calendar.getInstance();

    //check for current date
    Calendar rightNow = Calendar.getInstance();
    int day_of_year = rightNow.get(Calendar.DAY_OF_YEAR);
    int day_of_month = rightNow.get(Calendar.DAY_OF_MONTH);
    int month = rightNow.get(Calendar.MONTH) + 1;
    int year = rightNow.get(Calendar.YEAR);

    double packCost = 5.51;
    double packSize = 20;

    public Overview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //read from the database
        ValueEventListener firebaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //empty the arraylist
                alldata.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    //create new Day object
                    Day newDay = snapshot.getValue(Day.class);

                    //add newDay to our array
                    alldata.add(newDay);

                    Log.d("firebase", "Value is: " + String.valueOf(newDay.getDay_of_year()));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }

        };

        //add listener to our database reference
        ref.addValueEventListener(firebaseListener);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        //store today's data as new object
        Day today = new Day(20, 0, day_of_year, day_of_month, month, year);

        //there are previous days stored in alldata
        if (alldata.size() != 0){

            //if the current date has already been added
            if(alldata.get(alldata.size()-1).getDay_of_year() == day_of_year){
                Log.d("contains", "Today was already added to the array. Do nothing.");
                Log.d("contains", "Array size: " + String.valueOf(alldata.size()));
            }

            //if the current date is new
            else {
                Log.d("contains", "Today not yet added. Adding it to the array.");
                //add today to alldata array
                alldata.add(today);
                //add to Firebase
                ref.child(String.valueOf(day_of_year)).setValue(today);
                Log.d("contains", "Array size: " + String.valueOf(alldata.size()));
            }

        }

        //array is empty
        else {
            Log.d("contains", "Today not found, because the array is empty. Adding it to the array.");
            //add today to alldata array
            alldata.add(today);
            //add to Firebase
            ref.child(String.valueOf(day_of_year)).setValue(today);
            Log.d("contains", "Array size: " + String.valueOf(alldata.size()));
        }

        //print last item of array (today)
        Log.d("alldata", "Before add: " + String.valueOf(alldata.get(alldata.size()-1).getLimit()));
        Log.d("alldata", "Before add: " + String.valueOf(alldata.get(alldata.size()-1).getSmoked()));
        Log.d("alldata", "Before add: " + String.valueOf(alldata.get(alldata.size()-1).getRemaining()));

        Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /*final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Add Recipe");
                dialog.setCancelable(true);*/
                //call add cigarette function to update object
                addCigarette();
            }
        });

        updateAll(view);

        //set date
        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(String.valueOf(alldata.get(alldata.size()-1).getMonth()) + "/" + String.valueOf(alldata.get(alldata.size()-1).getDay_of_month()) + "/" + String.valueOf(alldata.get(alldata.size()-1).getYear()));

        //read data
        for (int i = alldata.size()-1; i >= 0; i--) {
            Log.d("alldata", "Value at index " + i + ": " + String.valueOf(alldata.get(i).getDay_of_month()) + "/" + String.valueOf(alldata.get(i).getMonthString()));
            Log.d("alldata", "Smoked at index " + i + ": " + String.valueOf(alldata.get(i).getSmoked()));
            Log.d("alldata", "Remaining at index " + i + ": " + String.valueOf(alldata.get(i).getRemaining()));
        }

    }

    public void updateAll(View view) {

        //update overview
        updateOverview(view);

        //update time since last
        calcAndUpdateTimeSinceLast(view);

        //update average daily intake
        calcAndUpdateAvgDailyIntake(view);

        //update money saved
        calcAndUpdateMoneySaved(view);

        //update progress bar
        calcAndUpdateProgressBar(view);
    }

    public void addCigarette() {

        View view = getView();

        //update smoked value
        alldata.get(alldata.size()-1).updateSmoked();

        /*//hashmap to update to firebase child
        HashMap<String, Object> updated = new HashMap<>();
        updated.put("smoked", alldata.get(alldata.size()-1).getSmoked());
        updated.put("remaining", alldata.get(alldata.size()-1).getRemaining());*/

        //update firebase
        ref.child(String.valueOf(day_of_year)).child("smoked").setValue(alldata.get(alldata.size()-1).getSmoked());
        ref.child(String.valueOf(day_of_year)).child("remaining").setValue(alldata.get(alldata.size()-1).getRemaining());

        //store current time (time at last cigarette)
        atLast = Calendar.getInstance();

        updateAll(view);

        //print last item of array (should be today)
        Log.d("alldata", "After add: " + String.valueOf(alldata.get(alldata.size()-1).getLimit()));
        Log.d("alldata", "After add: " + String.valueOf(alldata.get(alldata.size()-1).getSmoked()));
        Log.d("alldata", "After add: " + String.valueOf(alldata.get(alldata.size()-1).getRemaining()));
    }

    public void calcAndUpdateTimeSinceLast(View view) {

        //time since last cigarette = current time - time at last cigarette
        Calendar currentTime = Calendar.getInstance();
        long diff = currentTime.getTimeInMillis() - atLast.getTimeInMillis();

        long seconds = diff / 1000;
        long minutes = diff / (1000 * 60);
        long hours = diff / (1000 * 60 * 60);
        long days = diff / (24 * 60 * 60 * 1000);

        time.setTimeSinceLast((int) seconds);
        //time.setTimeSinceLast(604800);

        //set stats text
        TextView last_cigarette = (TextView) view.findViewById(R.id.last_cigarette);
        last_cigarette.setText("Last Cigarette\n" + String.valueOf(days) + "d " + String.valueOf(hours) + "h " + String.valueOf(minutes) + "m");
    }

    public void updateOverview(View view) {

        //define overview text
        TextView limit = (TextView) view.findViewById(R.id.limit);
        TextView smoked = (TextView) view.findViewById(R.id.smoked);
        TextView remaining = (TextView) view.findViewById(R.id.remaining);

        //set overview text
        limit.setText(String.valueOf(alldata.get(alldata.size()-1).getLimit()));
        smoked.setText(String.valueOf(alldata.get(alldata.size()-1).getSmoked()));
        remaining.setText(String.valueOf(alldata.get(alldata.size()-1).getRemaining()));
    }

    public void calcAndUpdateAvgDailyIntake(View view) {

        //average daily intake = day.smoked for the past 7 days
        int sum = 0;
        float avg = 0;
        int count = 0;
        for (int i = alldata.size()-1; i >= 0; i--){
            count+=1;
            sum = sum + alldata.get(i).getSmoked();
        }
        avg = sum/count;

        //set text
        TextView avg_daily_intake = (TextView) view.findViewById(R.id.avg_daily_intake);
        avg_daily_intake.setText("Average Daily Intake\n" + String.valueOf(avg));
    }

    public void calcAndUpdateMoneySaved(View view) {

        //money saved = alldata.remaining * (packcost/packsize)

        double remaining = 0;
        double saved = 0;
        for (int i = alldata.size()-1; i >= 0; i--){
            remaining = remaining + alldata.get(i).getRemaining();
        }
        saved = remaining * (packCost/packSize);

        //set text
        TextView money_saved = (TextView) view.findViewById(R.id.money_saved);
        money_saved.setText("Money Saved\n" + "$" + String.format("%.2g%n", saved));
    }

    public void calcAndUpdateProgressBar(View view) {

        //set progress bar
        ProgressBar progress = (ProgressBar) view.findViewById(R.id.progressBar);

        int smoked = alldata.get(alldata.size()-1).getSmoked();
        int limit = alldata.get(alldata.size()-1).getLimit();
        float result = (float) smoked/limit;
        float finalresult = result*100;
        Log.d("progress", String.valueOf(smoked));
        Log.d("progress", String.valueOf(limit));
        Log.d("progress", String.valueOf((float) smoked/limit));
        //calculate percentage
        progress.setProgress((int) finalresult);
    }

}
