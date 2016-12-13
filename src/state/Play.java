package state;

import BasicGame.Control;
import BasicGame.Debug;
import BasicGame.Listener;
import BasicGame.Resources;
import BasicGame.window;
import camera.PlayerCamera;
import chapter.ChapterLoader;
import entities.Box;
import entities.Entity;
import entities.Bullet.Bullet;
import entities.Monster.Monster;
import entities.NPC.NPC;
import entities.Player.Player;
import entities.Player.PlayerData;
import entities.Trigger.Trigger;
import entities.items.PortalDoor;
import world.Map;
import world.Tile;

import java.awt.im.InputContext;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import Data.DataPath;

public class Play extends BasicGameState {

	// public static ArrayList<Entity> entities;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Entity> items;
	public static ArrayList<NPC> NPC;
	public static ArrayList<Monster> monsters;
	public static ArrayList<Trigger> triggers;
	public static Player player;
	private static int EntityNo = 0;
	private static PlayerCamera camera;
	public static boolean pause = false;
	public boolean MenuPause = false;
	private PauseMenu menu;
	private Color pause_color = new Color(0f, 0f, 0f, 0.2f);
	public static ChapterLoader chapterLoader;
	public static Dialog dialog;
	public static boolean talking = false;
	// Image image =Image(path, false, Image.FILTER_NEAREST);
	

	private Debug debugLine;

	public Play(int ID) {

	}
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		
	}

	private void init() {
		bullets = new ArrayList<Bullet>();
		items = new ArrayList<Entity>();
		monsters = new ArrayList<Monster>();
		NPC = new ArrayList<NPC>();
		triggers = new ArrayList<Trigger>();
		menu = new PauseMenu();
		menu.addOption("Resume");
		menu.addOption("Load");
		menu.addOption("Quit");
		debugLine = new Debug();
		debugLine.init();
		dialog = new Dialog();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbd, Graphics g) throws SlickException {

		camera.render(gc, g);
	
		Map.renderBackground();
		
		

		// g.scale(0, 0);

		// render all entities
		player.render(gc, g);
		for (EntityNo = 0; EntityNo < bullets.size(); EntityNo++) {
			try {
				bullets.get(EntityNo).render(gc, g);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		for (EntityNo = 0; EntityNo < items.size(); EntityNo++) {
			try {
				items.get(EntityNo).render(gc, g);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		for (EntityNo = 0; EntityNo < monsters.size(); EntityNo++) {
			try {
				monsters.get(EntityNo).render(gc, g);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		for (EntityNo = 0; EntityNo < NPC.size(); EntityNo++) {
			try {
				NPC.get(EntityNo).render(gc, g);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		for (EntityNo = 0; EntityNo < triggers.size(); EntityNo++) {
			try {
				triggers.get(EntityNo).render(gc, g);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		Map.renderForeground();

		g.resetTransform();
		//render_UI
		player.Player_UI_draw();
		
		if (talking == true) {
			dialog.render(gc, g);
		}
		if (MenuPause == true) {

			menu.render(gc, sbd, g);

		} else {
			if (menu.getSlect() != 0) {
				menu.setSlect(0);
			}
		}

		debugLine.render(gc, sbd, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbd, int delta) throws SlickException {

		if (talking == true || MenuPause == true) {
			pause = true;
		} else {
			pause = false;
		}
		if (pause == false) {
			Map.update();
			player.update(gc, delta);
			for (EntityNo = 0; EntityNo < bullets.size(); EntityNo++) {
				try {
					bullets.get(EntityNo).update(gc, delta);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			for (EntityNo = 0; EntityNo < items.size(); EntityNo++) {
				try {
					items.get(EntityNo).update(gc, delta);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			for (EntityNo = 0; EntityNo < monsters.size(); EntityNo++) {
				try {
					monsters.get(EntityNo).update(gc, delta);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			for (EntityNo = 0; EntityNo < NPC.size(); EntityNo++) {
				try {
					NPC.get(EntityNo).update(gc, delta);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			for (EntityNo = 0; EntityNo < triggers.size(); EntityNo++) {
				try {
					triggers.get(EntityNo).update(gc, delta);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

		}
		if (MenuPause == true) {
			menu.update(gc, sbd, delta);

		} else if (talking == true) {
			dialog.update(gc, delta);
		}
		// 設置esc跳出
		
		

		if (Control.input.isKeyPressed(Input.KEY_ESCAPE)) {
			if (MenuPause == false) {
				MenuPause = true;

			} else {
				MenuPause = false;
				Control.input.clearKeyPressedRecord();
			}
		}
		if (Control.input.isKeyPressed(Input.KEY_D)) {
			player.getHurt(1);
			System.out.println(items);
		}
		debugLine.update(gc, sbd, delta);
		Play.chapterLoader.switchMap();
		Control.input.clearKeyPressedRecord();
	}

	@Override
	public int getID() {

		return state.Play;
	}

	public void Load_Resources(String ChapterName) {
		init();
	
		player = new Player();//intit player first(important)
		chapterLoader = new ChapterLoader();
		chapterLoader.loadChapter();

		

		// addEntity(player);
		camera = new PlayerCamera(player);
	}

	public static void addItem(Box b) {
		items.add((Entity) b);
	}

	public static void removeItem(int No) {
		items.remove(No);
	}

	public static void addBullet(Box b) {
		bullets.add((Bullet) b);
	}

	public static void removeBullet(int No) {
		bullets.remove(No);
	}

	public static void addNPC(Box b) {
		NPC.add((NPC) b);
	}

	public static void removeNPC(int No) {
		NPC.remove(No);
	}

	public static void addTrigger(Box b) {
		triggers.add((Trigger) b);
	}

	public static void removeTrigger(int No) {
		triggers.remove(No);
	}

	public static int getCurrentEntity() {
		return EntityNo;
	}

	public static void addDialogText(Image avatar0, Image avatar1, ArrayList<String> context) {
		dialog.setCintext(avatar0, avatar1, context);
	}

	@Override
	public void keyPressed(int key, char c) {

	}
}

class Dialog extends Entity {
	// private static HashMap<String, Image> talker;
	private Image[] Talkers;
	private ArrayList<String> context;
	private UnicodeFont Font;
	private int FontSize;
	private int currentTalk;

	private TiledMap Dialog_Image;
	private int Dialog_Image_draw_X, Dialog_Image_draw_Y;
	private int talker_Image_draw_X, talker_Image_draw_Y;
	private int context_draw_X, context_draw_Y;
	private Color pause_color = new Color(0f, 0f, 0f, 0.2f);
	String speech ;
	public Dialog() {

	}

	@Override
	public void init() {
		String fontPath = DataPath.path_res_font + "msjhbd/msjhbd.ttc";
		try {
			FontSize = 20;
			currentTalk = 0;
			Font = new UnicodeFont(fontPath, FontSize, true, true);
			Font.addAsciiGlyphs();
			Font.addGlyphs(0, 1000);

			Font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));

			// Dialog_Image = new Image(
			// DataPath.path_res_pic_UI+"dialog.png",false,Image.BOTTOM_RIGHT);
			Dialog_Image = new TiledMap(DataPath.path_res_pic_UI + "UI.tmx");

			Dialog_Image_draw_Y = 0;
			Dialog_Image_draw_Y = window.HEIGHT - Dialog_Image.getHeight() * Dialog_Image.getTileHeight();
			talker_Image_draw_X = Dialog_Image.getLayerIndex("talker_pic");

			talker_Image_draw_X = Dialog_Image_draw_X + 3 * 4;
			talker_Image_draw_Y = Dialog_Image_draw_Y + 4 * 4;

			context = new ArrayList<String>();
			Talkers = new Image[2];

			context_draw_X = Dialog_Image_draw_X + 36 * 4;
			context_draw_Y = Dialog_Image_draw_Y + 6 * 4;

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		// String text = context.get(currentTalk);
		// talker.get("").draw(x, y, width, height);
		// Font.drawString(x, y, context.get(0), Color.white);
		// Dialog_Image.draw(Dialog_Image_draw_X, Dialog_Image_draw_Y);
		 speech = context.get(currentTalk).substring(1).replace(":", ":\n");

		int onTalk = Integer.parseInt(Character.toString(context.get(currentTalk).charAt(0)));
		g.setColor(pause_color);
		g.fillRect(0, 0, window.WIDTH, window.HEIGHT);
		Dialog_Image.render(Dialog_Image_draw_X, Dialog_Image_draw_Y);
		Talkers[onTalk].draw(talker_Image_draw_X, talker_Image_draw_Y, 27 * 4, 27 * 4);

		try {

			Font.addGlyphs(speech);
			Font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font.drawString(context_draw_X, context_draw_Y, speech, Color.orange);
		// Font.drawString(0, 0, "吃我的超猛超威猛帥氣盾", Color.orange);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		

		if (Control.input.isKeyPressed(Control.CHAT)) {
			if (currentTalk == (context.size() - 1)) {
				Play.talking = false;
				currentTalk = 0;
			} else {
				currentTalk += 1;
			}

		}

	}

	public void setCintext(Image avatar0, Image avatar1, ArrayList<String> context) {
		// this.context.clear();
		this.context = context;
		Talkers[0] = avatar0;
		Talkers[1] = avatar1;
		currentTalk = 0;
	}

}

class PauseMenu {
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
	private Color pause_color = new Color(0f, 0f, 0f, 0.2f);
	String context;

	public PauseMenu() {

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
		g.setColor(pause_color);
		g.fillRect(0, 0, window.WIDTH, window.HEIGHT);
		drawString();
	}

	public void update(GameContainer gc, StateBasedGame sbd, int delta) throws SlickException {
	
		if (Control.input.isKeyPressed(Control.UP)) {
			select -= 1;
			if (select >= 1) {
				TouchButton.play();
			} else if (select <= 0) {
				select = getSize();
			}
		} else if (Control.input.isKeyPressed(Control.DOWN)) {
			select += 1;
			if (select <= getSize() - 1) {
				TouchButton.play();
			} else if (select >= getSize()) {
				select = 0;
			}
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

	public void setSlect(int i) {
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
