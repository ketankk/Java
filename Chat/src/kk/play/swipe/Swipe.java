package kk.play.swipe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

@SuppressLint("NewApi")
public class Swipe extends SimpleOnGestureListener {
	final static int SWIPE_UP=1;
	final static int SWIPE_LEFT=2;
	final static int SWIPE_DOWN=3;	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	final static int SWIPE_RIGHT=4;
	
	final static int MODE_TRANSPARENT=0;
	final static int MODE_SOLID=1;
	final static int MODE_DYNAMIC=2;
	private static final int ACTION_FAKE = -13;

	private int mode=MODE_DYNAMIC;
	

	private int swipe_Min_Distance = 100;
    private int swipe_Max_Distance = 350;
    private int swipe_Min_Velocity = 100;

private Activity context;
GestureDetector detector;
SimpleOnGestureListener listener;
boolean running=true;
private boolean tapIndicator=false;

	public Swipe(Activity context,SimpleOnGestureListener sgl){
		this.context =context;
		this.detector=new  GestureDetector(context,this);
		this.listener=sgl;
		
		
	}
	
	void onTouch(MotionEvent event){
		if(!this.running)
			return;
		boolean result=this.detector.onTouchEvent(event);
		
		if(this.mode==MODE_SOLID)
			event.setAction(MotionEvent.ACTION_CANCEL);
		else 
		if(this.mode==MODE_DYNAMIC){
			if(event.getAction()==ACTION_FAKE)
				event.setAction(MotionEvent.ACTION_UP);
			else if(result)
				event.setAction(MotionEvent.ACTION_CANCEL);
			else if(this.tapIndicator){
				event.setAction(MotionEvent.ACTION_DOWN);
				this.tapIndicator=false;
					
				
			}
		}
	}
	
	
	
	public int getSwipe_Min_Distance() {
		return swipe_Min_Distance;
	}

	public void setSwipe_Min_Distance(int swipe_Min_Distance) {
		this.swipe_Min_Distance = swipe_Min_Distance;
	}

	public int getSwipe_Max_Distance() {
		return swipe_Max_Distance;
	}

	public void setSwipe_Max_Distance(int swipe_Max_Distance) {
		this.swipe_Max_Distance = swipe_Max_Distance;
	}

	public int getSwipe_Min_Velocity() {
		return swipe_Min_Velocity;
	}

	public void setSwipe_Min_Velocity(int swipe_Min_Velocity) {
		this.swipe_Min_Velocity = swipe_Min_Velocity;
	}
	 
	
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	           float velocityY){
		float xDistance=Math.abs(e1.getX()-e2.getX());
		float yDistance=Math.abs(e1.getY()-e2.getY());
		if(xDistance>this.swipe_Max_Distance||yDistance>this.swipe_Max_Distance)
			return false;
		velocityX=Math.abs(velocityX);
		velocityY=Math.abs(velocityY);
		boolean result=false;
		if(velocityX>this.swipe_Min_Velocity&&xDistance>this.swipe_Min_Distance)
		{
		}return running;
		}
		
	
	
	
	
	
}
