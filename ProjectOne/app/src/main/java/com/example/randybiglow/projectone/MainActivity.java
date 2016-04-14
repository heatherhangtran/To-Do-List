package com.example.randybiglow.projectone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList<String> mStringList;
    ArrayAdapter<String> mAdapter;
    EditText mEditText;
    Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add a default list
        mStringList = new LinkedList<>();
        mStringList.add("Pay bills");
        mStringList.add("Groceries");
        mStringList.add("Laundry");

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);

        ListView listName = (ListView) (findViewById(R.id.masterListView));
        listName.setAdapter(mAdapter);
        mEditText = (EditText) (findViewById(R.id.masterEditText));
        mAddButton = (Button) (findViewById(R.id.addButton));

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditText.getText().toString();
                Toast.makeText(MainActivity.this, "Hold to delete", Toast.LENGTH_LONG).show();
                if (input.length() > 0) {
                    mStringList.add(input);
                    mAdapter.notifyDataSetChanged();
                    mEditText.setText("");
                }

                //THIS IS AWESOME! This will hide the key board after user click on the add button. *Happy Dance*
                try {
                    InputMethodManager arbitraryName = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    arbitraryName.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                catch (Exception e) {
                }
            }
        });

        //Setting onItemLongClick code to remove list items.
        listName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mStringList.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
