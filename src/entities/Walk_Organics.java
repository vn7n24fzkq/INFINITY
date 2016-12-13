package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import BasicGame.Control;
import entities.Player.PlayerData;

public class Walk_Organics extends Entity {

	public int jump = 0;
	public int health;
	public int start_x, start_y;
	public byte face = EntityData.Motion_FaceRight;
	public boolean Invincible = false;
	public boolean Falling = false;
	public boolean jumping = false;

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

	}

	protected void Falling(float Speed_ComeDown, int delta) {

		if (testDown() && jumping == false) {
			int j = (int) (Speed_ComeDown * delta);
			Falling = true;
			for (int i = 0; i < j; i++) {
				if (testDown()) {
					y += 1;
				} else {
					Falling = false;
					break;
				}
			}
		} else {
			Falling = false;
		}

	}

	protected void jump(float jump_speed, int jump_height, int delta) {
		if (jump <= jump_height && jumping == true) {

			jump += 1;
			int j = (int) (jump_speed * delta);
			for (int i = 0; i < j; i++) {
				if (testUp()) {
					y -= 1;
				} else {
					jumping = false;
					jump = 0;
					break;
				}
			}
		} else if ((jump > jump_height) || (!testUp())) {
			jumping = false;
			jump = 0;
		}
	}

	protected void walk(byte face, int delta, float speed) {
		int j = (int) (speed * delta);
		switch (face) {
		case EntityData.Motion_FaceRight:
			for (int i = 0; i < j; i++) {
				if (testRight()) {
					x += 1;
				}

			}
			break;
		case EntityData.Motion_FaceLeft:
			for (int i = 0; i < j; i++) {
				if (testLeft()) {
					x -= 1;
				}

			}
			break;
		}

	}

	public void getHurt(int i) {
		// 如果不是無敵
		if (Invincible == false) {
			health -= i;
		}
	}

	protected void setInvincuble(final int time) {
		new Thread() {
			public void run() {
				Invincible = true;

				try {
					this.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Invincible = false;

			}
		}.start();

	}

	public void setStartot(int x, int y) {
		this.start_x = x;
		this.start_y = y;
		this.x = x;
		this.y = y;

	}

}