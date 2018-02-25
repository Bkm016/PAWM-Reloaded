package github.saukiya.pvpaswantedmanager.util;

import org.bukkit.entity.Player;

import org.bukkit.configuration.file.YamlConfiguration;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import github.saukiya.pvpaswantedmanager.PVPAsWantedManager;

public class Placeholders  extends EZPlaceholderHook{
	
	@SuppressWarnings("unused")
	private PVPAsWantedManager ourPlugin;

	public Placeholders(PVPAsWantedManager ourPlugin) {
		super(ourPlugin, "pawm");
		this.ourPlugin = ourPlugin;
	}

	@Override
	public String onPlaceholderRequest(Player player, String string) {
		PlayerData playerData = Data.getPlayerData(player.getName());
		if(string.equals("wanted_points")) return String.valueOf(playerData.getW_Points());
		if(string.equals("wanted_cumulativepoints")) return String.valueOf(playerData.getW_CumulativePoints());
		if(string.equals("wanted_highestpoints")) return String.valueOf(playerData.getW_HighestPoints());
		if(string.equals("jail_times")) return String.valueOf(playerData.getJ_Times());
		if(string.equals("jail_cumulativenumber")) return String.valueOf(playerData.getJ_CumulativeNumber());
		if(string.equals("aswanted_target"))return String.valueOf(playerData.getAw_Target());
		if(string.equals("aswanted_cumulativenumber")) return String.valueOf(playerData.getAw_CumulativeNumber());
		if(string.equals("aswanted_continuitynumber")) return String.valueOf(playerData.getAw_ContinuityNumber());
		return "§c请核对你的变量是否正确!";
	}

}
