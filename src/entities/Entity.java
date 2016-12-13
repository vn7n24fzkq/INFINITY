package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Data.DataPath;
import world.Map;

public abstract class Entity extends Box {

	public Color color;

	public boolean Mobility = true;// true 移動能力為真
	public boolean Canbehit = true;
	protected Image avatar;

	public Entity() {
		try {
			avatar = new Image(DataPath.path_res_pic + "unknow.png", false, Image.FILTER_LINEAR);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();

	}

	public abstract void init();

	public abstract void render(GameContainer gc, Graphics g);

	public abstract void update(GameContainer gc, int delta);

	public boolean testLeft() {
		if ((Map.hitTest(x - 1, getCenterY())) && (Map.hitTest(x - 1, y)) && (Map.hitTest(x - 1, getEndY()))) {
			return true;
		}

		return false;
	}

	public boolean testRight() {
		if ((Map.hitTest(getEndX() + 1, getCenterY())) && (Map.hitTest(getEndX() + 1, y))
				&& (Map.hitTest(getEndX() + 1, getEndY()))) {
			return true;
		}
		return false;
	}

	public boolean testUp() {
		if ((Map.hitTest(getCenterX(), y - 1)) && (Map.hitTest(getEndX(), y - 1)) && (Map.hitTest(x, y - 1))) {
			return true;
		}
		return false;
	}

	public boolean testDown() {
		if ((Map.hitTest(getCenterX(), getEndY() + 1)) && (Map.hitTest(getEndX(), getEndY() + 1))
				&& (Map.hitTest(x, getEndY() + 1))) {
			return true;
		} 
		
		return false;
	}

	public void Load_Resources() {

	}

	public Image getAvatar() {
		return avatar;
	}

}