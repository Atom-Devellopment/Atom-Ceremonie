package fr.atom.ceremony.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.atom.ceremony.Ceremony;
import fr.atom.ceremony.config.ConfigManager;

public class CeremonyListener implements Listener 
{
	private ConfigManager config = ConfigManager.getINSTANCE();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{	
		if(!config.TEXTURE_PACK_AUTO_DOWNLOAD) return;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Ceremony.getINSTANCE(), () -> {	 
			Player player = event.getPlayer();		
			player.setTexturePack(config.TEXTURE_PACK_URL); 
		}, 1L);

	}
}
