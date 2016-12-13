package entities.items;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import BasicGame.Control;
import entities.Entity;
import state.Play;
import world.Map;
import world.Tile;

public class PortalDoor extends Entity {

	public static HashMap<String, PortalDoor> doors = new HashMap<String, PortalDoor>();
	private boolean usable = true;
	private String LinkDoor, MapName, DoorName;

	public PortalDoor(int x, int y, short wight, short height) {
		this.x = x;
		this.y = y;
		this.width = wight;
		this.height = height;
	}
	public PortalDoor(int x, int y,String DoorName, String LinkDoor, String MapName) {
		
		int width = 9*Tile.SMALL_SIZE;
		int height = 13*Tile.SMALL_SIZE;

		setDoor( x, y,  width, height, DoorName,  LinkDoor, MapName);
		doors.put(DoorName, this);
	}
	public PortalDoor(int x, int y, int width, int height, String DoorName, String LinkDoor, String MapName) {
		setDoor( x, y,  width, height, DoorName,  LinkDoor, MapName);
		
	}
	private void setDoor(int x, int y, int width, int height, String DoorName, String LinkDoor, String MapName){
		this.x = x;
		this.y = y;
		this.width = (short) width;
		this.height = (short) height;
		this.LinkDoor = LinkDoor;
		this.DoorName = DoorName;
		this.MapName = MapName;
		doors.put(DoorName, this);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
	//	g.drawRect(x, y, this.width, this.height);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		if (usable) {
			if (hitTest(Play.player)) {
				if (Control.input.isKeyPressed(Control.UP)) {
					PortalToLinkDoor();
				}
			}
		}
		
	}

	public void setUsable(boolean bool) {
		usable = bool;
	}

	public void PortalToLinkDoor() {
		// Map.setMap(MapName);
		Play.player.setStartDot(doors.get(LinkDoor).x, doors.get(LinkDoor).y);
		if (Map.getName() == "Truth's room") {
			Play.chapterLoader.setSwitchMap("test");
		} else {
			Play.chapterLoader.setSwitchMap("Truth's room");
		}
		Play.chapterLoader.setSwitchMap(doors .get(LinkDoor).MapName);
	
	}

	public PortalDoor getDoor() {
		return doors.get(DoorName);
	}

	public static void putDoor(String name, PortalDoor door) {
		doors.put(name, door);
	}

	public static void clearDoor() {
		doors.clear();

	}

}