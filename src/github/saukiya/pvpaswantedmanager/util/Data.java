package github.saukiya.pvpaswantedmanager.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Data {
	private static YamlConfiguration data;
	final static File dataFile = new File("plugins" + File.separator + "PVPAsWantedManager" + File.separator + "Data.dat");
	public static HashMap<String,PlayerData> dataMap = new HashMap<String,PlayerData>();
	private static List<String> WantedList = new ArrayList<String>();
	static public void loadData(){
		//检测data.dat是否存在
		if(!dataFile.exists()){
	        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §cCreate Data.dat");
			data = new YamlConfiguration();
			data.set("WantedList", WantedList);
			try {data.save(dataFile);} catch (IOException e) {e.printStackTrace();}
		}else{
	        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §aFind Data.dat");
		}
		data = new YamlConfiguration();
		try {data.load(dataFile);} catch (IOException | InvalidConfigurationException e) {e.printStackTrace();Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §c读取 Data.yml 时发生错误");}
		WantedList = data.getStringList("WantedList");
	}
	static void saveData(){
		try {data.save(dataFile);} catch (IOException e) {e.printStackTrace();}
	}
	static public void addWantedList(String playerName){
		if(WantedList.contains(playerName))return;
		WantedList.add(playerName);
		data.set("WantedList", WantedList);
		saveData();
	}
	static public void removeWantedList(String playerName){
		if(!WantedList.contains(playerName))return;
		WantedList.remove(playerName);
		data.set("WantedList", WantedList);
		saveData();
	}
	static public PlayerData getPlayerData(String playerName){
		if(dataMap.containsKey(playerName)){
			return dataMap.get(playerName);
		}else{
			return PlayerData.load(playerName);
		}
	}
	static public Boolean isJail(PlayerData data){
		return data.getJ_Times() > 0;
	}
	static public Boolean isWanted(PlayerData data){
		return data.getW_Points() >0;
	}
	static public Boolean isAsWanted(String playerName,PlayerData data){
		if(Config.getConfig(Config.NO_TASK_ENABLED).equalsIgnoreCase("true"))return true;
		return data.getAw_Target().equalsIgnoreCase(playerName);
	}
	static public Boolean isOn(String worldName){
		for(String world: Config.getList(Config.WHITE_LIST_WORLD)){
			if(world.equalsIgnoreCase(worldName))return false;
		}
		return true;
	}
	static public Boolean isNovice(PlayerData playerData){
		if(!Config.getConfig(Config.PLAYER_NOVICE_PROTECTION_ENABLED).equalsIgnoreCase("true"))return false;
		return playerData.getOnline_Times() <= Integer.valueOf(Config.getConfig(Config.PLAYER_NOVICE_PROTECTION_TIMES));
	}
	static public void takePlayerLevel(Player player , int playerWantedPoints){
		if(player.hasPermission("pvpaswantedmanager.levelwhite"))return;
		if(!Config.getConfig(Config.DEATH_PENALIZE_ENABLED).equalsIgnoreCase("true"))return;
		if(player.getExp() ==0 &&player.getLevel() == 0)return;
		Double value = Double.valueOf(Config.getConfig(Config.DEATH_PENALIZE_LEVEL));
		if(value !=1){
			DecimalFormat    df   = new DecimalFormat("######0.0");
			value = Double.valueOf(df.format(value*playerWantedPoints));
			String str = String.valueOf(value).replace(".", "-");
			playerWantedPoints = Integer.valueOf(str.split("-")[0]);
			float exp = Float.valueOf("0."+str.split("-")[1]);
			if(exp != (int)exp){
				exp = player.getExp()-exp;
				if(exp < 0){
					playerWantedPoints ++;
					exp ++;
				}
				player.setExp(exp);
			}
			int level = player.getLevel() - playerWantedPoints;
			if(level<0){
				level=0;
				player.setExp(0);
			}
			player.setLevel(level);
			player.sendMessage(Message.getMsg(Message.PLAYER_DEATH_TAKE_EXP,String.valueOf(value)));
		}else{
			int level = player.getLevel() - playerWantedPoints;
			if(level<0)level=0;
			player.setLevel(level);
			player.sendMessage(Message.getMsg(Message.PLAYER_DEATH_TAKE_EXP,String.valueOf(playerWantedPoints)));
		}
	}
}
