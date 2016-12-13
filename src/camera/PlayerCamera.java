package camera;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import BasicGame.window;
import entities.Entity;
import entities.Player.Player;
import entities.Player.PlayerData;
import state.Play;
import world.Map;

public class PlayerCamera {

	private boolean scoller_right = false, scoller_left = false, scoller_up = false, scoller_down = false;
	private int polar_x_right = 0, polar_x_left = 0, polar_y_up = 0, polar_y_down = 0;
	public boolean scollering = false;
	public static int Camera_X, Camera_Y;
	private int Background_x, Background_y, translate_x, translate_y;
	private static boolean move = false;

	public PlayerCamera() {

	}

	public PlayerCamera(Player player) {

	}

	public void render(GameContainer gc, Graphics g) {
		if (move == false) {
			if (Map.getWIDTH() < window.WIDTH) {
				Camera_X = window.WIDTH / 2 - Map.getWIDTH() / 2;
				Background_x = Camera_X;
			} else {

				Camera_X = -Play.player.getCenterX() + window.WIDTH / 2;
				if ((Camera_X >= 0)) {
					Camera_X = 0;
				} else if ((-Camera_X + window.WIDTH >= Map.getWIDTH())) {
					Camera_X = -(Map.getWIDTH() - window.WIDTH);

				}
				Background_x = Camera_X / 2;
			}

			if (Map.getHEIGHT() < window.HEIGHT) {
				Camera_Y = window.HEIGHT / 2 - Map.getHEIGHT() / 2;
				Background_y = Camera_Y;
			} else {

				Camera_Y = -Play.player.getCenterY() + window.HEIGHT / 2;

				if ((Camera_Y >= 0)) {
					Camera_Y = 0;
				} else if ((-Camera_Y + window.HEIGHT >= Map.getHEIGHT())) {
					Camera_Y = -(Map.getHEIGHT() - window.HEIGHT);
				}
				Background_y = Camera_Y / 2;
			}
		} else {

			if (Map.getHEIGHT() < window.HEIGHT) {
				Background_y = Camera_Y;
			} else {
				Background_y = Camera_Y / 2;
			}
			if (Map.getWIDTH() < window.WIDTH) {
				Background_x = Camera_X;
			} else {
				Background_x = Camera_X / 2;
			}
		}
		Map.drawBackImage(Background_x, Background_y);
		g.translate(Camera_X, Camera_Y);

		// g.scale(1, 1);
		// *************************************************************************
		// 直接過圖
		// 玩家跨過右邊界

		/*
		 * if (player.testRight() && (player.getCenterX() >= window.WIDTH)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; }
		 * 
		 * scoller_right = true;
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if (scoller_right == true) {
		 * 
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).x -= window.WIDTH; }
		 * 
		 * Map.X -= window.WIDTH;
		 * 
		 * 
		 * scoller_right = false; for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false; }
		 * 
		 * }
		 * 
		 * // 玩家跨過左邊界 if (player.testLeft() && (player.getCenterX() <= 0 )) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; } scoller_left = true;
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if (scoller_left == true) {
		 * 
		 * 
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).x += window.WIDTH; }
		 * 
		 * Map.X += window.WIDTH;
		 * 
		 * scoller_left = false; for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false; }
		 * 
		 * }
		 * 
		 * // 玩家越過上邊界 if (player.testUp() && (player.getCenterY() <= 0)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; }
		 * 
		 * scoller_up = true;
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if (scoller_up == true) {
		 * 
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).y += window.HEIGHT; }
		 * 
		 * Map.Y += window.HEIGHT;
		 * 
		 * 
		 * scoller_up = false; for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false;
		 * 
		 * } }
		 * 
		 * // 玩家越過下邊界 if (player.testDown() && (player.getCenterY()>=
		 * window.HEIGHT)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; } scoller_down = true;
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if (scoller_down == true) {
		 * 
		 * 
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).y -= window.HEIGHT; }
		 * 
		 * Map.Y -= window.HEIGHT;
		 * 
		 * 
		 * scoller_down = false; for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false;
		 * 
		 * } }
		 */

		// ***********************************************************************************

		/*
		 * 緩慢捲動 // 玩家跨過右邊界
		 * 
		 * if (player.testRight() && (player.getEndX() >= window.WIDTH)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; }
		 * 
		 * scoller_right = true;
		 * 
		 * polar_x_right = 0;
		 * 
		 * }
		 * 
		 * if (scoller_right == true) {
		 * 
		 * polar_x_right += cameraData.scoller_speed;
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).x -= cameraData.scoller_speed; }
		 * 
		 * Map.startX -= cameraData.scoller_speed;
		 * 
		 * if (polar_x_right >= window.WIDTH / 2) { scoller_right = false; for
		 * (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false; } } }
		 * 
		 * // 玩家跨過左邊界 if (player.testLeft() && (player.x <= 0)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; } scoller_left = true;
		 * 
		 * polar_x_left = 0;
		 * 
		 * }
		 * 
		 * if (scoller_left == true) {
		 * 
		 * polar_x_left += cameraData.scoller_speed;
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).x += cameraData.scoller_speed; }
		 * 
		 * Map.startX += cameraData.scoller_speed;
		 * 
		 * if (polar_x_left >= window.WIDTH / 2) { scoller_left = false; for
		 * (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false; } } }
		 * 
		 * // 玩家越過上邊界 if (player.testUp() && (player.y <= 0)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; }
		 * 
		 * scoller_up = true;
		 * 
		 * polar_y_up = 0;
		 * 
		 * }
		 * 
		 * if (scoller_up == true) {
		 * 
		 * polar_y_up += cameraData.scoller_speed;
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).y += cameraData.scoller_speed; }
		 * 
		 * Map.startY += cameraData.scoller_speed;
		 * 
		 * if (polar_y_up >= window.HEIGHT / 2) { scoller_up = false; for (int i
		 * = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false; } } }
		 * 
		 * // 玩家越過下邊界 if (player.testDown() && (player.getEndY() >=
		 * window.HEIGHT)) {
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = true; } scoller_down = true;
		 * 
		 * polar_y_down = 0;
		 * 
		 * }
		 * 
		 * if (scoller_down == true) {
		 * 
		 * polar_y_down += cameraData.scoller_speed;
		 * 
		 * for (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).y -= cameraData.scoller_speed; }
		 * 
		 * Map.startY -= cameraData.scoller_speed;
		 * 
		 * if (polar_y_down >= window.HEIGHT / 2) { scoller_down = false; for
		 * (int i = 0; i < entities.size(); i++) {
		 * 
		 * entities.get(i).pause = false; } } }
		 */
	}

	public static void MoveCamera(int x, int y) {
		if (move == false) {
			move = true;
			Camera_X += x;
			Camera_Y += y;
		}
	}

	public static void MoveRecovery() {
		move = false;

	}
}
