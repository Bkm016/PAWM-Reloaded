package github.saukiya.pvpaswantedmanager.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;


public class Config {
	private static YamlConfiguration config;
	final static File configFile = new File("plugins" + File.separator + "PVPAsWantedManager" + File.separator + "Config.yml");
	final public static String LANGUAGE = "Language";
	final public static String ASWANTED_ARREST_BROAD_CAST = "AsWantedArrestBroadCast";
	final public static String DEATH_PENALIZE_ENABLED = "DeathPenalize.Enabled";
	final public static String DEATH_PENALIZE_LEVEL = "DeathPenalize.Level";
	final public static String CANCELL_ASWANTED_TARGET_ENABLED = "CancellAsWantedTarget.Enabled";
	final public static String CANCELL_ASWANTED_TARGET_MONEY = "CancellAsWantedTarget.Money";
	final public static String TASKREWARD_BASICMONEY = "TaskReward.BasicMoney";
	final public static String TASKREWARD_MONEY = "TaskReward.Money";
	final public static String ARREST_PUNISH_ENABLED = "ArrestPunish.Enabled";
	final public static String SURREND_PLAYER_ENABLED = "SurrendPlayer.enabled";
	final public static String NO_TASK_ENABLED = "NoTask.enabled";
	final public static String GUI_ID_GLASS = "Gui.ID.Glass";
	final public static String GUI_ID_PAGEDOWN = "Gui.ID.PageDown";
	final public static String GUI_ID_PAGEUP = "Gui.ID.PageUp";
	final public static String GUI_ID_JAILINFO = "Gui.ID.JailInfo";
	final public static String GUI_ID_PVPPROTECT = "Gui.ID.PVPProtect";
	final public static String GUI_ID_SURREND = "Gui.ID.Surrend";
	final public static String GUI_ID_QUIT = "Gui.ID.Quit";
	final public static String GUI_ID_CANCELLTARGET = "Gui.ID.CancellTarget";
	final public static String TIMETICK_WANTED_PLAYER_TIME_DEDUCTION = "TimeTick.WantedPlayerTimeDeduction";
	final public static String TIMETICK_JAIL_PLAYER_TIMES = "TimeTick.JailPlayerTimes";
	final public static String TIMETICK_TARGET_TIME_MESSAGE = "TimeTick.TargetTimeMessage";
	final public static String PLAYER_NOVICE_PROTECTION_ENABLED = "PlayerNoviceProtection.Enabled";
	final public static String PLAYER_NOVICE_PROTECTION_TIMES = "PlayerNoviceProtection.Times";
	final public static String WHITE_LIST_WORLD = "WhiteListWorld";
	final public static String JAIL_EM_DAMAGE = "Jail.EventManager.Damage";
	final public static String JAIL_EM_COMMAND = "Jail.EventManager.Command";
	final public static String JAIL_EM_WHITELIST_CMD = "Jail.EventManager.WhiteListCmd";
	final public static String JAIL_EM_CHAT = "Jail.EventManager.Chat";
	final public static String JAIL_EM_DROPITEM = "Jail.EventManager.DropItem";
	final public static String JAIL_EM_PICKUPITEM = "Jail.EventManager.PickUpItem";
	final public static String JAIL_EM_INTERACT = "Jail.EventManager.Interact";
	final public static String JAIL_EM_PORTAL = "Jail.EventManager.Portal";
	final public static String JAIL_LOCATION_X = "Jail.Location.X";
	final public static String JAIL_LOCATION_Y = "Jail.Location.Y";
	final public static String JAIL_LOCATION_Z = "Jail.Location.Z";
	final public static String JAIL_LOCATION_WORLD = "Jail.Location.World";
	
	static public void createConfig(){
        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §cCreate Config.yml");
		config = new YamlConfiguration();
		config.set(LANGUAGE, String.valueOf("CN"));
		config.set(ASWANTED_ARREST_BROAD_CAST, Boolean.valueOf(true));
		config.set(DEATH_PENALIZE_ENABLED, Boolean.valueOf(true));
		config.set(DEATH_PENALIZE_LEVEL, Double.valueOf(1));
		config.set(CANCELL_ASWANTED_TARGET_ENABLED, Boolean.valueOf(true));
		config.set(CANCELL_ASWANTED_TARGET_MONEY, Integer.valueOf(100));
		config.set(TASKREWARD_BASICMONEY, Integer.valueOf(500));
		config.set(TASKREWARD_MONEY, Integer.valueOf(200));
		config.set(ARREST_PUNISH_ENABLED, Boolean.valueOf(true));
		config.set(SURREND_PLAYER_ENABLED, Boolean.valueOf(true));
		config.set(NO_TASK_ENABLED, Boolean.valueOf(false));
		config.set(GUI_ID_GLASS, String.valueOf("160:15"));
		config.set(GUI_ID_PAGEDOWN, Integer.valueOf("262"));
		config.set(GUI_ID_PAGEUP, Integer.valueOf("262"));
		config.set(GUI_ID_JAILINFO, Integer.valueOf("347"));
		config.set(GUI_ID_PVPPROTECT, Integer.valueOf("299"));
		config.set(GUI_ID_SURREND, Integer.valueOf("386"));
//		if(PVPAsWantedManager.versionValue >= 188){
//			config.set(GUI_ID_QUIT, Integer.valueOf("166"));
//		}else{
			config.set(GUI_ID_QUIT, Integer.valueOf("324"));
//		}
		config.set(GUI_ID_CANCELLTARGET, Integer.valueOf("352"));
		config.set(TIMETICK_WANTED_PLAYER_TIME_DEDUCTION, String.valueOf("30min"));
		config.set(TIMETICK_JAIL_PLAYER_TIMES, String.valueOf("20min"));
		config.set(TIMETICK_TARGET_TIME_MESSAGE, String.valueOf("1min"));
		config.set(PLAYER_NOVICE_PROTECTION_ENABLED, Boolean.valueOf(true));
		config.set(PLAYER_NOVICE_PROTECTION_TIMES, String.valueOf("300min"));
		ArrayList<String> worldWhite = new ArrayList<String>();
		worldWhite.add("pvpworld");
		config.set(WHITE_LIST_WORLD, worldWhite);
		config.set(JAIL_EM_DAMAGE,Boolean.valueOf(true));
		config.set(JAIL_EM_COMMAND,Boolean.valueOf(true));
		ArrayList<String> listCmd = new ArrayList<String>();
		listCmd.add("pawm");
		listCmd.add("bal");
		listCmd.add("list");
		listCmd.add("t");
		listCmd.add("tell");
		listCmd.add("msg");
		listCmd.add("login");
		listCmd.add("l");
		config.set(JAIL_EM_WHITELIST_CMD, listCmd);
		config.set(JAIL_EM_CHAT,Boolean.valueOf(false));
		config.set(JAIL_EM_DROPITEM,Boolean.valueOf(true));
		config.set(JAIL_EM_PICKUPITEM,Boolean.valueOf(true));
		config.set(JAIL_EM_INTERACT,Boolean.valueOf(true));
		config.set(JAIL_EM_PORTAL,Boolean.valueOf(true));
		config.set(JAIL_LOCATION_X, Integer.valueOf(0));
		config.set(JAIL_LOCATION_Y, Integer.valueOf(80));
		config.set(JAIL_LOCATION_Z, Integer.valueOf(0));
		config.set(JAIL_LOCATION_WORLD, String.valueOf("world"));
		try {config.save(configFile);} catch (IOException e) {e.printStackTrace();}
		config = new YamlConfiguration();
		try {config.load(configFile);} catch (IOException | InvalidConfigurationException e) {e.printStackTrace();Bukkit.getConsoleSender().sendMessage("§8[§6PVPAsWantedManager§8] §a读取config时发生错误");}
	}
	
	static public void loadConfig(){
		if(!configFile.exists()){
			createConfig();
			return;
		}else{
	        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §aFind Config.yml");
		}
		config = new YamlConfiguration();
		try {config.load(configFile);} catch (IOException | InvalidConfigurationException e) {e.printStackTrace();Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §a读取config时发生错误");}
	}

	public static String getConfig(String loc){
		String raw = config.getString(loc);
		if(raw == null || raw.isEmpty()){
			Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §cConfig.yml 文件报错！! 位置: §a"+loc);
			return null;
		}
		if(raw.contains("min")){
			raw = raw.replace("min", "");
		}else if(raw.contains("%")){
			raw = raw.replace("%", "");
		}
		
		return raw;
	}
	public static List<String> getList(String loc){
		List<String> list = config.getStringList(loc);
		if (list == null || list.isEmpty()) {
			Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §cConfig.yml 文件报错！! 位置: §a"+loc);
			return null;
		}
		
		return list;
	}
	
	
	public static void setConfig(String loc , Object arg){
		config.set(loc, arg);
		try {config.save(configFile);} catch (IOException e) {e.printStackTrace();}
	}
}
