package fr.atom.ceremony.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import fr.atom.ceremony.Ceremony;
import lombok.Getter;

public class ConfigManager 
{
	@Getter public static ConfigManager INSTANCE;
	public ConfigManager(){ INSTANCE = this; }

	public String CEREMONY_PERMISSION;
	public String TEXTURE_PACK_URL;
	public String CEREMONY_SOUND;
	
	public boolean TEXTURE_PACK_AUTO_DOWNLOAD;
	public List<String> TEXTURE_PACK_DOWNLOAD_MESSAGE;

	public boolean END_ANIMATION_ACTIVE;
	public int END_ANIMATION_TIMER_COOLDOWN;
	public String END_ANIMATION_MESSAGE;

	public String CEREMONY_ALREADY_STARTED;	
	
	public int CEREMONY_TIME;
	
	public static Map<Integer, String> CEREMONY_TASK_MESSAGE = new HashMap<>();
	
	
	public void loadConfiguration()
	{
		FileConfiguration config = Ceremony.getINSTANCE().getConfig();		
		
		CEREMONY_PERMISSION = config.getString("ceremony-permission");
		TEXTURE_PACK_URL = config.getString("texture-pack-url");
		CEREMONY_SOUND = config.getString("ceremony-sound");

		TEXTURE_PACK_AUTO_DOWNLOAD = config.getBoolean("texture-pack-auto-download");
		TEXTURE_PACK_DOWNLOAD_MESSAGE = (List<String>) config.getStringList("texture-pack-download-message");
		TEXTURE_PACK_DOWNLOAD_MESSAGE.replaceAll(string -> string.replace("&", "§"));
		
		END_ANIMATION_ACTIVE = config.getBoolean("active-thank-you-animation");
		END_ANIMATION_TIMER_COOLDOWN = config.getInt("thank-you-animation-cooldown");
		END_ANIMATION_MESSAGE = config.getString("thank-you-animation-message").replace("&","§");
		
		CEREMONY_ALREADY_STARTED = config.getString("ceremony-already-started").replace("&", "§");

		CEREMONY_TIME = config.getInt("ceremony-time");
		
		for (String key : config.getConfigurationSection("ceremony-messages").getKeys(false)) {
            int time = Integer.parseInt(key);
            String message = config.getString("ceremony-messages." + key);
            CEREMONY_TASK_MESSAGE.put(time, message);
        }		
	}
}
