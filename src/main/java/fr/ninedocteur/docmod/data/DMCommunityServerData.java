package fr.ninedocteur.docmod.data;

import com.google.gson.annotations.SerializedName;

public class DMCommunityServerData {
	@SerializedName("servers")
	public ServerInfo[] servers;
	
	@SerializedName("server_count")
	public String server_count;
	
	public ServerInfo[] getServers() {
		return servers;
	}
	
	public String getServerCount() {
		return server_count;
	}

    public static class ServerInfo {
    	@SerializedName("icon")
        private String icon;

        @SerializedName("server_name")
        private String serverName;

        @SerializedName("server_owner")
        private String serverOwner;

        @SerializedName("server_ip")
        private String serverIp;
        
        @SerializedName("players_online")
        private String playerOnline;
        
        @SerializedName("players_max")
        private String playerMax;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getServerName() {
			return serverName;
		}

        public void setServerName(String server_name) {
            this.serverName = server_name;
        }

        public String getServerOwner() {
            return serverOwner;
        }

        public void setServerOwner(String server_owner) {
            this.serverOwner = server_owner;
        }

        public String getServerIp() {
            return serverIp;
        }

        public void setServerIp(String server_ip) {
            this.serverIp = server_ip;
        }
        
        public String getPlayerMax() {
			return playerMax;
		}
        
        public String getPlayerOnline() {
			return playerOnline;
		}
    }
}
