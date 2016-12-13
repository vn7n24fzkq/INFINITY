package entities;

import state.Play;

public class Box {

	public  int x;
	public  int y;
	public  short width;
	public  short height;

	
	public Box() {

	}

	public Box(int x, int y, short width, short height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public int getEndX() {
		return (x + width);
	}

	public int getEndY() {
		return (y + height);
	}

	public int getHalfWidth() {
		return (width / 2);
	}

	public int getHalfHeight() {
		return (height / 2);
	}

	public int getCenterX() {
		return (x + getHalfWidth());
	}

	public int getCenterY() {
		return (y + getHalfHeight());
	}

	public boolean hitTest(Box b) {
		//真為 有蹦撞到
		return (b.x <= getCenterX()
				&&  b.getEndX() >=getCenterX()
				&&  b.y <=getCenterY()
				&& b.getEndY() >= getCenterY());
		
	
	}

}