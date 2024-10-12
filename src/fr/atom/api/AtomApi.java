package fr.atom.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;

import fr.atom.api.bstats.Metrics;
import fr.atom.api.command.CommandFramework;
import fr.atom.api.inventory.FastInvManager;
import fr.atom.api.license.LicenseManager;
import lombok.Getter;

@Getter
public abstract class AtomApi extends JavaPlugin 
{
	@Getter public static AtomApi INSTANCE;
	
	private String pluginName;
	private int pluginId;
	
	public CommandFramework commandFramework;
	public Gson gson;

	public AtomApi(String pluginName, int pluginId)
	{
		INSTANCE = this;
		this.pluginName = pluginName;
		this.pluginId = pluginId;
	}
	
	@Override
	public void onEnable() 
	{
		commandFramework = new CommandFramework(this);
		FastInvManager.register(this);

		//this.gson = SaverManager.getINSTANCE().getGsonBuilder().create();
		
		LicenseManager licenseManager = new LicenseManager();
		licenseManager.returnLicenseState();

		if(!licenseManager.isValidLicense())
		{
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		
		enable();
		
        Metrics metrics = new Metrics(this, pluginId);
		
		getLogger().info("---------------------------------------");
		getLogger().info(pluginName + ": ON");
		getLogger().info("---------------------------------------");
	}
	
	@Override
	public void onDisable() 
	{
		disable();
		
		getLogger().info("---------------------------------------");
		getLogger().info(pluginName + ": OFF");
		getLogger().info("---------------------------------------");
	}
	
	public abstract void enable();	
	public abstract void disable();
}
