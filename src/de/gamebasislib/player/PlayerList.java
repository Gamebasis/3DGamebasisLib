/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasislib.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Justin
 */
public class PlayerList {
    
    protected HashMap<Integer,IPlayer> player = new HashMap<Integer,IPlayer>();
    protected int lastPlayerID = 0;
    protected List<IPlayer> playeronline = new ArrayList<IPlayer>();
    
    public PlayerList () {
        this.player = new HashMap<Integer,IPlayer>();
    }
    
    public int addPlayer (IPlayer player) {
        this.player.put(++this.lastPlayerID, player);
        return this.lastPlayerID;
    }
    
    public List<IPlayer> getPlayerOnlineList () {
        return this.playeronline;
    }
    
    public void logoutPlayer (int playerID) {
        IPlayer player = this.player.get(playerID);
        
        if (player != null) {
            this.playeronline.remove(player);
        }
    }
    
}
