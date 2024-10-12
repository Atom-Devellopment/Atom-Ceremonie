package fr.atom.api.license.config;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.core.Core;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.atom.api.AtomApi;
import lombok.Getter;

public class LicenseConfig 
{
	@Getter public static LicenseConfig INSTANCE;
	
	public File licenseFile;
	@Getter public FileConfiguration licenseConfig;
	private LicenseConfiguration licenseConfiguration = new LicenseConfiguration();
	
	
	public LicenseConfig()
	{
		INSTANCE = this;
		
		createLicenseConfig();
		licenseConfiguration.loadConfiguration();
	}
	
	
	private void createLicenseConfig() {
		licenseFile = new File(AtomApi.getINSTANCE().getDataFolder(), "license.yml");
        if (!licenseFile.exists()) {
        	licenseFile.getParentFile().mkdirs();
        	AtomApi.getINSTANCE().saveResource("license.yml", false);
         }

        licenseConfig= new YamlConfiguration();
        try {
        	licenseConfig.load(licenseFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
