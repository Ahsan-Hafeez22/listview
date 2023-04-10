package com.example.listviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText person;
    SearchView search;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    ListView listView;
    ArrayList<String> arrNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        arrNames.add("Ahsan");
        arrNames.add("Asad");
        arrNames.add("Arsal");
        arrNames.add("Hammad");
        arrNames.add("Rizwan");
        arrNames.add("Arham");
        arrNames.add("Sarim");
/*
        listView.setVisibility(View.GONE);
*/
        settingUpListeners();
    }

    private void settingUpListeners() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrNames);
        listView.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
/*
                listView.setVisibility(View.VISIBLE);
*/
                adapter.getFilter().filter(s);

                return false;
            }
        });
        //add
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = person.getText().toString();
                if(!name.isEmpty()){
                    arrNames.add(name);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "New Entry Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //delete
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = person.getText().toString();
                if(!arrNames.isEmpty()){
                    if(!name.isEmpty() && arrNames.contains(name)){
                        arrNames.remove(name);
                        listView.setAdapter(adapter);
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "No entry found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //update
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = person.getText().toString();
                if(!arrNames.isEmpty()){
                    adapter.notifyDataSetChanged();
                    listView.invalidateViews();
                    listView.refreshDrawableState();
                }
            }
        });
        //search
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = person.getText().toString();
                if(!arrNames.isEmpty()){
                    if(!name.isEmpty() && arrNames.contains(name)){
                        Toast.makeText(MainActivity.this, "Found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "No entry found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initComponents() {
        person = findViewById(R.id.text1);
        search = findViewById(R.id.searchView);
        listView = findViewById(R.id.list_view_items);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
    }
}