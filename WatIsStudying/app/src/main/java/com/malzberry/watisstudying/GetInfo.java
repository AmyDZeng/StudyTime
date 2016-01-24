package com.malzberry.watisstudying;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Malzberry on 1/23/2016.
 */
public class GetInfo extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    Spinner spinner;
    Button submitBtn;
    TextView subject, courseNum, room, desc;
    AsyncTask asyncTask = new PostJSON(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Broadcasting");
        spinner = (Spinner) findViewById(R.id.courses);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("CS");
        categories.add("MATH");
        categories.add("PSYCH");
        categories.add("ART");
        categories.add("SCI");
        categories.add("BUS");


        //subject = (TextView) findViewById(R.id.et_subject);
        courseNum = (TextView) findViewById(R.id.et_course_num);
        room = (TextView) findViewById(R.id.et_room);
        desc = (TextView) findViewById(R.id.et_desc);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        submitBtn = (Button) findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asyncTask.execute(new String[]{spinner.getSelectedItem().toString(),
                        courseNum.getText().toString(),
                        room.getText().toString(),
                        desc.getText().toString()});
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void processFinish(ArrayList<String> output){
        if(output.size() > 2 && output.get(0) == "ERROR"){
            Toast.makeText(this, output.get(1).toString(), Toast.LENGTH_LONG).show();

            finish();
            return;
        }
        MainActivity.broadcasting = true;
        finish();
    }

}
