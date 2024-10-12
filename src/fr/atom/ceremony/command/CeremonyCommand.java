package fr.atom.ceremony.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.atom.api.command.Command;
import fr.atom.api.command.CommandArgs;
import fr.atom.api.utils.PluginUtils;
import fr.atom.ceremony.config.ConfigManager;
import fr.atom.ceremony.manager.CeremonyManager;

public class CeremonyCommand {

	private ConfigManager config = ConfigManager.getINSTANCE();
	private CeremonyManager manager = CeremonyManager.getINSTANCE();
	
	@Command(name = "ceremony")
	public void onCommand(CommandArgs args) 
	{
		CommandSender sender = args.getSender();
				
		if(!hasPermission(sender)) return;
		
		//TODO SEND COMMAND MESSAGE
		sender.sendMessage("");
		sender.sendMessage(" §7┃ §7§lAtom§7-Ceremony:");
		sender.sendMessage("");
		sender.sendMessage("§6/ceremony start§7:§e permet de lancer la ceremony");
		sender.sendMessage("§6/ceremonypack§7:§e permet de télécharger manuellement le pack de texture");
		sender.sendMessage("");
		sender.sendMessage(" §7┃ §7Support: §7" + PluginUtils.getSupportDiscord());
		sender.sendMessage("");
		
	}
	
	@Command(name = "ceremony.start")
	public void onStart(CommandArgs args)
	{	
		CommandSender sender = args.getSender();
		
		if(!hasPermission(sender)) return;
		
		if(manager.isStarted)
		{
			sender.sendMessage(config.CEREMONY_ALREADY_STARTED);
			return;
		}
		
		manager.startCeremony();
	}
	
	private boolean hasPermission(CommandSender sender)
	{
		return sender.isOp() || sender.hasPermission(config.CEREMONY_PERMISSION);
	}

}
