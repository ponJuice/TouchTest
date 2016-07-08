package com.example.touchtest;

class Touch{
	public enum Pos_Flag{
		X,
		Y
	}
	private float x;
	private float y;
	private float deltaX;
	private float deltaY;
	private int touchID;
	public Touch(){
		x = 0;
		y = 0;
		deltaX = 0;
		deltaY = 0;
		touchID = -1;
	}
	public void setTouch(float x,float y,int touchID){
		this.x = x;
		this.y = y;
		this.touchID = touchID;
		deltaX = 0;
		deltaY = 0;
	}
	public void updatePosition(float x,float y){
		deltaX = java.lang.Math.abs(this.x - x);
		deltaY = java.lang.Math.abs(this.y - y);
		this.x = x;
		this.y = y;
	}
	public float getPosition(Pos_Flag pos){
		if(pos == Pos_Flag.X){
			return x;
		}
		else{
			return y;
		}
	}
	public float getDeltaPosition(Pos_Flag pos){
		if(pos == Pos_Flag.X){
			return deltaX;
		}
		else{
			return deltaY;
		}
	}
	public int getTouchID(){
		return touchID;
	}
	public void removeTouch(){
		touchID = -1;
	}
	@Override
	public String toString(){
		return "pos ["+x+":"+y+"] delta["+deltaX+":"+deltaY+"] touchID="+touchID;

	}
}
