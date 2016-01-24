package com.malzberry.watisstudying;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// import info.androidhive.materialtabs.R;


public class ThreeFragment extends OneFragment{

    public ThreeFragment() {
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

        // TODO: SORT AND SEARCH BASED ON YOUR FAVORITED ONES
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), this.getActivity().MODE_PRIVATE);

        if( sharedPref.contains("favorites")){
            Set<String> defValues = new HashSet<String>();
            sharedPref.getStringSet("favorites", defValues);

            for(int i = 0; i < ids.size(); i++){
                if( !defValues.contains(courses.get(i)) ){
                    ids.remove(i);
                    courses.remove(i);
                    locations.remove(i);
                    checkIns.remove(i);
                }
            }
        } else{
            return;
        }

        mAdapter = new MyAdapter(this.getActivity(), ids, courses, locations, checkIns);

        mRecyclerView.setAdapter(mAdapter);
    }

}