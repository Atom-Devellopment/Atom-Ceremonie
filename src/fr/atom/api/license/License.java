package fr.atom.api.license;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import lombok.Getter;

@Getter
public class License {

    private String requestServer = "https://rosybrown-hamster-878175.hostingersite.com/";
    private String requestKey = "vmLAyzmppLLDgvqMPFyHLSkWdyHYqRImNueC1OLK";
	
    private String license;
    private String plugin;
    private boolean debug;

    private boolean valid = false;
    private ReturnType returnType;
    
    public License(String license, String plugin, boolean debug) {
        this.license = license;
        this.plugin = plugin;
        this.debug = debug;
    }

   
    public void request() {
        try {
            URL url = new URL(requestServer + "/request.php");
            URLConnection connection = url.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

            connection.setRequestProperty("License-Key", license);
            connection.setRequestProperty("Plugin-Name", plugin);
            connection.setRequestProperty("Request-Key", requestKey);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String response = builder.toString();

            String[] responseSplited = response.split(";");
            if (responseSplited[0].equals("VALID")) {
                valid = true;
                returnType = ReturnType.valueOf(responseSplited[0]);
            } else {
                valid = false;
                returnType = ReturnType.valueOf(responseSplited[0]);
            }
        } catch (Exception ex) {
            if (debug) {
                ex.printStackTrace();
            }
        }
    }
}