package entities.Trigger;

import org.json.simple.JSONObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import BasicGame.Control;
import entities.Box;
import entities.Entity;
import entities.Player.*;
import state.Play;
import world.Map;

public class Trigger extends Entity {
	public boolean triggerable = true;
	private Gear gear = new Gear();
	public boolean prompt;

	public Trigger() {
		Canbehit = true;
	}

	public Trigger(String setting) {

	}

	public Trigger(int i, int j) {
		this.x = i;
		this.y = j;
		this.width = 20;
		this.height = 20;

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		//g.drawOval(x, y, this.width, this.height);

		gear.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		if (hitTest(Play.player)) {
			if (input.isKeyPressed(Control.CHAT)) {
				trigger();

			}
		}

		gear.update(gc, delta);
	}

	public void trigger() {
		if (triggerable == true) {
			System.out.println("trigger");
		}

		triggerable = false;
	}
}

class Gear extends Entity {

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

}