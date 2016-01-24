package com.malzberry.watisstudying;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import it.sephiroth.android.library.picasso.Picasso;

//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Activity activity;
    private ArrayList<String> courses;
    private ArrayList<String> ids;
    private ArrayList<String> locations;
    private ArrayList<Integer> checkIns;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView txtCheckIns;

        public ViewHolder(View v) {
            super(v);
            // declare xml elements here
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtCheckIns = (TextView) v.findViewById(R.id.check_ins);
        }
    }
    // TODO: FINISH THESE?? add & remove
    public void add(int position, String course, String location){
        //rankings.add(position, rank);
        // add elements here
        courses.add(position, course);
        locations.add(position, location);

        notifyItemInserted(position);
    }

    public void remove(String course) {
        int position = courses.indexOf(course);
        // players.remove(position);
        courses.remove(position);
        locations.remove(position);

        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Activity activity, ArrayList<String> ids, ArrayList<String> courses, ArrayList<String> locations, ArrayList<Integer> checkIns) {
        this.activity = activity;
        this.ids = ids;
        this.courses = courses;
        this.locations = locations;
        this.checkIns = checkIns;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View v = LayoutInflater.from(activity).inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.txtHeader.setText(courses.get(position));
        holder.txtFooter.setText(locations.get(position));
        if(checkIns.get(position) == 1) {
            holder.txtCheckIns.setText(checkIns.get(position) + " Check-In");
        }else{
            holder.txtCheckIns.setText(checkIns.get(position) + " Check-Ins");
        }

        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to second page


                //Intent goToDisplay = new Intent(activity.getBaseContext(), DisplayInfo.class); // make display

                //activity.startActivity(goToDisplay);

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return courses.size();
    }

}


/*
package com.malzberry.watisstudying;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import it.sephiroth.android.library.picasso.Picasso;

//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Activity activity;
    private ArrayList<String> courses;
    private ArrayList<String> ids;
    private ArrayList<String> locations;
    private ArrayList<String> checkIns;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            // declare xml elements here
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
        }
    }

    public void add(int position, String course, String location){
        //rankings.add(position, rank);
        // add elements here
        courses.add(position, course);
        locations.add(position, location);

        notifyItemInserted(position);
    }

    public void remove(String course) {
        int position = courses.indexOf(course);
        // players.remove(position);
        courses.remove(position);
        locations.remove(position);

        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Activity activity, ArrayList<String> ids, ArrayList<String> courses, ArrayList<String> locations, ArrayList<String> checkIns) {
        this.activity = activity;
        this.ids = ids;
        this.courses = courses;
        this.locations = locations;
        this.checkIns = checkIns;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View v = LayoutInflater.from(activity).inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.txtHeader.setText(courses.get(position));
        holder.txtFooter.setText(locations.get(position));

        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to second page
                // Toast.makeText(v.getContext(), "click", Toast.LENGTH_LONG).show();


                Intent goToDisplay = new Intent(activity.getBaseContext(), DisplayInfo.class); // make display
                //goToDisplay.putExtra("player", name);
                activity.startActivity(goToDisplay);

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return courses.size();
    }

}

 */