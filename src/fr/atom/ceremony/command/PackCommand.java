package fr.atom.ceremony.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.atom.api.command.Command;
import fr.atom.api.command.CommandArgs;
import fr.atom.ceremony.Ceremony;
import fr.atom.ceremony.config.ConfigManager;
import fr.atom.ceremony.manager.CeremonyManager;

public class PackCommand 
{
	private ConfigManager config = ConfigManager.getINSTANCE();
	
	@Command(name = "ceremonypack")
	public void onCommand(CommandArgs args) 
	{
		if(!args.isPlayer()) return;
		
		 Bukkit.getScheduler().scheduleSyncDelayedTask(Ceremony.getINSTANCE(), () -> {
			 
				Player player = args.getPlayer(); 
				player.setTexturePack(config.TEXTURE_PACK_URL);
				
				config.TEXTURE_PACK_DOWNLOAD_MESSAGE.forEach(message ->{
					player.sendMessage(message);
				});

         }, 1L);
	}
}
