package entities.Bullet;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import entities.Box;
import entities.Entity;
import entities.EntityData;
import entities.Player.PlayerData;
import state.Play;

public class Bullet extends Entity {
	public static Animation animation_flying_right, animation_flying_left, animation_explosion;

	private Animation animation;
	public final static byte fly_width = 20;
	public final static byte fly_height = 10;
	public final static byte exp_width = 18;
	public final static byte exp_height = 17;
	private final static byte speed_right = 5;
	private final static byte speed_left = -5;
	private byte speed;
	private byte face;
	private boolean hit = false;

	public Bullet(byte face, int x, int y, short width, short height) {
		this.face = face;
		this.width = width;
		this.height = height;
		this.Canbehit = true;

		if (face == EntityData.Motion_FaceRight) {
			this.x = x + getHalfWidth();
			this.y = y - getHalfHeight();
			speed = speed_right;
		} else if (face == EntityData.Motion_FaceLeft) {
			this.x = x - getHalfWidth();
			this.y = y - getHalfHeight();
			speed = speed_left;
		}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		if (hit == false) {
			// animation_flying.draw(x, y);
			// animation.draw(x,y);
			if (this.face == PlayerData.Face_Right) {

				animation_flying_right.draw(x, y);
			} else if (this.face == PlayerData.Face_Left) {

				animation_flying_left.draw(x, y);
			}

			// animation.getCurrentFrame().getFlippedCopy(true, false).draw(x,
			// y);
		} else {
			// animation_explosion.draw(x, y);
			if (this.face == PlayerData.Face_Right) {
				animation_explosion.draw(x, y);
			} else if (this.face == PlayerData.Face_Left) {
				animation_explosion.draw(x - 10, y);
			}
			if (animation_explosion.getFrame() + 1 == animation_explosion.getFrameCount()) {
				Play.removeBullet(Play.getCurrentEntity());
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if (this.face == PlayerData.Face_Right) {

			for (int i = 0; i < speed; i++) {
				if (hit()) {
					x += 1;
				} else {
					hit = true;
					break;
				}

			}
		} else if (this.face == PlayerData.Face_Left) {
			for (int i = 0; i > speed; i--) {
				if (hit()) {
					x -= 1;
				} else {
					hit = true;
					break;
				}

			}
		}

		// TODO Auto-generated method stub

	}

	private boolean hit() {
		if (this.face == PlayerData.Face_Right) {
			return testRight();
		} else if (this.face == PlayerData.Face_Left) {
			return testLeft();
		}
		return false;
	}

	@Override
	public boolean hitTest(Box b) {
		// TODO Auto-generated method stub
		if ((Canbehit == true) && b.getClass().getName().contains("entities.Player")
				&& b.getClass().getName().contains("entities.Monster.Monster")) {

			if (super.hitTest(b)) {
				hit = true;
			}

		}
	

		return false;
	}
	

}