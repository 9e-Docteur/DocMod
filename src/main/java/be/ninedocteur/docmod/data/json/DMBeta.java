package be.ninedocteur.docmod.data.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DMBeta {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("players")
    @Expose
    private List<String> players = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

}
