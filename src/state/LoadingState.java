package state;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import BasicGame.Game;
import BasicGame.Resources;
import BasicGame.window;
import Data.Chapter;
import entities.Player.PlayerData;
import world.Map;

public class LoadingState extends BasicGameState {
	private Animation Loading;
	private Image load_image;
	private boolean load_finish = false;
	private boolean loading = false;
	private Play PlayState;

	public LoadingState(int menu) {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		Loading = new Animation(Resources.getSprite("loading"), PlayerData.AnimationDuration);
		arg1.getCurrentStateID();

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		Loading.draw(window.WIDTH - 200, window.HEIGHT - 100);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		if ((loading == false) && (load_finish == false)) {
			if (PlayState == null) {
				PlayState = (Play) sbg.getState(state.Play);
				// PlayState = new Play(state.Play);
				// sbg.addState(PlayState);
			
				PlayState.Load_Resources(Chapter.name[0]);
			//	load();
				loading = false;
				load_finish = true;
				

			}

		} else if (load_finish == true) {
			loading = false;
			load_finish = false;
			sbg.enterState(state.Play);
		}
		//************** ´ú¸Õ¥Î
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(state.Play);
		}
		

	}

	@Override
	public int getID() {

		// TODO Auto-generated method stub
		return state.Loading;
	}

	private void load() {
		new Thread() {
			public void run() {
				loading = true;
				
				// start load
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// load finish
				loading = false;
				load_finish = true;
			}

		}.start();
	}
}