package fr.atom.ceremony;

import fr.atom.api.AtomApi;
import fr.atom.ceremony.config.ConfigManager;
import fr.atom.ceremony.manager.CeremonyManager;

public class Ceremony extends AtomApi
{
	public Ceremony() 
	{
		super("Ceremony", 22722);		
	}
	
	@Override
	public void enable() 
	{
		this.saveDefaultConfig();
		ConfigManager config = new ConfigManager();
		config.loadConfiguration();
		
		new CeremonyManager();
	}
	
	@Override
	public void disable() {}
}
