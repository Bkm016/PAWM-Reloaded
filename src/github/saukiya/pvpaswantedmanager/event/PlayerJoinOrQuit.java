package github.saukiya.pvpaswantedmanager.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import github.saukiya.pvpaswantedmanager.util.Data;
import github.saukiya.pvpaswantedmanager.util.PlayerData;

/*本类由 Saukiya 在 2018年2月14日 上午12:31:33 时创建
 *TIM:admin@Saukiya.cn
 *GitHub:https://github.com/Saukiya
**/

public class PlayerJoinOrQuit implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event){
		String playerName = event.getPlayer().getName();
		PlayerData playerData = PlayerData.load(playerName);
		Data.dataMap.put(playerName, playerData);
		//BukkitRunnable
	}
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event){
		if(Data.dataMap.containsKey(event.getPlayer().getName()))
		Data.dataMap.remove(event.getPlayer().getName()).save();
	}
}
