package fr.atom.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import fr.atom.api.AtomApi;

public class PluginUtils 
{
	private static AtomApi api = AtomApi.getINSTANCE();
	
	public static void registerCommand(Object obj) 
	{
		api.getCommandFramework().registerCommands(obj);
	}
	
	public static void registerListener(Listener listener) 
	{
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(listener, api);
	}
	
	public static String getSupportDiscord()
	{
		return "https://discord.gg/CbNp7yh";
	}
}
