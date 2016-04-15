package com.example.randybiglow.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class Main2Activity extends AppCompatActivity {
    static LinkedList<String> m2StringList;
    static ArrayAdapter<String> m2Adapter;
    EditText m2EditText;
    TextView m2TextView;
    static Button m2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Add a default list of items.
        m2StringList = new LinkedList<>();
        m2StringList.add("Item 1");
        m2StringList.add("Item 2");
        m2StringList.add("Item 3");

        m2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, m2StringList);

        ListView list2Name = (ListView) (findViewById(R.id.secondListView));
        list2Name.setAdapter(m2Adapter);
        m2EditText = (EditText) (findViewById(R.id.secondEditText));
        m2Button = (Button) (findViewById(R.id.secondButton));
        m2TextView = (TextView) (findViewById(R.id.secondTextView));

        Intent oldIntent = getIntent();
        int data = oldIntent.getIntExtra("newList", 0);
        String newText = oldIntent.getStringExtra("name");
        m2TextView.setText(newText);
        Log.d("Main2", data + ""); //Still unsure as to how to read logs.

        m2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = m2EditText.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(Main2Activity.this, "Please enter text", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Main2Activity.this, "Hold to delete", Toast.LENGTH_LONG).show();
                    m2StringList.add(input);
                    m2Adapter.notifyDataSetChanged();
                    m2EditText.setText("");
                }

                try {
                    InputMethodManager arbitraryName = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    arbitraryName.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                } catch (Exception e) {
                }
            }
        });

        list2Name.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                m2StringList.remove(position);
                m2Adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
