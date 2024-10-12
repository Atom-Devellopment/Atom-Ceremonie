package fr.atom.api.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import fr.atom.api.AtomApi;

public abstract class GsonAdapter<T> extends TypeAdapter<T> {
	
  private AtomApi plugin;
  
  public GsonAdapter(AtomApi plugin) {
    this.plugin = plugin;
  }
  
  public Gson getGson() {
    return this.plugin.getGson();
  }
}
 