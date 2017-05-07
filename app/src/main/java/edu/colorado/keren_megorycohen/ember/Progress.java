package edu.colorado.keren_megorycohen.ember;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import static edu.colorado.keren_megorycohen.ember.Day.alldata;


/**
 * A simple {@link Fragment} subclass.
 */
public class Progress extends Fragment {


    public Progress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        //set text
        TextView x_label = (TextView) view.findViewById(R.id.x_label);
        x_label.setText(String.valueOf(alldata.get(alldata.size()-1).getMonthString()));

        drawGraph(view);

    }

    public void drawGraph(View view) {

        //get length of array
        int data_len = alldata.size()-1;
        Log.d("length", String.valueOf(data_len));

        //get graph element
        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        /*//print last 7 days (debugging)
        Log.d("previous", String.valueOf(alldata.get(data_len-6).getSmoked()));
        Log.d("previous", String.valueOf(alldata.get(data_len-5).getSmoked()));
        Log.d("previous", String.valueOf(alldata.get(data_len-4).getSmoked()));
        Log.d("previous", String.valueOf(alldata.get(data_len-3).getSmoked()));
        Log.d("previous", String.valueOf(alldata.get(data_len-2).getSmoked()));
        Log.d("previous", String.valueOf(alldata.get(data_len-1).getSmoked()));
        Log.d("previous", String.valueOf(alldata.get(data_len).getSmoked()));*/

        /*LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(alldata.get(data_len-6).getDay_of_year(), alldata.get(data_len-6).getSmoked()),
                new DataPoint(alldata.get(data_len-5).getDay_of_year(), alldata.get(data_len-5).getSmoked()),
                new DataPoint(alldata.get(data_len-4).getDay_of_year(), alldata.get(data_len-4).getSmoked()),
                new DataPoint(alldata.get(data_len-3).getDay_of_year(), alldata.get(data_len-3).getSmoked()),
                new DataPoint(alldata.get(data_len-2).getDay_of_year(), alldata.get(data_len-2).getSmoked()),
                new DataPoint(alldata.get(data_len-1).getDay_of_year(), alldata.get(data_len-1).getSmoked()),
                new DataPoint(alldata.get(data_len).getDay_of_year(), alldata.get(data_len).getSmoked())
        });
        graph.addSeries(series);

        //draw
        graph.addSeries(series);
        graph.setBackgroundColor(0);
        series.setThickness(5);
        series.setColor(Color.rgb(237, 34, 93));*/
    }

}
