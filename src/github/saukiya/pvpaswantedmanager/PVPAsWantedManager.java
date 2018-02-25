package github.saukiya.pvpaswantedmanager;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import github.saukiya.pvpaswantedmanager.util.SubCommand;
import github.saukiya.pvpaswantedmanager.util.Placeholders;
import github.saukiya.pvpaswantedmanager.event.InventoryClick;
import github.saukiya.pvpaswantedmanager.event.Jail;
import github.saukiya.pvpaswantedmanager.event.PlayerDeath;
import github.saukiya.pvpaswantedmanager.event.PlayerJoinOrQuit;
import github.saukiya.pvpaswantedmanager.util.Config;
import github.saukiya.pvpaswantedmanager.util.Data;
import github.saukiya.pvpaswantedmanager.util.Message;

/*本类由 Saukiya 在 2018年2月14日 上午10:41:50 时创建
 *TIM:admin@Saukiya.cn
 *GitHub:https://github.com/Saukiya
 *佛祖保佑 永无BUG
**/

public class PVPAsWantedManager extends JavaPlugin implements Listener{
	static ArrayList<Method> methodList = new ArrayList<Method>();
	public void onEnable()
	{
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinOrQuit(), this);
        Bukkit.getPluginManager().registerEvents(new Jail(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
        	new Placeholders(this).hook();
        	Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §aFind PlacholderAPI!");
        }
        Config.loadConfig();
        Data.loadData();
		Message.loadMessage();
		for(Method m:this.getClass().getDeclaredMethods()){
			methodList.add(m);
		}
        Bukkit.getConsoleSender().sendMessage("[PVPAsWantedManager] §a加载成功! 插件作者: Saukiya 联系:1940208750");
	}
	static public Plugin getPlugin(){
		return PVPAsWantedManager.getPlugin(PVPAsWantedManager.class);
	}
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
        if(label.equalsIgnoreCase("pawm") || label.equalsIgnoreCase(this.getName())){
                //判断是否是玩家
                if((sender instanceof Player)){
                    //判断是否有权限
                    if(!sender.hasPermission(this.getName()+ ".use")){
        				sender.sendMessage(Message.getMsg(Message.ADMIN_NO_PER_CMD));
                        return true;
                    }
                }
                //无参数
                if(args.length==0){
                	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6==========[&b "+ this.getName() +"&6 ]=========="));
                        for(java.lang.reflect.Method method : methodList){
                                if(!method.isAnnotationPresent(SubCommand.class)){
                                        continue;
                                }
                                SubCommand sub=method.getAnnotation(SubCommand.class);
                                if(sender.hasPermission(this.getName()+"." + sub.cmd())){
                                	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/"+ label + " " +sub.cmd()+"&6"+sub.arg()+"&7-:&3 "+Message.getMsg("Command."+ sub.cmd())));
                                }
                        }
                        return true;
                }
                for(java.lang.reflect.Method method:methodList){
                        if(!method.isAnnotationPresent(SubCommand.class)){
                                continue;
                        }
                        SubCommand sub=method.getAnnotation(SubCommand.class);
                        if(!sub.cmd().equalsIgnoreCase(args[0])){
                                continue;
                        }
                        //判断玩家是否有这个权限
                		if(sender instanceof Player){
                			if(!sender.hasPermission(this.getName()+ "." + args[0])) {
                				sender.sendMessage(Message.getMsg(Message.ADMIN_NO_PER_CMD));
                	            return true;
                			}
                		}
                        try {
                                method.invoke(this, sender,args);
                        } catch (IllegalAccessException e) {
                                e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                        } catch (InvocationTargetException e) {
                                e.printStackTrace();
                        }
                        return true;
                }
                sender.sendMessage(Message.getMsg(Message.ADMIN_NO_CMD, args[0]));
            return true;
        }
        return false;
	}

	@SubCommand(cmd="open")
	void onOpenCommand(CommandSender sender,String args[]){
		//TODO 让玩家打开菜单
	}
	@SubCommand(cmd="joinJail",arg=" <player> <times>")
	void onJoinJailCommand(CommandSender sender,String args[]){
		//TODO 让玩家进入监狱
	}
	@SubCommand(cmd="quitJail",arg=" <player>")
	void onQuitJailCommand(CommandSender sender,String args[]){
		//TODO 让玩家离开监狱
	}
	@SubCommand(cmd="setJail",arg=" <player>")
	void onSetJailCommand(CommandSender sender,String args[]){
		//TODO 设置监狱位置
	}
	
	@SubCommand(cmd="reload")
	void onReloadCommand(CommandSender sender,String args[]){
        Config.loadConfig();
        Data.loadData();
		Message.loadMessage();
		sender.sendMessage(Message.getMsg(Message.ADMIN_RELOAD));
		for(World world:Bukkit.getWorlds()){
			System.out.println(world.getName());
		}
	}
	
}
