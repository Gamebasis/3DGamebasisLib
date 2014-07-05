/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.gamesettings;

import de.gamebasislib.event.GameEvent;
import java.util.HashMap;

/**
 *
 * @author Justin
 */
public class GameSettingsChangedEvent implements GameEvent {
    
    protected HashMap<String,String> gamesettings = new HashMap<String,String>();
    
    public GameSettingsChangedEvent () {
        //
    }
    
    protected void setHashMap (HashMap<String,String> gamesettings) {
        this.gamesettings = gamesettings;
    }
    
    public HashMap<String,String> getHashMap () {
        return this.gamesettings;
    }
    
    public void addGameSetting (String gamesetting, String value) {
        this.gamesettings.put(gamesetting, value);
    }
    
}
