package github.saukiya.pvpaswantedmanager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/*本类由 Saukiya 在 2018年2月14日 下午3:47:35 时创建
 *TIM:admin@Saukiya.cn
 *GitHub:https://github.com/Saukiya
**/

//@SuppressWarnings("unused")
public class PlayerData {
//	a = Wanted
//	j = Jail
//	aw = AsWanted
//	a = Attribute
	private String player_Name = "";
	private int w_Points = 0;
	private int w_CumulativePoints = 0;
	private int w_HighestPoints = 0;
	private int j_Times = 0;
	private int j_CumulativeNumber = 0;
	private String aw_Target = "N/A";
	private int aw_CumulativeNumber = 0;
	private int aw_ContinuityNumber = 0;
	private Location a_loc  = new Location(Bukkit.getWorlds().get(0),0,60,0);
	private int online_Times = 0;
	public static PlayerData load(String playerName){
		File DataFile = new File("plugins" + File.separator + "PVPAsWantedManager" + File.separator + "PlayerData" + File.separator + playerName + ".yml");
		PlayerData playerData = new PlayerData();
		playerData.player_Name = playerName;
		   if(DataFile.exists()){
				YamlConfiguration data = new YamlConfiguration();
				try {data.load(DataFile);} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (InvalidConfigurationException e) {e.printStackTrace();}
				playerData.w_Points = data.getInt("wanted.points");
				playerData.w_CumulativePoints = data.getInt("wanted.cumulativePoints");
				playerData.w_HighestPoints = data.getInt("wanted.highestPoints");
				playerData.j_Times = data.getInt("jail.times");
				playerData.j_CumulativeNumber = data.getInt("jail.cumulativeNumber");
				playerData.aw_Target = data.getString("asWanted.target");
				playerData.aw_CumulativeNumber = data.getInt("asWanted.cumulativenumber");
				playerData.aw_ContinuityNumber = data.getInt("asWanted.continuitynumber");
				playerData.a_loc = (Location) data.get("attribute.location");
				playerData.online_Times = data.getInt("cumulativeOnlineTime");
		   }else{
			   playerData.save();
		   }
		   return playerData;
	}
	public void save(){
		File DataFile = new File("plugins" + File.separator + "PVPAsWantedManager" + File.separator + "PlayerData" + File.separator + this.player_Name + ".yml");
		YamlConfiguration PlayerData = new YamlConfiguration();
		PlayerData.set("wanted.points", this.w_Points);
		PlayerData.set("wanted.cumulativePoints", this.w_CumulativePoints);
		PlayerData.set("wanted.highestPoints", this.w_HighestPoints);
		PlayerData.set("jail.times", this.j_Times);
		PlayerData.set("jail.cumulativeNumber", this.j_CumulativeNumber);
		PlayerData.set("asWanted.target", this.aw_Target);
		PlayerData.set("asWanted.cumulativenumber", this.aw_CumulativeNumber);
		PlayerData.set("asWanted.continuitynumber", this.aw_ContinuityNumber);
		PlayerData.set("attribute.location", this.a_loc);
		PlayerData.set("cumulativeOnlineTime", this.online_Times);
		try {
			PlayerData.save(DataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getW_Points() {
		return w_Points;
	}
	public void addW_Points() {
		this.w_Points++;
	}
	public void setW_Points(int w_Points) {
		this.w_Points = w_Points;
	}
	public int getW_CumulativePoints() {
		return w_CumulativePoints;
	}
	public void addW_CumulativePoints() {
		this.w_CumulativePoints++;
	}
	public void setW_CumulativePoints(int w_CumulativePoints) {
		this.w_CumulativePoints = w_CumulativePoints;
	}
	public int getW_HighestPoints() {
		return w_HighestPoints;
	}
	public void setW_HighestPoints(int w_HighestPoints) {
		this.w_HighestPoints = w_HighestPoints;
	}
	public int getJ_Times() {
		return j_Times;
	}
	public void setJ_Times(int j_Times) {
		this.j_Times = j_Times;
	}
	public int getJ_CumulativeNumber() {
		return j_CumulativeNumber;
	}
	public void addJ_CumulativeNumber() {
		this.j_CumulativeNumber++;
	}
	public void setJ_CumulativeNumber(int j_CumulativeNumber) {
		this.j_CumulativeNumber = j_CumulativeNumber;
	}
	public String getAw_Target() {
		return aw_Target;
	}
	public void setAw_Target(String aw_Target) {
		this.aw_Target = aw_Target;
	}
	public int getAw_CumulativeNumber() {
		return aw_CumulativeNumber;
	}
	public void addAw_CumulativeNumber() {
		this.aw_CumulativeNumber++;
	}
	public void setAw_CumulativeNumber(int aw_CumulativeNumber) {
		this.aw_CumulativeNumber = aw_CumulativeNumber;
	}
	public int getAw_ContinuityNumber() {
		return aw_ContinuityNumber;
	}
	public void setAw_ContinuityNumber(int aw_ContinuityNumber) {
		this.aw_ContinuityNumber = aw_ContinuityNumber;
	}
	public Location getA_loc() {
		return a_loc;
	}
	public void setA_loc(Location a_loc) {
		this.a_loc = a_loc;
	}
	public int getOnline_Times() {
		return online_Times;
	}
	public void addOnline_Times() {
		this.online_Times++;
	}
	public void setOnline_Times(int online_Times) {
		this.online_Times = online_Times;
	}
	public String getPlayer_Name() {
		return player_Name;
	}
}
