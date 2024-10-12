package fr.atom.api.license.config;

import org.bukkit.configuration.file.FileConfiguration;

import lombok.Getter;


public class LicenseConfiguration 
{
	@Getter public static LicenseConfiguration INSTANCE;
	
	public LicenseConfiguration()
	{
		INSTANCE = this;
	}
	
	public String LICENSE;
	
	public  void loadConfiguration()
	{
		FileConfiguration config = LicenseConfig.getINSTANCE().getLicenseConfig();

		LICENSE = config.getString("license-key");
	}
}
