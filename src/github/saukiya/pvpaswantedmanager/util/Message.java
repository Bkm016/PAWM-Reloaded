package github.saukiya.pvpaswantedmanager.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Message {
	private static YamlConfiguration messages;
	final static File messageFile = new File("plugins" + File.separator + "PVPAsWantedManager" + File.separator + "Message.yml");
	final public static String PLAYER_DEATH_TAKE_EXP = "Player.DeathTakeExp";
	final public static String PLAYER_ARREST_PUNISH = "Player.ArrestPunish";
	final public static String PLAYER_ARREST_PUNISH_FAIL = "Player.ArrestPunishFai";
	
	final public static String ADMIN_NO_PER_CMD = "Admin.NoPermissionCommand";
	final public static String ADMIN_NO_CMD = "Admin.NoCommand";
	final public static String ADMIN_NO_FORMAT = "Admin.NoFormat";
	final public static String ADMIN_NO_ONLINE = "Admin.NoOnline";
	final public static String ADMIN_NO_CONSOLE = "Admin.NoConsole";
	final public static String ADMIN_RELOAD = "Admin.PluginReload";
	
	final public static String COMMAND_OPEN = "Command.open";
	final public static String COMMAND_JOIN_JAIL = "Command.joinJail";
	final public static String COMMAND_QUIT_JAIL = "Command.quitJail";
	final public static String COMMAND_SET_JAIL = "Command.setJail";
	final public static String COMMAND_RELOAD = "Command.reload";
	
	static public void createMessage(){
        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §cCreate Message.yml");
		messages = new YamlConfiguration();
		messages.set(PLAYER_DEATH_TAKE_EXP, String.valueOf("&8[&4死亡&8] &7你因为PK值导致额外扣取 &c{0}&7 经验等级"));
		messages.set(PLAYER_ARREST_PUNISH, String.valueOf("&8[&c通缉&8] &7因为坐牢而扣取 &c{0}&7金币!"));
		messages.set(PLAYER_ARREST_PUNISH_FAIL, String.valueOf("&8[&c通缉&8] &c金币不足!增加监狱时长: &6{0}&c分钟!"));
		
		messages.set(ADMIN_NO_PER_CMD, String.valueOf("&8[&d通缉&8] &c你没有权限执行此指令"));
		messages.set(ADMIN_NO_CMD, String.valueOf("&8[&d通缉&8] &c未找到此子指令:{0}"));
		messages.set(ADMIN_NO_FORMAT, String.valueOf("&8[&d通缉&8] &c格式错误!"));
		messages.set(ADMIN_NO_ONLINE, String.valueOf("&8[&d通缉&8] &c玩家不在线或玩家不存在!"));
		messages.set(ADMIN_NO_CONSOLE, String.valueOf("&8[&d通缉&8] &c控制台不允许执行此指令!"));
		messages.set(ADMIN_RELOAD, String.valueOf("&8[&d通缉&8] §c插件已重载"));
		
		messages.set(COMMAND_OPEN, String.valueOf("打开通缉菜单"));
		messages.set(COMMAND_JOIN_JAIL, String.valueOf("让玩家进入监狱"));
		messages.set(COMMAND_QUIT_JAIL, String.valueOf("让玩家离开监狱"));
		messages.set(COMMAND_SET_JAIL, String.valueOf("设置监狱位置坐标"));
		messages.set(COMMAND_RELOAD, String.valueOf("重新加载这个插件的配置"));
		try {messages.save(messageFile);} catch (IOException e) {e.printStackTrace();}
	}
	
	static public void loadMessage(){
		if(!messageFile.exists()){
				createMessage();
		}else{
	        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §aFind Message.yml");
		}
		messages = new YamlConfiguration();
		try {messages.load(messageFile);} catch (IOException | InvalidConfigurationException e) {e.printStackTrace();Bukkit.getConsoleSender().sendMessage("§8[§6PVPAsWantedManager§8] §a读取message时发生错误");}
	}
	

	public static String getMsg(String loc,String... args){
		String raw = messages.getString(loc);
		if (raw == null || raw.isEmpty()) {
			return "Null Message: " + loc;
		}
		raw = raw.replaceAll("&", "§");
		if (args == null) {
			return raw;
		}
		ArrayList<String> replaceList = Message.getList("replace");
		for (int i = 0; i < args.length; i++) {
			for(int l=0; l <replaceList.size();l++){
				String str = replaceList.get(l);
				String str1 = str.split(":")[0];
				String str2 = str.split(":")[1].replace("&", "§");
				if(args[i].equals(str1))args[i] = args[i].replace(str1, str2);
			}
			raw = raw.replace("{" + i + "}", args[i]==null ? "null" : args[i]);
		}
		
		return raw;
	}
	
	public static ArrayList<String> getList(String loc,String... args){
		ArrayList<String> list = (ArrayList<String>) messages.getStringList(loc);
		ArrayList<String> elist = new ArrayList<String>();
		if (list == null || list.isEmpty()) {
			list.add("Null Message: " + loc);
			return list;
		}
		if (args == null) {
			for(int e= 0;e <list.size();){
				elist.add(list.get(e).replace("&", "§"));
				e++;
			}
			return elist;
		}else{
			
		}
		//循环lore
		for(int e= 0;e <list.size();){
			String lore = list.get(e);
			for (int i= 0; i < args.length;i++){
				lore = lore.replace("&", "§").replace("{" + i + "}", args[i]==null ? "null" : args[i]);
			}
			elist.add(lore);
			e++;
		}
		return elist;
	}
	
	public static void playerTitle(Player player,String loc){
		String str = messages.getString(loc).replace("&", "§");
		if(str.contains(":")){
			player.sendTitle(str.split(":")[0], str.split(":")[1], 2, 30, 3);
		}else{
			player.sendTitle(str, "", 2, 30, 3);
		}
	}
}
