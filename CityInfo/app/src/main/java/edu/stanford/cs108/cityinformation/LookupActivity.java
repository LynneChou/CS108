package edu.stanford.cs108.cityinformation;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.database.Cursor;

public class LookupActivity extends AppCompatActivity {

    SQLiteDatabase db;
    String[] fromArray = {"name","continent","population"};
    int[] toArray = {R.id.custom_name,R.id.custom_continent,R.id.custom_population};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);

        db = openOrCreateDatabase("MyDB",MODE_PRIVATE,null);

        Cursor tablesCursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name='cities';", null);
    }

    public void onSearch(View view) {
        EditText inputName = (EditText)findViewById(R.id.name);
        String name = inputName.getText().toString();
        EditText inputContinent = (EditText)findViewById(R.id.continent);
        String continent = inputContinent.getText().toString();
        EditText inputPopulation = (EditText)findViewById(R.id.population);
        String population = inputPopulation.getText().toString();

        String query = "";
        if(!name.equals("")) {
            query += " AND name LIKE \'%" + name + "%\'";
        }
        if(!continent.equals("")) {
            query += " AND continent LIKE \'%" + continent + "%\'";
        }
        if(!population.equals("")) {
            // need to know which button is checked
            RadioButton buttonGeq = (RadioButton)findViewById(R.id.geq);
            RadioButton buttonLess = (RadioButton) findViewById(R.id.less);
            if(buttonGeq.isChecked()) {
                query += " AND population >= " + population;
            } else {
                query += " AND population < " + population;
            }
        }

        if(!name.isEmpty() || !continent.isEmpty() || !population.isEmpty()) {
            query = query.substring(4);
            query = " WHERE" + query;
        }
        query = "SELECT * FROM cities" + query;
        query += ";";

        System.err.println(query);
        Cursor cursor = db.rawQuery(query,null);

        ListAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.custom_adapter, cursor, fromArray, toArray, 0);

        ListView listView = (ListView) findViewById(R.id.results);
        listView.setAdapter(adapter);

    }
}
