package be.ninedocteur.docmod.data;

import java.util.List;

public class DMServerData {
	private List<Player> players;
	
	private String version;
	
	private String online_players;
	
	private String slots;
	 
	  
	  public List<Player> getPlayers() {
	    return players;
	  }
	  
	  public String getVersion() {
		return version;
	}
	  
	  public String getSlots() {
		return slots;
	}
	  
	  public String getOnlinePlayers() {
		return online_players;
	}
	  
	  public static class Player {
	    private String username;
	    
	    public String getUsername() {
	      return username;
	    }
	  }
}
