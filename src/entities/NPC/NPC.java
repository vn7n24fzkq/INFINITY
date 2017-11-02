package entities.NPC;


import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import BasicGame.Control;
import BasicGame.Resources;
import Data.DataPath;
import entities.Entity;
import entities.Player.Player;
import entities.Player.PlayerData;
import state.Play;

public class NPC extends Entity {
	private Animation npc;
	
	private ArrayList<String> speech;
	public NPC(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = PlayerData.PlayerWIDTH;
		this.height = PlayerData.PlayerHEIGHT;

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		 npc = Resources.getAnimation(DataPath.path_res_character_NPC+"npc.png",PlayerData.PlayerSheetWIDTH, PlayerData.PlayerSheetWIDTH, PlayerData.AnimationDuration);
		 try {
			avatar = new Image(DataPath.path_res_pic + "talker.png", false, Image.FILTER_LINEAR);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 speech = new  ArrayList<String>();
		 speech.add("1NPC:HELLO WORLD!");
		 speech.add("0褚斯:I am Programmer,I have no life.");
		 speech.add("0褚斯:我準備好了");
		 speech.add("1NPC:那我們可以開始了");	 
	}

	@Override
	public void render(GameContainer gc, Graphics g) {

		npc.draw(x, y,width, height);

	}

	@Override
	public void update(GameContainer gc, int delta) {

		if (this.hitTest(Play.player) && Play.pause == false) {
		if (Control.input.isKeyPressed(Control.CHAT)) {
			
				Play.talking = true;
				Play.addDialogText(Play.player.getAvatar(),getAvatar(),speech);
			 
		}
		}
	}

	private void chatFinish() {
		Play.pause = false;
	}

}
