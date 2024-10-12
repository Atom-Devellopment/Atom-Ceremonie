package fr.atom.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageUtils
{
	public static void sendMessageToAdmin(String permission,String message)
	{
		Bukkit.getOnlinePlayers().forEach(player ->{
			if(!player.isOp() || !player.hasPermission(permission)) return;
			player.sendMessage(message);
		});
	}
	
	public static void sendMessageToAdmin(String message)
	{
		Bukkit.getOnlinePlayers().forEach(player ->{
			if(!player.isOp()) return;
			player.sendMessage(message);
		});
	}
	
    public static void sendTitleToPlayer(Player player, String title, String subtitle) 
    {
    	//player.setTitleTimes(0, 40, 0);
        player.sendTitle(title, subtitle);
    }
    
    public static void clearTitle(Player player) 
    {
        player.resetTitle();
    }
    
    public static void sendTitleToAllPlayers(String title, String subtitle) 
    {
        for (Player player : Bukkit.getOnlinePlayers()) 
        {
            sendTitleToPlayer(player, title, subtitle);
        }
    }
    
    public static void clearTitleToAllPlayers()
    {
        for (Player player : Bukkit.getOnlinePlayers()) 
        {
        	clearTitle(player);
        }
    }
}
