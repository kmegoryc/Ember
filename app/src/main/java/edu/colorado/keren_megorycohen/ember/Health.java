package edu.colorado.keren_megorycohen.ember;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import static edu.colorado.keren_megorycohen.ember.Time.time;

public class Health extends Fragment {


    public Health() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        int calc_time = time.getTimeSinceLast();
        Log.d("time", String.valueOf(calc_time));

        //define all the progress bars
        ProgressBar nicotine = (ProgressBar) view.findViewById(R.id.progress_nicotine);
        ProgressBar coronary_arteries = (ProgressBar) view.findViewById(R.id.progress_coronary_arteries);
        ProgressBar heart_attack = (ProgressBar) view.findViewById(R.id.progress_heart_attack);
        ProgressBar lung_cancer = (ProgressBar) view.findViewById(R.id.progress_lung_cancer);
        ProgressBar lung_capacity = (ProgressBar) view.findViewById(R.id.progress_lung_capacity);
        ProgressBar stroke = (ProgressBar) view.findViewById(R.id.progress_stroke);
        ProgressBar lung_hairs = (ProgressBar) view.findViewById(R.id.progress_lung_hairs);

        //nicotine
        int oneWeek = 604800;
        float result1 = (float) calc_time/oneWeek;
        float finalresult1 = result1*100;
        nicotine.setProgress((int) finalresult1);

        //lung capacity
        int threeMonths = 7776000;
        float result2 = (float) calc_time/threeMonths;
        float finalresult2 = result2*100;
        lung_capacity.setProgress((int)finalresult2);

        //lung hairs
        int nineMonths = 23328000;
        float result3 = (float) calc_time/nineMonths;
        float finalresult3 = result3*100;
        lung_hairs.setProgress((int)finalresult3);

        //coronary arteries
        int oneYear = 31104000;
        float result4 = (float) calc_time/oneYear;
        float finalresult4 = result4*100;
        coronary_arteries.setProgress((int)finalresult4);

        //heart attack
        int twoYears = 62208000;
        float result5 = (float) calc_time/twoYears;
        float finalresult5 = result5*100;
        heart_attack.setProgress((int)finalresult5);

        //stroke
        int fiveYears = 155520000;
        float result6 = (float) calc_time/fiveYears;
        float finalresult6 = result6*100;
        stroke.setProgress((int)finalresult6);

        //lung cancer
        int tenYears = 311040000;
        float result7 = (float) calc_time/tenYears;
        float finalresult7 = result7*100;
        lung_cancer.setProgress((int)finalresult7);
    }

}
