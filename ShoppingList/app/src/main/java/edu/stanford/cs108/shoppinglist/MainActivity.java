package edu.stanford.cs108.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shoppingList  = new ArrayList<String>();
    }

    private ArrayList<String> shoppingList;

    public void addItem(View view) {
        // get info from EditText, add to List, set EditText empty string
        EditText et = (EditText) findViewById(R.id.newItem);
        final String text = et.getText().toString();
        et.setText("");
        shoppingList.add(text);
        // show info in the TextView
        String show = "";
        for(int i = 0; i < shoppingList.size(); i++) {
            show = show + shoppingList.get(i) + "\n";
        }
        TextView tv = (TextView) findViewById(R.id.showList);
        tv.setText(show);
    }

    public void clearList(View view) {
        // clear List, set TextView empty string
        shoppingList.clear();
        TextView tv = (TextView) findViewById(R.id.showList);
        tv.setText("");
    }
}
