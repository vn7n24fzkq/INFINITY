package entities.Player;

public class PlayerData {

	public static final byte PlayerWIDTH = 40;
	public static final byte PlayerHEIGHT = 40;

	public static final byte PlayerSheetWIDTH = 80;
	public static final byte PlayerSheetHEIGHT = 80;

	public static final byte Face_Right = 0;
	public static final byte Face_Left = 1;

	public static final float Speed_Move = 0.15f;
	public static final float Speed_Jump = 0.25f;
	public static final float Speed_ComeDown = 0.2f;

	public static final int AnimationDuration = 200;
	public static final long AnimationUpdate = 20;
	
	public static final byte Motion_FaceRight = 0;
	public static final byte Motion_FaceLeft = 1;
	public static final byte Motion_Stand = 2;
	public static final byte Motion_Walk = 3;
	public static final byte Motion_Jump = 4;
	public static final byte Motion_Squat = 5;
	public static final byte Motion_Attack_Z = 6;
	public static final byte Motion_Attack_X= 7;
	public static final byte Motion_Hurt = 8;
	public static final byte Motion_Falling = 9;
	
	
	public static final int Player_JumpHEIGHT = 40;
	public static final float Player_attack_speed =1.5f;
	public static final float  Player_hurtAnimation_speed =1f;
	
	public static final int attack_ColdDown = 500;
	
	public static final short bullet_WIDTH = 10;
	public static final short bullet_HEIGHT = 10;
}