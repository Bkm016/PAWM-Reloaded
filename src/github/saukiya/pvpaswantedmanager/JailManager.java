package github.saukiya.pvpaswantedmanager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import github.saukiya.pvpaswantedmanager.util.Config;
import github.saukiya.pvpaswantedmanager.util.Data;
import github.saukiya.pvpaswantedmanager.util.PlayerData;

/*本类由 Saukiya 在 2018年2月15日 上午1:43:33 时创建
 *TIM:admin@Saukiya.cn
 *GitHub:https://github.com/Saukiya
**/

public class JailManager {
	static public void tpAttributeLocation(Player player){
		new BukkitRunnable(){

			@Override
			public void run() {
				PlayerData playerData = Data.getPlayerData(player.getName());
				Location loc = playerData.getA_loc();
				player.teleport(loc);
				System.out.println("tp");
			}
		}.runTask(PVPAsWantedManager.getPlugin());
	}
	static public void tpJailLocation(Player player){
		int x = Integer.valueOf(Config.getConfig(Config.JAIL_LOCATION_X));
		int y = Integer.valueOf(Config.getConfig(Config.JAIL_LOCATION_Y));
		int z = Integer.valueOf(Config.getConfig(Config.JAIL_LOCATION_Z));
		World world = Bukkit.getWorld(Config.getConfig(Config.JAIL_LOCATION_WORLD));
		player.teleport(new Location(world, x, y, z));
	}
}
