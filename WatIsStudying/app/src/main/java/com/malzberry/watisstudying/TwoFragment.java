package com.malzberry.watisstudying;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

// import info.androidhive.materialtabs.R;


public class TwoFragment extends OneFragment{

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void processFinish(ArrayList<String> output){
        if(output.get(0) == "ERROR"){
            Toast.makeText(this.getActivity(), output.get(1).toString(), Toast.LENGTH_LONG).show();
            return;
        }

        for(int i = 0; i < output.size(); i++){
            temp = output.get(i).split("\\s*,\\s*");

            ids.add(temp[0]);
            courses.add(temp[1] + " " + temp[2]);
            locations.add(temp[3]);
            checkIns.add(Integer.parseInt(temp[4]));
        }
        // TODO: SORT BASED ON CHECKINS



        mAdapter = new MyAdapter(this.getActivity(), ids, courses, locations, checkIns);

        mRecyclerView.setAdapter(mAdapter);
    }

    public void sort(ArrayList<String> arr){
        ArrayList<Integer> iArr = new ArrayList<Integer>();
        // translate to ints
        for(int i = 0; i < arr.size(); i++){
            iArr.add(Integer
                    .parseInt(arr.get(i)
                            .substring(arr.get(i)
                                    .lastIndexOf(","))));
        }



    }
}