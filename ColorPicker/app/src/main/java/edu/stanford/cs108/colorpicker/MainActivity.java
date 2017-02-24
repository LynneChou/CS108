package edu.stanford.cs108.colorpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.graphics.Color;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeColor(View view) {
        SeekBar sbR = (SeekBar) findViewById(R.id.red);
        SeekBar sbG = (SeekBar) findViewById(R.id.green);
        SeekBar sbB = (SeekBar) findViewById(R.id.blue);
        int red = sbR.getProgress();
        int green = sbG.getProgress();
        int blue = sbB.getProgress();

        // set background color
        View v = findViewById(R.id.background);
        v.setBackgroundColor(Color.rgb(red,green,blue));

        // set TextView
        String str = "Red " + red + ", Green " + green + ", Blue " + blue;
        TextView tv = (TextView) findViewById(R.id.rgb);
        tv.setText(str);
    }
}
