package com.example.touchtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView pointerInfo,count,text1,text2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout rootLayout = (LinearLayout)findViewById(R.id.rootLayout);
		pointerInfo = new TextView(this);
		count = new TextView(this);
		text1 = new TextView(this);
		text2 = new TextView(this);
		pointerInfo.setText("null");
		count.setText("count : "+Input.getTouchCount());
		text1.setText(Input.getTouchArray()[0].toString());
		text2.setText(Input.getTouchArray()[1].toString());
		rootLayout.addView(pointerInfo);
		rootLayout.addView(count);
		rootLayout.addView(text1);
		rootLayout.addView(text2);
	}

	@Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int eventAction = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int ptrIndex = event.findPointerIndex(pointerId);
    	Touch temp;

        switch (eventAction) {
        case MotionEvent.ACTION_DOWN:
        	Input.addTouchCount();
        	(Input.getTouch()).setTouch(event.getX(ptrIndex), event.getY(ptrIndex), pointerId);
            break;

        case MotionEvent.ACTION_POINTER_DOWN:
        	Input.addTouchCount();
        	if(Input.getTouchCount() <= Input.getMaxTouch()){
        		(Input.getTouch()).setTouch(event.getX(ptrIndex), event.getY(ptrIndex), pointerId);
        	}
            break;

        case MotionEvent.ACTION_POINTER_UP:
        	Input.subTouchCount();
        	if((temp = Input.getTouch(pointerId)) != null){
        		temp.removeTouch();
        	}
            break;

        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
        	Input.subTouchCount();
        	if((temp = Input.getTouch(pointerId)) != null){
        		temp.removeTouch();
        	}
            break;

        case MotionEvent.ACTION_MOVE:
        	//どれか一つでも移動された場合、全てのタッチ位置を更新する
    		for(int n=0;n < Input.getMaxTouch();n++){
    			if((temp = Input.getTouchArray()[n]).getTouchID() != -1){
    				temp.updatePosition(event.getX(event.findPointerIndex(temp.getTouchID())),event.getY(event.findPointerIndex(temp.getTouchID())));
    			}
        	}
            break;
        }
        //pointerInfo.setText("pointerID:"+pointerId+" pointerIndex:"+pointerIndex+" ptrIndex:"+ptrIndex);
        //count.setText("count : " + Input.getTouchCount());
        //text1.setText(Input.getTouchArray()[0].toString());
        //text2.setText(Input.getTouchArray()[1].toString());
        return true;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
