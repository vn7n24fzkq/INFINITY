package BasicGame;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import entities.Player.PlayerData;
import world.Tile;


public class Resources {

	private static Map<String, Image> images;
	private static Map<String, Sound> sounds;
	private static Map<String, SpriteSheet> sprites;
	private static Map<String, TiledMap> map;
	
	

	public Resources() {
		images = new HashMap<String, Image>();
		sprites = new HashMap<String, SpriteSheet>();
		FileWriter fw;
		try {
			// read map tmx
			//map.put("test", loadMap( Data.DataPath.path + "/tiled/test.tmx"));
			// load charactersheet
			sprites.put("life_full", loadSprite(Data.DataPath.path_res_pic_UI + "/life_full.png",
					40, 32));
			sprites.put("life_empty", loadSprite(Data.DataPath.path_res_pic_UI + "/life_empty.png",
					40, 32));
			sprites.put("loading", loadSprite(Data.DataPath.path_res_pic + "/loading.png",
					160, 80));
			// read character image
			// images.put("cha",loadImage(path+"/character/davis/davis_stand_right.png"));

			// reade opening background image
			images.put("background", loadImage(Data.DataPath.path_res_back + "/background.png"));
			// read map png
			//images.put("back", loadImage(Data.DataPath.path + "/tiled/test_back.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Animation getAnimation(String path,byte SheetWIDTH,byte SheetHEIGHT,int AnimationDuration){
		try {
			return new Animation(loadSprite(path,SheetWIDTH, SheetHEIGHT),AnimationDuration);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
	}

	public static Image getImage(String getter) {
		return images.get(getter);
	}

	public static TiledMap loadMap(String path) throws SlickException {
		return new TiledMap(path);
	}

	public static TiledMap getMap(String getter) {
		return map.get(getter);
	}

	public static SpriteSheet loadSprite(String path, int tw, int th) throws SlickException {
		return new SpriteSheet(loadImage(path), tw, th);
	}

	public static SpriteSheet getSprite(String getter) {
		return sprites.get(getter);
	}

	public static Image getSpriteImage(String getter, int x, int y) {
		return sprites.get(getter).getSubImage(x, y);
	}

	public static Sound getSound(String getter) {
		return sounds.get(getter);
	}
	
	
}
