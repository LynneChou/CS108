package edu.stanford.cs108.mobiledraw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import static edu.stanford.cs108.mobiledraw.CustomView.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void update(View view) {
        if(updateId >= 0) {
            EditText et_x = (EditText) findViewById(R.id.x);
            String x = et_x.getText().toString();
            EditText et_y = (EditText) findViewById(R.id.y);
            String y = et_y.getText().toString();
            EditText et_w = (EditText) findViewById(R.id.width);
            String w = et_w.getText().toString();
            EditText et_h = (EditText) findViewById(R.id.height);
            String h = et_h.getText().toString();
            if(!x.isEmpty() && !y.isEmpty() && !w.isEmpty() && !h.isEmpty()) {
                if(curr_mode == 0 || curr_mode == 3) {
                    float fx = Float.parseFloat(x);
                    float fy = Float.parseFloat(y);
                    float fw = Float.parseFloat(w);
                    float fh = Float.parseFloat(h);
                    diams.get(updateId).left = fx;
                    diams.get(updateId).right = fx + fw;
                    diams.get(updateId).top = fy;
                    diams.get(updateId).bottom = fy + fh;
                    fromUpdate = true;
                    CustomView cv = (CustomView) findViewById(R.id.customview);
                    cv.invalidate();
                } else {
                    float fx = Float.parseFloat(x);
                    float fy = Float.parseFloat(y);
                    float fw = Float.parseFloat(w);
                    float fh = Float.parseFloat(h);
                    diams.remove(updateId);
                    shapes.remove(updateId);
                    left = fx;
                    right = fx + fw;
                    top = fy;
                    bottom = fy + fh;
                    CustomView cv = (CustomView) findViewById(R.id.customview);
                    cv.invalidate();
                }
            }
        }
    }
}
