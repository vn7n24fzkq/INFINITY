package world;

import java.io.FileReader;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.*;

import BasicGame.Resources;
import BasicGame.window;

public class Map {
	public static int WIDTH;
	public static int HEIGHT;

	private static TiledMap map;

	private static int Solids, Background, Platform, Goods, Foreground;
	private static int mapEndX;
	private static int mapEndY;
	public static int X = 0, realX = 0;
	public static int Y = 0, realY = 0;
	public static int back_x = 0, back_y = 0;
	private static String MapName;
	private static Image background;

	public static void render() {
		map.render(X, Y, Background);
		map.render(X, Y, Platform);
		map.render(X, Y, Goods);
		// map.render(X, Y, Foreground);

		// MapImage.draw(X, Y);
		// MapBackgroundImage.draw(back_x,back_y);
		// map.render(startX, startY);

		// map.render(startX, startY, startX, startY, 80, 80);
	}

	public static void renderBackground() {
		renderLayers(X, Y, Background);
	
		/*map.render(X, Y, Solids);
		map.render(X, Y, Platform);
		map.render(X, Y, Goods);*/
		renderLayers(X, Y, Solids);
		renderLayers(X, Y, Platform);
		renderLayers(X, Y, Goods);
	}

	public static void renderPlatform() {
		renderLayers(X, Y, Platform);

	}

	public static void renderGoods() {
		renderLayers(X, Y, Goods);

	}

	public static void renderForeground() {
		renderLayers(X, Y, Foreground);

	}

	public static void renderLayers(int x, int y, int obj) {
		if(map != null){
			map.render(x, y, obj);
		}

	}

	public static String getName() {
		return MapName;
	}

	public static void update() {

		// map.getTileId(0, 0, objectLayer);

	}

	public static void drawBackImage(float x, float y) {
		if (background != null) {
			background.draw(x, y);
		}
	}

	public static void load(String Name, TiledMap Map, Image Back) {

		MapName = Name;
		map = Map;
		background = Back;
		WIDTH = map.getWidth() * Tile.SMALL_SIZE;
		HEIGHT = map.getHeight() * Tile.SMALL_SIZE;
		Solids = map.getLayerIndex("solids");
		Background = map.getLayerIndex("background");
		Platform = map.getLayerIndex("platform");
		Goods = map.getLayerIndex("goods");
		Foreground = map.getLayerIndex("foreground");

	}

	public static void setStartXY(int tileX, int tileY) {

		X = (-1 * tileX * Tile.SMALL_SIZE);
		realX = (-1 * X);
		Y = (-1 * tileY * Tile.SMALL_SIZE);
		realY = (-1 * Y);
		mapEndX = (X + map.getWidth() * Tile.SMALL_SIZE);

		mapEndY = (Y + map.getHeight() * Tile.SMALL_SIZE);
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static int getEndX() {
		return X + map.getWidth();
	}

	public static int getEndY() {
		return Y + map.getHeight();
	}

	public static boolean hitTest(int x, int y) {
		try {

			// System.out.println("*****************");
			if (map.getTileId(((x - X) / Tile.SMALL_SIZE), ((y - Y) / Tile.SMALL_SIZE), Solids) == 0) {
				return true;// 如果沒有物件 回傳妹碰撞到(true
			}
		} catch (Exception e) {
			// System.out.println("hitTestError:"+e.toString());
			return false;
		}
		return false;
	}

	public static boolean PlatformHitTest(int x, int y) {
		try {

			// System.out.println("*****************");
			if (map.getTileId(((x - X) / Tile.SMALL_SIZE), ((y - Y) / Tile.SMALL_SIZE), Platform) == 0) {
				return true;// 如果沒有物件 回傳妹碰撞到(true
			}
		} catch (Exception e) {
			// System.out.println("hitTestError:"+e.toString());
			return false;
		}
		return false;
	}

}