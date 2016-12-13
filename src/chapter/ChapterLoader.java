package chapter;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import BasicGame.Resources;
import Data.DataPath;
import camera.PlayerCamera;
import entities.Entity;
import entities.Bullet.Bullet;
import entities.NPC.NPC;
import entities.Player.Player;
import entities.Player.PlayerData;
import entities.Trigger.Trigger;
import entities.items.PortalDoor;
import state.Play;
import world.Map;

public class ChapterLoader {
	private String ChapterName;
	private HashMap<String, MapObject> MapList = new HashMap<String, MapObject>();
	private boolean switchMap = false;
	private String switchName;

	public ChapterLoader() {
		// TODO Auto-generated method stub

	}

	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub

	}

	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

	}

	public void setSwitchMap(String MapName) {
		switchMap = true;
		switchName = MapName;
	}

	public void switchMap() {
		if (switchMap == true) {
			PlayerCamera.MoveRecovery();
			MapObject mapO = MapList.get(switchName);
			Map.load(mapO.getName(), mapO.getMap(), mapO.getBackground());

			// Play.items.clear();
			Play.items = mapO.getItemsList();
			// Play.NPC.clear();
			Play.NPC = mapO.getrNPCList();
			// Play.monsters.clear();
			Play.monsters = mapO.getMonsterList();
			Play.triggers = mapO.getTriggerList();
			switchMap = false;
		}
	}

	public void loadChapter() {
		try {

			MapObject mapObject;
			// ****************load_Map****************

			/*
			 * mapObject = new MapObject("test", new
			 * TiledMap(Data.DataPath.path_res_map_tiled + "test.tmx"), new
			 * Image(DataPath.path_res_map_tiled + "test_back.png"));
			 * mapObject.addItem(new PortalDoor(40, 314, 20,
			 * 50,"test_door","Truth's room Door",mapObject.getName()));
			 * mapObject.addTrigger(new Trigger(118, 330));
			 * MapList.put(mapObject.getName(), mapObject);
			 */

			// --------------------------Truth's_room
			try {
				mapObject = new MapObject("Truth's_room",
						new TiledMap(Data.DataPath.path_res_map_tiled + "Truth's_room.tmx"), null);
				mapObject.addItem(new PortalDoor(288, 60, "Truth's_door_1", "Truth's_door_2", mapObject.getName()));
				mapObject.addNPC(new NPC(236, 74));
				mapObject.addTrigger(new Trigger(151, 87));
				// addItem(new PortalDoor(291, 66, 25, 40, "testdoor", "Truth's
				// Door").getDoor());
				MapList.put(mapObject.getName(), mapObject);
			} catch (Exception e) {

			}
			// --------------------------upstair
			try {
				mapObject = new MapObject("upstair", new TiledMap(Data.DataPath.path_res_map_tiled + "upstair.tmx"),
						null);
				mapObject.addItem(new PortalDoor(148, 64, "Truth's_door_2", "Truth's_door_1", mapObject.getName()));
				mapObject.addItem(new PortalDoor(16, 64, "Downstair_door", "Upstair_door", mapObject.getName()));
				MapList.put(mapObject.getName(), mapObject);
			} catch (Exception e) {

			}
			// ---------------------------Living Room
			try {
				mapObject = new MapObject("Living_Room",
						new TiledMap(Data.DataPath.path_res_map_tiled + "Living_Room.tmx"), null);
				mapObject.addItem(new PortalDoor(400, 64, "Upstair_door", "Downstair_door", mapObject.getName()));
				mapObject.addItem(new PortalDoor(500, 64, "Bathroom_door", "B_to_L_door", mapObject.getName()));
				mapObject.addItem(new PortalDoor(612, 64, "Mom's_door", "M_to_L_door", mapObject.getName()));
				MapList.put(mapObject.getName(), mapObject);
			} catch (Exception e) {

			}
			// ---------------------------Bathroom
			try {
				mapObject = new MapObject("Bathroom", new TiledMap(Data.DataPath.path_res_map_tiled + "Bathroom.tmx"),
						null);
				mapObject.addItem(new PortalDoor(68, 64, "B_to_L_door", "Bathroom_door", mapObject.getName()));
				MapList.put(mapObject.getName(), mapObject);
			} catch (Exception e) {

			}
			// ---------------------------Mom_Room
			try {
				mapObject = new MapObject("Mom_Room", new TiledMap(Data.DataPath.path_res_map_tiled + "Mom_Room.tmx"),
						null);
				mapObject.addItem(new PortalDoor(252, 64, "M_to_L_door", "Mom's_door", mapObject.getName()));
				MapList.put(mapObject.getName(), mapObject);
			} catch (Exception e) {

			}
			// System.out.println("---------------------"+mapObject.getName()+"\n");

			
			// ****************load_Map_end*****************
			// ****************set_Map****************

			// ---------------set_map_back--------------
			Play.player.setStartDot(10, 10);
			Map.back_x = 640;
			Map.back_y = 480;

			// ****************set_Map_end****************

			// ********************load_bullet***********************
			Bullet.animation_explosion = Resources.getAnimation(DataPath.path_res_pic + "bullet_expoler.png",
					Bullet.exp_width, Bullet.exp_height, PlayerData.AnimationDuration);
			Bullet.animation_flying_right = Resources.getAnimation(DataPath.path_res_pic + "bullet_flying_right.png",
					Bullet.fly_width, Bullet.fly_height, PlayerData.AnimationDuration);
			Bullet.animation_flying_left = Resources.getAnimation(DataPath.path_res_pic + "bullet_flying_left.png",
					Bullet.fly_width, Bullet.fly_height, PlayerData.AnimationDuration);
			// ********************load_bullet_end***********************
			//*********************map set********************
			setSwitchMap("Truth's_room");
			switchMap();
			//*********************map_set_end********************
		} catch (Exception e) {

		}

		// ***********player set*************

	}

}