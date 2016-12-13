package BasicGame;

import java.awt.Frame;

import java.awt.Toolkit;
import java.awt.im.InputContext;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Data.DataPath;
import state.LoadingState;
import state.Menu;
import state.Play;
import state.StartMenu;
import state.state;


public class Game extends StateBasedGame {
//	public static  Listener keyListener = new Listener();
	
	public Game(String name) {
		super(name);
		this.addState(new StartMenu(state.StartMenu));
		this.addState(new Play(state.Play));
		this.addState(new LoadingState(state.Loading));
		this.addState(new Menu(state.Menu));

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		new Resources();
	//	this.getState(state.StartMenu).init(gc, this);
	//	this.getState(state.Play).init(gc, this);
	//	this.getState(state.Menu).init(gc, this);
	//	this.enterState(state.StartMenu);
		// gc.setMouseGrabbed(true);
		Control.input = gc.getInput();
	
		
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game(window.gamename));
			
		app.setDisplayMode(window.WIDTH, window.HEIGHT, window.full);
		// .setDisplayMode(¼e,°ª, ¬O§_¥þ¿Ã¹õ);
	//	app.setAlwaysRender(true);
	//	app.setMinimumLogicUpdateInterval(10);
		app.setAlwaysRender(false);
		app.setMinimumLogicUpdateInterval(15);
		app.setMaximumLogicUpdateInterval(15);
		app.setTargetFrameRate(window.FPS);
		app.setShowFPS(false);
		app.setAlwaysRender(false);
		
	//	app.setVSync(true);
		//app.setSmoothDeltas(true);
		//app.setDefaultMouseCursor();
	//	app.setMouseGrabbed(true);
	//	Mouse.setGrabbed(true);
	
		try {

			app.start();
			
			//app.getInput().addKeyListener(keyListener);
		} catch (Exception e) {
/*
			FileWriter fw;
			try {
				fw = new FileWriter(DataPath.path+"/toast.txt");
				BufferedWriter bw = new BufferedWriter(fw, 20);
				bw.write(e.toString());
				bw.newLine();
				bw.flush();
				bw.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
*/
		}
	}
	

}