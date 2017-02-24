package edu.stanford.cs108.mobiledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.widget.RadioButton;
import java.util.*;

/**
 * Created by zhangrao on 2/20/17.
 */

public class CustomView extends View{
    protected static int curr_mode = 1; // select : 0,  rect : 1,   oval : 2,   erase : 3
    protected static int updateId = -1;
    protected static int idx0, idx3;
    protected static boolean fromUpdate = false;
    protected static float x1,y1,x2,y2,xx,yy;
    protected static float top, left, bottom, right, height, width;
    protected Paint myPaint1, myPaint2, mySelect1, mySelect2;
    protected static List<RectF> diams = new ArrayList<RectF>();
    protected static List<Boolean> shapes = new ArrayList<Boolean>();    // false : rect,    true : oval


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        myPaint1 = new Paint();
        myPaint1.setColor(Color.rgb(255,255,255));
        myPaint1.setStyle(Paint.Style.FILL);

        myPaint2 = new Paint();
        myPaint2.setColor(Color.rgb(140,21,21));
        myPaint2.setStyle(Paint.Style.STROKE);
        myPaint2.setStrokeWidth(5.0f);

        mySelect1 = new Paint();
        mySelect1.setColor(Color.rgb(255,255,255));
        mySelect1.setStyle(Paint.Style.FILL);

        mySelect2 = new Paint();
        mySelect2.setColor(Color.rgb(0,0,255));
        mySelect2.setStyle(Paint.Style.STROKE);
        mySelect2.setStrokeWidth(15.0f);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (curr_mode) {

            case 0:
                // select mode
                if(!fromUpdate) {
                    idx0 = detect();
                }
                fromUpdate = false;
                display(idx0);
                updateId = idx0;
                myDraw(canvas, idx0);
                break;
            case 1:
                // rect mode
                RectF rectF = new RectF(left, top, right, bottom);
                diams.add(rectF);
                shapes.add(false);
                updateId = diams.size() - 1;
                display(diams.size() - 1);
                myDraw(canvas, diams.size() - 1);
                break;

            case 2:
                // oval mode
                RectF rectO = new RectF(left, top, right, bottom);
                diams.add(rectO);
                shapes.add(true);
                updateId = diams.size() - 1;
                display(diams.size() - 1);
                myDraw(canvas, diams.size() - 1);
                break;

            case 3:
                // erase mode
                if(!fromUpdate) {
                    idx3 = detect();
                }
                fromUpdate = false;
                if(idx3 >= 0) {
                    diams.remove(idx3);
                    shapes.remove(idx3);
                }
                display(-1);
                myDraw(canvas, -1);
                break;
        }
    }


    private void display(int idx) {
        if(idx >= 0 && (curr_mode <= 2)) {
            height = diams.get(idx).bottom - diams.get(idx).top;
            width = diams.get(idx).right - diams.get(idx).left;
            EditText ev_x = (EditText) ((Activity) getContext()).findViewById(R.id.x);
            ev_x.setText(Float.toString(diams.get(idx).left));
            EditText ev_y = (EditText) ((Activity) getContext()).findViewById(R.id.y);
            ev_y.setText(Float.toString(diams.get(idx).top));
            EditText ev_h = (EditText) ((Activity) getContext()).findViewById(R.id.height);
            ev_h.setText(Float.toString(height));
            EditText ev_w = (EditText) ((Activity) getContext()).findViewById(R.id.width);
            ev_w.setText(Float.toString(width));
        } else {
            EditText ev_x = (EditText) ((Activity) getContext()).findViewById(R.id.x);
            ev_x.setText("");
            EditText ev_y = (EditText) ((Activity) getContext()).findViewById(R.id.y);
            ev_y.setText("");
            EditText ev_h = (EditText) ((Activity) getContext()).findViewById(R.id.height);
            ev_h.setText("");
            EditText ev_w = (EditText) ((Activity) getContext()).findViewById(R.id.width);
            ev_w.setText("");
        }
    }


    private int detect() {
        int idx = -1;
        for(int i = diams.size() - 1; i >= 0 ; i--) {
            RectF tempF = diams.get(i);
            if(tempF.top <= yy && yy <= tempF.bottom && tempF.left <= xx && xx <= tempF.right) {
                idx = i;
                break;
            }
        }
        return idx;
    }


    protected void myDraw(Canvas canvas, int index) {
        for(int i = 0; i < diams.size(); i++) {
            if(!shapes.get(i)) {     // rect
                if(i != index) {
                    canvas.drawRect(diams.get(i), myPaint1);
                    canvas.drawRect(diams.get(i), myPaint2);
                } else {
                    canvas.drawRect(diams.get(i), mySelect1);
                    canvas.drawRect(diams.get(i), mySelect2);
                }
            } else {                // oval
                if(i != index) {
                    canvas.drawOval(diams.get(i), myPaint1);
                    canvas.drawOval(diams.get(i), myPaint2);
                } else {
                    canvas.drawOval(diams.get(i), mySelect1);
                    canvas.drawOval(diams.get(i), mySelect2);
                }
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // check curr_mode
        RadioButton selectBtn = (RadioButton)((Activity)getContext()).findViewById(R.id.select);
        RadioButton rectBtn = (RadioButton)((Activity)getContext()).findViewById(R.id.rect);
        RadioButton ovalBtn = (RadioButton)((Activity)getContext()).findViewById(R.id.oval);
        RadioButton eraseBtn = (RadioButton)((Activity)getContext()).findViewById(R.id.erase);

        if(selectBtn.isChecked()) {
            curr_mode = 0;
        }
        if(rectBtn.isChecked()) {
            curr_mode = 1;
        }
        if(ovalBtn.isChecked()) {
            curr_mode = 2;
        }
        if(eraseBtn.isChecked()) {
            curr_mode = 3;
        }

        switch(curr_mode) {
            case 0: // select
            case 3: // erase
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xx = event.getX();
                        yy = event.getY();
                        invalidate();
                        break;
                }

                break;
            case 1: // rect
            case 2: // oval
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        if (x1 > x2) {
                            left = x2;
                            right = x1;
                        } else {
                            left = x1;
                            right = x2;
                        }

                        if (y1 > y2) {
                            top = y2;
                            bottom = y1;
                        } else {
                            top = y1;
                            bottom = y2;
                        }
                        invalidate();
                        break;
                }
        }
        return true;
    }
}