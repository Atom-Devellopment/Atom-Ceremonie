package fr.atom.ceremony.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.atom.api.AtomApi;
import fr.atom.api.utils.MessageUtils;
import fr.atom.api.utils.PluginUtils;
import fr.atom.ceremony.command.CeremonyCommand;
import fr.atom.ceremony.command.PackCommand;
import fr.atom.ceremony.config.ConfigManager;
import fr.atom.ceremony.listener.CeremonyListener;
import fr.atom.ceremony.task.CeremonyTask;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CeremonyManager 
{
	@Getter public static CeremonyManager INSTANCE;
	
	public ConfigManager config = ConfigManager.getINSTANCE();
	public boolean isStarted = false;
	public boolean isCancel;
	
        
	public CeremonyManager()
	{
		INSTANCE = this;
		
		PluginUtils.registerCommand(new CeremonyCommand());
		PluginUtils.registerCommand(new PackCommand());
		
		if(config.TEXTURE_PACK_AUTO_DOWNLOAD)
			PluginUtils.registerListener(new CeremonyListener());
	}
	
	public void startCeremony()
	{
		setStarted(true);
		setCancel(false);
		
		CeremonyTask ceremonyTask = new CeremonyTask();
		ceremonyTask.runTaskTimerAsynchronously(AtomApi.getINSTANCE(), 0, 20);
	}
	
	public void startSound() {        
        Bukkit.getOnlinePlayers().forEach(player ->
            player.playSound(player.getLocation(), config.CEREMONY_SOUND, 10, 1)
        );
    } 	
	
	public void startThankYouAnimation() {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        new BukkitRunnable() {
            int playerIndex = 0;

            @Override
            public void run() {
                if (playerIndex < players.size()) {
                    Player player = players.get(playerIndex);
                    sendThankYouTitle(player);
                    playerIndex++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(AtomApi.getINSTANCE(), 0, config.END_ANIMATION_TIMER_COOLDOWN);
    }
    
    private void sendThankYouTitle(Player player) {
        String playerName = player.getName();
        String title = config.END_ANIMATION_MESSAGE.replace("%player%", playerName);
        
        MessageUtils.clearTitleToAllPlayers();
        MessageUtils.sendTitleToAllPlayers("",title);
    }
}
