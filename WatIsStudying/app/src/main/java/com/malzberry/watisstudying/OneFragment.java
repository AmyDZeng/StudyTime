package com.malzberry.watisstudying;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

// import info.androidhive.materialtabs.R;


public class OneFragment extends Fragment implements AsyncResponse{

    protected RecyclerView mRecyclerView;
    protected RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String url = "";
        AsyncTask asyncTask = new ParseItemInfo(this);
        asyncTask.execute(new String[]{url});

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }
    /*
    {id: String,
        coursePrefix: String,
        courseNumber: Number,
        location: String,
        checkinAmount : Number}
     */
    ArrayList<String> courses = new ArrayList<String>();
    ArrayList<String> ids = new ArrayList<String>();
    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<Integer> checkIns = new ArrayList<Integer>();

    String[] temp;

    public void processFinish(ArrayList<String> output){
        if(output.get(0) == "ERROR"){
            Toast.makeText(this.getActivity(), output.get(1).toString(), Toast.LENGTH_LONG).show();
            return;
        }
        // TODO: start adapter and list here with new info
        for(int i = 0; i < output.size(); i++){
            temp = output.get(i).split("\\s*,\\s*");

            ids.add(temp[0]);
            courses.add(temp[1] + " " + temp[2]);
            locations.add(temp[3]);
            checkIns.add(Integer.parseInt(temp[4]));
        }
        mAdapter = new MyAdapter(this.getActivity(), ids, courses, locations, checkIns);

        mRecyclerView.setAdapter(mAdapter);
    }

}