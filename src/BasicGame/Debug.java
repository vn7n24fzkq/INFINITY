package BasicGame;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import Data.DataPath;
import camera.PlayerCamera;

public class Debug {
	static boolean show = false;
	
	private Option option;

	public Debug() {

	}

	int x = 0, y = 0;

	public void init() {
		option = new Option();

	}

	public void render(GameContainer gc, StateBasedGame sbd, Graphics g) {
		// TODO Auto-generated method stub
		// g.drawGradientLine(x1, y1, red1, green1, blue1, alpha1, x2, y2, red2,
		// green2, blue2, alpha2);
		/*
		 * for(int i = 0; i < window.HEIGHT;i+=16){
		 * g.drawLine(0,i,window.WIDTH,i); } for(int i = 0; i <
		 * window.WIDTH;i+=16){ g.drawLine(i,0,i,window.HEIGHT); }
		 */

		if (show == true) {
			g.setColor(Color.white);
			g.drawString("X:" + (x - PlayerCamera.Camera_X) + "--Y:" + (window.HEIGHT - y - PlayerCamera.Camera_Y), x,
					window.HEIGHT - y);
			try {
				option.render(gc, sbd, g);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void update(GameContainer gc, StateBasedGame sbd, int delta) {

		// TODO Auto-generated method stub
		x = Mouse.getX();
		y = Mouse.getY();

		try {
			option.update(gc, sbd, delta);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Option {
	// private static String fontPath = DataPath.path_res_font +
	// "GreatVibes-Regular.otf";
	private String fontPath = DataPath.path_res_font + "/karmatic_arcade/ka1.ttf";
	private float x = 0, y = 0;
	private UnicodeFont Font;
	private ArrayList<String> Option_list = new ArrayList<String>();
	private int FontSize = 20;

	private Color col, color_none = Color.yellow;
	int update_count = 0;
	String context;
	String delta = "", time = "", FPS = "",update = "";
 int option_count = 4;
	public Option() {

		try {

			Font = new UnicodeFont(fontPath, FontSize, true, true);
			Font.addAsciiGlyphs();
			Font.addGlyphs(0, 0);
			Font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			Font.loadGlyphs();
			Option_list.add("");
			this.y = window.HEIGHT - Font.getAscent() * option_count * 2;
			this.x = Font.getWidth("   ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void render(GameContainer gc, StateBasedGame sbd, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		// drawString();
		float x = this.x, y = this.y;
		Font.drawString(x, y, this.delta, color_none);

		y += Font.getAscent() * 2;
		Font.drawString(x, y, time, color_none);

		y += Font.getAscent() * 2;
		Font.drawString(x, y, FPS, color_none);
		y += Font.getAscent() * 2;
		Font.drawString(x, y,update, color_none);
		y += Font.getAscent() * 2;
		update_count = 0;
	}

	public void update(GameContainer gc, StateBasedGame sbd, int delta) throws SlickException {
		update_count+=1;
		this.delta = "Delta:" + Integer.toString(delta);
		time = "time:" + Long.toString(gc.getTime());
		FPS = "FPS:" + gc.getFPS();
		update = "update:" + Integer.toString(update_count);

	}

	public void drawString() {
		float x = this.x, y = this.y;
		/*
		 * for (int i = 0; i < Option_list.size(); i++) {
		 * 
		 * Font.drawString(x, y, Option_list.get(i), color_none);
		 * 
		 * y += Font.getAscent() * 2; }
		 */
	}

	public void gety() {
		Font.getAscent();
	}

	public int addOption(String s) {
		Option_list.add(s);
		this.y = window.HEIGHT - Font.getAscent() * option_count * 2;
		this.x = Font.getWidth("   ");
		return Option_list.indexOf(s);

	}

	public int getSize() {
		return Option_list.size();
	}

	private boolean checkSelect(int sel) {
		if (sel == 0 || sel == Option_list.size()) {
			return false;
		}
		return true;

	}

}