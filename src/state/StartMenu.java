package state;

import java.awt.Window;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tests.*;
import BasicGame.Control;
import BasicGame.Debug;
import BasicGame.Listener;
import BasicGame.Resources;
import BasicGame.window;
import Data.DataPath;
import world.Tile;

public class StartMenu extends BasicGameState {
	Image background;

	// float start_x, start_y, option_x, option_y, quit_x, quit_y;
	private Color color_start = Color.black, color_option = Color.black, color_quit = Color.black;
	private float menuX, menuY0, menuY1, menuY2;
	private Music bgm;

	private boolean onButton = false;
	private boolean TouchButtonPlayed = false;
	// private String[] option = { "", "Start", "Save", "Options", "Quit" };
	private Option menu;
	private int start, load, option, quit;
	private Debug debugLine = new Debug();

	public StartMenu(int start) {
		
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		background = Resources.getImage("background");
		bgm = new Music(DataPath.path_res_bgm + "/TestBgm.ogg");
		bgm.setVolume(0.5f);

		
		bgm.loop();

		/*
		 * menuX = (window.WIDTH / 12.8f); menuY0 = (window.HEIGHT / 1.8f);
		 * menuY1 = (window.HEIGHT / 1.44f); menuY2 = (window.HEIGHT / 1.2f);
		 */
		menu = new Option();
		start = menu.addOption("START");
		load = menu.addOption("LOAD");
		option = menu.addOption("OPTION");
		quit = menu.addOption("QUIT");
		debugLine.init();
		/*
		 * fpsFont.addAsciiGlyphs(); fpsFont.addGlyphs(0, 0);
		 * fpsFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		 * fpsFont.loadGlyphs();
		 * 
		 */
		gc.getInput().addKeyListener(new Listener());

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbd, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(background, 0, 0, window.WIDTH, window.HEIGHT, 0, 0, 1280, 720, Color.white);
		menu.render(gc, sbd, g);
		debugLine.render(gc,sbd, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbd, int delta) throws SlickException {
		// TODO Auto-generated method stub
	//	int dot_x = Mouse.getX();
	//	int dot_y = Mouse.getY();
		debugLine.update(gc,sbd, delta);
		color_start = Color.black;
		color_option = Color.black;
		color_quit = Color.black;

		onButton = false;

		
		menu.update(gc, sbd, delta);

		// System.out.println(focus);
		if ((Control.input.isKeyDown(Control.CHAT)) || (Control.input.isKeyDown(Input.KEY_ENTER))) {
			if (menu.getSlect() == start) {
				sbd.enterState(state.Loading);
				bgm.stop();

			} else if (menu.getSlect() == load) {
				sbd.enterState(state.Load);
				bgm.stop();
			} else if (menu.getSlect() == option) {
				sbd.enterState(state.Menu);
				bgm.stop();
			} else if (menu.getSlect() == quit) {
				gc.exit();
			}
		}

	}
	/*
	 * if (((dot_x >= menuX) && (dot_x <= window.WIDTH / 5.35f)) && ((dot_y >=
	 * window.HEIGHT / 2.58f)) && (dot_y <= window.HEIGHT / 2.14f)) {
	 * color_start = Color.red; onButton = true; if (Mouse.isButtonDown(0)) {
	 * click.play(); sbd.enterState(3); bgm.stop(); } } else if (((dot_x >=
	 * menuX) && (dot_x <= window.WIDTH / 4.6f)) && ((dot_y >= window.HEIGHT /
	 * 4.24f) && (dot_y <= window.HEIGHT / 3.16f))) { color_option = Color.red;
	 * onButton = true; if (Mouse.isButtonDown(0)) { click.play();
	 * sbd.enterState(2); bgm.stop(); } } else if (((dot_x >= menuX) && (dot_x
	 * <= window.WIDTH / 5.65f)) && ((dot_y >= window.HEIGHT / 12.32f) && (dot_y
	 * <= window.HEIGHT / 5.65f))) { color_quit = Color.red; onButton = true; if
	 * (Mouse.isButtonDown(0)) { click.play(); bgm.stop(); // sbd.enterState(2);
	 * gc.exit(); } } else { TouchButtonPlayed = false; }
	 */

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return state.StartMenu;
	}

}

class Option {
	// private static String fontPath = DataPath.path_res_font +
	// "GreatVibes-Regular.otf";
	private String fontPath = DataPath.path_res_font + "gomarice_nanikano_capsule.ttf";
	private float x = 0, y = 0;
	private UnicodeFont Font;
	private ArrayList<String> Option_list = new ArrayList<String>();
	private int FontSize = (int) (0.04 * window.WIDTH);
	private int select;
	private Color col, color_none = Color.black, color_select = Color.red;
	private Sound click, TouchButton;
	String context;

	public Option() {

		try {
			click = new Sound(DataPath.path_res_sound + "/SE_決定音.ogg");

			TouchButton = new Sound(DataPath.path_res_sound + "SE_選択音.ogg");
			Font = new UnicodeFont(fontPath, FontSize, true, true);
			Font.addAsciiGlyphs();
			Font.addGlyphs(0, 0);
			Font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			Font.loadGlyphs();
			Option_list.add("");
			select = 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void render(GameContainer gc, StateBasedGame sbd, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		drawString();
	}

	public void update(GameContainer gc, StateBasedGame sbd, int delta) throws SlickException {
		
		if (Control.input.isKeyPressed(Control.UP)) {
			select -= 1;
			if (select >= 1) {
			//	TouchButton.play();
			} else if (select <= 0) {
				select = getSize()-1 ;
			}
			TouchButton.play();
		} else if (Control.input.isKeyPressed(Control.DOWN)) {
			select += 1;
			if (select <= getSize() - 1) {
		//		TouchButton.play();
			} else if (select >=getSize()) {
				select = 1;
			}
			TouchButton.play();
		}
		if (((Control.input.isKeyPressed(Control.CHAT)) || (Control.input.isKeyPressed(Input.KEY_ENTER))) && checkSelect(select)) {
			click.play();
		}
		
	}

	public void drawString() {
		float x = this.x, y = this.y;
		for (int i = 0; i < Option_list.size(); i++) {
			if (i == select) {
				Font.drawString(x, y, Option_list.get(i), color_select);
			} else {
				Font.drawString(x, y, Option_list.get(i), color_none);
			}
			y += Font.getAscent() * 2;
		}
	}

	public void gety() {
		Font.getAscent();
	}

	public int addOption(String s) {
		Option_list.add(s);
		this.y = window.HEIGHT - Font.getAscent() * (Option_list.size()) * 2;
		this.x = Font.getWidth("   ");
		return Option_list.indexOf(s);

	}

	public int getSlect() {
		return select;
	}
	public void setSlect(int i ) {
		 select = i;
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