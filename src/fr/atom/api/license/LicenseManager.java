package fr.atom.api.license;

import fr.atom.api.AtomApi;
import fr.atom.api.license.config.LicenseConfig;
import fr.atom.api.license.config.LicenseConfiguration;
import lombok.Getter;

@Getter
public class LicenseManager 
{	
	private String pluginName = AtomApi.getINSTANCE().getPluginName();
	private License license;
	private String licenseKey;
	
	public LicenseManager()
	{
		new LicenseConfig();
		
		this.licenseKey = LicenseConfiguration.getINSTANCE().LICENSE;
		license = new License(licenseKey, pluginName, false);
        license.request();
	}
	
	public boolean isValidLicense()
	{        
        return license.isValid();
	}
	
	public void returnLicenseState()
	{
        String state = license.isValid() ? "Valide" : "Invalide";

		if (license.isValid()) {
        	System.out.println("------------------------------");
        	System.out.println("Informations:");
        	System.out.println("| License: " + license.getLicense());
        	System.out.println("| Plugin: " + pluginName);
        	System.out.println("| État: " + state);
        	System.out.println("------------------------------");
        } else {
        	System.out.println("License Information:");
        	System.out.println("| License: " + license.getLicense());
        	System.out.println("| Plugin: " + pluginName);
        	System.out.println("| État: " + state);
        }
	}
	
	
	
}
