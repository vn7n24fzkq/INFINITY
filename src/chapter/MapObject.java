package chapter;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import entities.Box;
import entities.Entity;
import entities.Monster.Monster;
import entities.NPC.NPC;
import entities.Trigger.Trigger;
import world.Tile;

public class MapObject {

	public int WIDTH;
	public int HEIGHT;
	public int X = 0, realX = 0;
	public int Y = 0, realY = 0;
	public int back_x = 0, back_y = 0;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> items = new ArrayList<Entity>();
	private ArrayList<NPC> NPC = new ArrayList<NPC>();
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<Trigger> triggers = new ArrayList<Trigger>();
	private TiledMap map;
	private String MapName;
	private Image Background;

	public MapObject(String MapName, TiledMap map, Image back) {
		this.MapName = MapName;
		this.map = map;
		this.Background = back;
	}

	public String getName() {
		return MapName;
	}

	public TiledMap getMap() {
		return map;
	}

	public Image getBackground() {
		return Background;
	}

	public void addEntity(Box b) {
		entities.add((Entity) b);
	}

	public void removeEntity(int No) {
		entities.remove(No);
	}

	public void addItem(Box b) {
		items.add((Entity) b);
	}

	public void removeItem(int No) {
		items.remove(No);
	}

	public void addNPC(Box b) {
		NPC.add((NPC) b);
	}

	public void removeNPC(int No) {
		NPC.remove(No);
	}

	public void addMonster(Box b) {
		monsters.add((Monster) b);
	}

	public void removeMonster(int No) {
		monsters.remove(No);
	}
	public void addTrigger(Box b) {
		triggers.add((Trigger) b);
	}

	public  void removeTrigger(int No) {
		triggers.remove(No);
	}

	public ArrayList<Entity> getItemsList() {
		return this.items;
	}

	public ArrayList<NPC> getrNPCList() {
		return this.NPC;
	}

	public ArrayList<Monster> getMonsterList() {
		return this.monsters;
	}
	public ArrayList<Trigger> getTriggerList() {
		return this.triggers;
	}
}