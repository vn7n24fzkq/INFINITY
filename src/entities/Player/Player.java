package entities.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import BasicGame.Control;
import BasicGame.Listener;
import BasicGame.Resources;
import BasicGame.SoundPlayer;
import Data.DataPath;
import camera.PlayerCamera;
import entities.Box;
import entities.Entity;
import entities.EntityData;
import entities.Walk_Organics;
import entities.Bullet.Bullet;
import state.Play;
import world.Map;

public class Player extends Walk_Organics {

	private int flickering = 0;
	private int bullet_count = 6;

	// attack
	// private Animation
	// move
	private Animation life_full_sprite, life_empty_sprite;

	private Animation StandRight, StandLeft, WalkRight, WalkLeft, JumpRight, JumpLeft, SquatRight, SquatLeft,
			Z_AttackRight, Z_AttackLeft, X_AttackRight, X_AttackLeft, HurtRight, HurtLeft;
	private String[] AnimationName = new String[] { "StandRight", "StandLeft", "WalkRight", "WalkLeft", "JumpRight",
			"JumpLeft", "Z_AttackRight", "Z_AttackLeft", "X_AttackRight", "X_AttackLeft", "HurtRight", "HurtLeft" };

	private Animation animation;

	private byte Motion = PlayerData.Motion_Stand;
	// private SoundPlayer Sound_player;

	private HashMap<String, Animation> sprites;
	private boolean attacking = false, can_attack = true, hurtting = false;
	private UnicodeFont Font;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.face = PlayerData.Face_Right;
		this.health = 3;
		try {
			avatar = new Image(DataPath.path_res_character_player + "avatar.png", false, Image.FILTER_LINEAR);
			String fontPath = DataPath.path_res_font + "gomarice_nanikano_capsule.ttf";
			Font = new UnicodeFont(fontPath, 12, true, true);
			Font.addAsciiGlyphs();
			Font.addGlyphs(0, 0);
			Font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			Font.loadGlyphs();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Player() {

		sprites = new HashMap<String, Animation>();
		read_resources(AnimationName);

		// ¦å¶q
		life_full_sprite = new Animation(Resources.getSprite("life_full"), PlayerData.AnimationDuration);
		life_empty_sprite = new Animation(Resources.getSprite("life_empty"), PlayerData.AnimationDuration);

		width = PlayerData.PlayerWIDTH;
		height = PlayerData.PlayerHEIGHT;

		AnimationSet();
		/*
		 * try { SoundSet(); } catch (SlickException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// change animation from switch motion

		switch (Motion) {

		case PlayerData.Motion_Stand:

			if (face == PlayerData.Face_Right) {
				animation = StandRight;

			} else {

				animation = StandLeft;

			}

			break;

		case PlayerData.Motion_Walk:

			if (face == PlayerData.Face_Right) {
				WalkRight.update(20);
				WalkRight.setPingPong(true);
				animation = WalkRight;

			} else {
				WalkLeft.update(20);
				WalkLeft.setPingPong(true);
				animation = WalkLeft;

			}

			// Motion = PlayerData.Motion_Stand;
			break;

		case PlayerData.Motion_Jump:
			if (face == PlayerData.Face_Right) {
				animation = JumpRight;

			} else {

				animation = JumpLeft;
			}

			break;
		case PlayerData.Motion_Falling:
			if (face == PlayerData.Face_Right) {
				animation = JumpRight;

			} else {

				animation = JumpLeft;
			}

			break;
		case PlayerData.Motion_Squat:

			if (face == PlayerData.Face_Right) {

				animation = SquatRight;

			} else {

				animation = SquatLeft;

			}

			break;
		case PlayerData.Motion_Attack_X:

			if (face == PlayerData.Face_Right) {

				animation = X_AttackRight;

				attacking = action(animation, PlayerData.Player_attack_speed, PlayerData.AnimationUpdate, false, false);

			} else {

				animation = X_AttackLeft;
				attacking = action(animation, PlayerData.Player_attack_speed, PlayerData.AnimationUpdate, false, false);

			}

			break;

		case PlayerData.Motion_Hurt:

			if (face == PlayerData.Face_Right) {
				flickering = 0;
				animation = HurtRight;

				hurtting = action(animation, PlayerData.Player_hurtAnimation_speed, PlayerData.AnimationUpdate, false,
						false);

			} else {
				flickering = 0;
				animation = HurtLeft;
				hurtting = action(animation, PlayerData.Player_hurtAnimation_speed, PlayerData.AnimationUpdate, false,
						false);

			}

			break;

		default:

			System.out.println("no Motion!!\n" + "x:" + x + "---" + "y:" + y);

		}
		if (Play.pause == false) {
			animation.start();
			if (Invincible == true) {
				flickering += 1;
				if (((flickering) >= 0 && (flickering) <= 5) || ((flickering) >= 10 && (flickering) <= 15)
						|| ((flickering) >= 20 && (flickering) <= 25) || ((flickering) >= 30 && (flickering) <= 35)
						|| ((flickering) >= 40 && (flickering) <= 45)) {
					animation.draw(x, y, width, height);
				} else if ((flickering) >= 45) {
					flickering = 0;
				}

			} else {

				animation.draw(x, y, width, height);
			}
		} else {
			animation.stop();
			animation.draw(x, y, width, height);
		}
		// System.out.println("x:" + x + "-*--" + "y:" + y);
		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
	};

	@Override
	public void update(GameContainer gc, int delta) {
	
		Motion = PlayerData.Motion_Stand;

		BasicControl(Control.input, delta);

		Falling(PlayerData.Speed_ComeDown, delta);

		if (Falling) {
			Motion = PlayerData.Motion_Falling;
		}
		if (jumping) {
			Motion = PlayerData.Motion_Jump;
			jump(PlayerData.Speed_Jump, PlayerData.Player_JumpHEIGHT, delta);
		}
		if (attacking) {
			Motion = PlayerData.Motion_Attack_X;

		}
		if (hurtting) {
			Motion = PlayerData.Motion_Hurt;
		}

		if (health <= 0) {
			relive();
		}

	}

	private void BasicControl(Input input, int delta) {
		if (Mobility == true) {
			if (input.isKeyDown(Control.LEFT)) {

				face = EntityData.Motion_FaceLeft;
				walk(face, delta, PlayerData.Speed_Move);
				Motion = PlayerData.Motion_Walk;

			} else if (input.isKeyDown(Control.RIGHT)) {

				face = EntityData.Motion_FaceRight;
				walk(face, delta, PlayerData.Speed_Move);
				Motion = PlayerData.Motion_Walk;

			}
			if (input.isKeyDown(Control.UP)) {
				// PlayerCamera.MoveCamera(0, 100);
			} else if (input.isKeyPressed(Control.UP)) {
				// PlayerCamera.MoveRecovery();
			}
			/*
			 * if (input.isKeyPressed(Control.DOWN)) { //
			 * PlayerCamera.MoveCamera(0, -100); } else if
			 * (input.isKeyPressed(Control.DOWN)) { //
			 * PlayerCamera.MoveRecovery(); DownPlatform = true; }
			 */
			if (input.isKeyPressed(Control.DOWN)) {
				DownPlatform = true;
			}
			if (input.isKeyPressed(Control.JUMP)) {
				if (!testDown() && testUp() && jumping == false) {
					jumping = true;
				}
			}
			/*
			 * if (input.isKeyPressed(Control.CHAT)) { Motion =
			 * PlayerData.Motion_Attack_Z; }
			 */
			if (input.isKeyDown(Control.ATTACK)) {
				attack();
			}
		}

	}

	// jumpProcess
	@Override
	protected void jump(float jump_speed, int jump_height, int delta) {

		super.jump(jump_speed, jump_height, delta);

	}

	private void attack() {

		if (can_attack == true) {
			if (bullet_count > 0) {
				Motion = PlayerData.Motion_Attack_X;
				attacking = true;
				attackColdDown(PlayerData.attack_ColdDown);
				Play.addBullet(new Bullet(face, getCenterX(), getCenterY(), PlayerData.bullet_WIDTH,
						PlayerData.bullet_HEIGHT));
				bullet_count -= 1;
			} else {
				bullet_count = 6;
				attackColdDown(PlayerData.attack_ColdDown);
			}
		}
	}

	private void AnimationSet() {

		StandRight = sprites.get("StandRight");
		StandLeft = sprites.get("StandLeft");
		WalkRight = sprites.get("WalkRight");
		WalkLeft = sprites.get("WalkLeft");
		JumpRight = sprites.get("JumpRight");
		JumpLeft = sprites.get("JumpLeft");
		SquatRight = sprites.get("SquatRight");
		SquatLeft = sprites.get("SquatLeftt");

		Z_AttackRight = sprites.get("Z_AttackRight");
		Z_AttackLeft = sprites.get("Z_AttackLeft");
		X_AttackRight = sprites.get("X_AttackRight");
		X_AttackLeft = sprites.get("X_AttackLeft");
		HurtRight = sprites.get("HurtRight");
		HurtLeft = sprites.get("HurtLeft");

		JumpRight.setLooping(false);
		JumpLeft.setLooping(false);
		// ¦å¶q
		life_full_sprite = new Animation(Resources.getSprite("life_full"), PlayerData.AnimationDuration);
		life_empty_sprite = new Animation(Resources.getSprite("life_empty"), PlayerData.AnimationDuration);
	}

	/*
	 * private void SoundSet() throws SlickException { Sound_movement = new
	 * Sound(Data.DataPath.path_res_sound + "/003.ogg"); Sound_Z_attack = new
	 * Sound(Data.DataPath.path_res_sound + "/short_punch1.ogg"); Sound_jump =
	 * new Sound(Data.DataPath.path_res_sound + "/jump.ogg");
	 * 
	 * }
	 */
	private boolean action(Animation a, float speed, long update, boolean AutoUpdate, boolean Looping) {

		a.setSpeed(speed);
		a.setAutoUpdate(AutoUpdate);
		a.setLooping(Looping);
		Mobility = false;

		if (AutoUpdate == false) {
			a.update(update);
		}

		// System.out.println(a.getFrame());
		// System.out.println("count" + a.getFrameCount());
		// System.out.println(Motion );
		if (a.getFrame() == a.getFrameCount() - 1) {

			a.restart();
			Mobility = true;
			return false;
			// a.setCurrentFrame(0);
		}
		return true;
	}

	private void SoundPlay(Sound sound, float speed, float volume) {
		if (!sound.playing()) {
			sound.play(speed, volume);
		}
	}

	public void Player_UI_draw() {
		int life_draw = this.health;
		int life_draw_x = 40;
		int life_draw_y = 40;
		for (int i = 0; i < 3; i++) {
			if (life_draw > 0) {
				life_full_sprite.draw(life_draw_x, life_draw_y, 20, 16);
				life_draw -= 1;
			} else {
				life_empty_sprite.draw(life_draw_x, life_draw_y, 20, 16);
			}
			life_draw_x += 20;
		}
		Font.drawString(life_draw_x, life_draw_y, Integer.toString(bullet_count), Color.cyan);

	}

	private void attackColdDown(final int ms_time) {
		new Thread() {
			public void run() {
				can_attack = false;

				try {
					this.sleep(ms_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				can_attack = true;

			}
		}.start();
	}

	private void read_resources(String[] sources) {

		JSONParser parser = new JSONParser();

		try {
			for (int i = 0; i < sources.length; i++) {
				sprites.put(sources[i],
						Resources.getAnimation(Data.DataPath.path_res_character + "/player/" + sources[i] + ".png",
								PlayerData.PlayerSheetWIDTH, PlayerData.PlayerSheetWIDTH,
								PlayerData.AnimationDuration));
			}

			/*
			 * Object objj = parser.parse(new
			 * FileReader(DataPath.path_data_player + "/player.json"));
			 * 
			 * JSONObject jsonObject = (JSONObject) objj;
			 * 
			 * /* for (String element : sources) { System.out.println((String)
			 * jsonObject.get(element));
			 * 
			 * }
			 */
			/*
			 * JSONArray msg = (JSONArray) jsonObject.get("AnimationName");
			 * Iterator<String> iterator = msg.iterator(); while
			 * (iterator.hasNext()) { String name = iterator.next();
			 * sprites.put(name, Resources.getAnimation(
			 * Data.DataPath.path_res_character + "/player/" + (String)
			 * jsonObject.get(name), PlayerData.PlayerSheetWIDTH,
			 * PlayerData.PlayerSheetWIDTH, PlayerData.AnimationDuration)); }
			 */

			/*
			 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
			 * (IOException e) { e.printStackTrace(); } catch (ParseException e)
			 * { e.printStackTrace();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void relive() {
		x = start_x;
		y = start_y;
		health = 3;

	}

	public void setStartDot(int x, int y){
		this.x= x;
		this.y = y;
		this.start_x = x;
		this.start_y = y;
	}
	@Override
	public void getHurt(int i) {
		if (Invincible == false) {
			hurtting = true;
			health -= i;
			super.setInvincuble(1000);
		}

	}

	@Override
	public boolean testDown() {
		if ((Map.hitTest(getCenterX(), getEndY() + 1)) && (Map.hitTest(getEndX(), getEndY() + 1))
				&& (Map.hitTest(x, getEndY() + 1))) {
			if (DownPlatform) {
				if (DownPlatform && testDownPlatform()) {
					DownPlatform = false;
				}
				return true;
			}

			return testDownPlatform();

		}
		DownPlatform = false;
		return false;
	}

	private boolean DownPlatform = false;

	private boolean testDownPlatform() {

		if ((Map.PlatformHitTest(getCenterX(), getEndY() + 1)) && (Map.PlatformHitTest(getEndX(), getEndY() + 1))
				&& (Map.PlatformHitTest(x, getEndY() + 1))) {
			return true;
		}

		return false;
	}

	public boolean hitTest(Box b) {
		// TODO Auto-generated method stub
		if (super.hitTest(b)) {
			if (b.getClass().getName() == "entities.Monster.Monster") {
				getHurt(1);
			}
		}
		return false;
	}

}