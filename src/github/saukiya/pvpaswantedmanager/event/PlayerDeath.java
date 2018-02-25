package github.saukiya.pvpaswantedmanager.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import github.saukiya.pvpaswantedmanager.JailManager;
import github.saukiya.pvpaswantedmanager.util.Config;
import github.saukiya.pvpaswantedmanager.util.Data;
import github.saukiya.pvpaswantedmanager.util.PlayerData;
import github.saukiya.pvpaswantedmanager.util.Message;
import github.saukiya.pvpaswantedmanager.util.Money;

/*本类由 Saukiya 在 2018年2月14日 上午12:31:13 时创建
 *TIM:admin@Saukiya.cn
 *GitHub:https://github.com/Saukiya
**/

public class PlayerDeath implements Listener {
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event){
		Player player = event.getEntity();
		if(!Data.isOn(player.getWorld().getName()))return;
		Player killer = player.getKiller();
		Location loc = player.getLocation();
		System.out.println(" ");
		System.out.println(player +" "+ killer);
		System.out.println(loc);
		PlayerData playerData = Data.getPlayerData(player.getName());
		int playerWantedPoints = playerData.getW_Points();
		int playerJailCumulativeNumber = playerData.getJ_CumulativeNumber();
		//玩家与玩家之间
		if(killer !=null){
			PlayerData killerData = Data.getPlayerData(killer.getName());
			if(Data.isAsWanted(player.getName(), killerData)){//通缉状态
				//处理通缉犯
				int jailTime = Integer.valueOf(Config.getConfig(Config.TIMETICK_JAIL_PLAYER_TIMES).replaceAll("[^0-9]", ""));
				playerData.setJ_Times(playerData.getW_Points()*jailTime);
				playerData.setW_Points(0);
				playerData.addJ_CumulativeNumber();
				playerData.setA_loc(loc);
				player.spigot().respawn();
				//TODO 传送到指定位置
				JailManager.tpJailLocation(player);
				Data.removeWantedList(player.getName());
				Double taskRewardMoney = Double.valueOf(Config.getConfig(Config.TASKREWARD_MONEY));
				if(Config.getConfig(Config.ARREST_PUNISH_ENABLED).equalsIgnoreCase("true")){
					if(Money.has(player.getName(), playerWantedPoints*taskRewardMoney)){
						Money.take(player.getName(), playerWantedPoints*taskRewardMoney);
						player.sendMessage(Message.getMsg(Message.PLAYER_ARREST_PUNISH, String.valueOf(playerWantedPoints*taskRewardMoney)));
					}else{
						playerData.setJ_Times(playerData.getJ_Times()+(playerWantedPoints*10));
						player.sendMessage(Message.getMsg(Message.PLAYER_ARREST_PUNISH_FAIL, String.valueOf(playerWantedPoints*10)));
					}
				}
				//处理逮捕人员
			}else{//击杀状态
				
			}
		}
		if(playerData.getW_Points()>0)Data.takePlayerLevel(player, playerData.getW_Points());
		
	}
}
