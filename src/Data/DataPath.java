package Data;

public class DataPath {
	public static String path = (System.getProperty("user.dir")).replace("\\", "/");
	// path sample = C:\Users\123\Desktop\Lavateinn_project
	//******************res**************************
	public static String path_res_back = path + "/res/background/";
	public static String path_res_bgm = path + "/res/bgm/";
	public static String path_res_character = path + "/res/character/";
	public static String path_res_character_player = path + "/res/character/player/";
	public static String path_res_character_NPC = path + "/res/character/NPC/";
	public static String path_res_font = path + "/res/font/";
	public static String path_res_pic = path + "/res/pic/";
	public static String path_res_pic_UI = path + "/res/pic/UI/";
	public static String path_res_pic_trigger = path + "/res/pic/trigger/";
	public static String path_res_sound = path + "/res/sounds/";
	public static String path_res_map_tiled = path + "/res/tiled/";
	//*****************data**************************
	public static String path_data_chapter = path + "/data/chapter/";
	public static String path_data_entity = path + "/data/entity/";
	public static String path_data_monster = path + "/data/monster/";
	public static String path_data_player = path + "/data/player/";
	public static String path_data_save = path + "/data/save/";
}