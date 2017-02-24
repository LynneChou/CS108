package edu.stanford.cs108.cityinformation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
    }

    public void onAdd(View view) {
        EditText inputName = (EditText)findViewById(R.id.name);
        String name = inputName.getText().toString();
        EditText inputContinent = (EditText)findViewById(R.id.continent);
        String continent = inputContinent.getText().toString();
        EditText inputPopulation = (EditText)findViewById(R.id.population);
        String population = inputPopulation.getText().toString();

        if(!name.isEmpty() && !continent.isEmpty() && !population.isEmpty()) {
            String insertData = "INSERT INTO cities VALUES (\'" + name + "\', \'" + continent + "\', " + population + ", NULL);";
            System.err.println(insertData);
            db.execSQL(insertData);

            Context context = getApplicationContext();
            Toast.makeText(context, name + " Added !", Toast.LENGTH_SHORT).show();
        }
    }
}
